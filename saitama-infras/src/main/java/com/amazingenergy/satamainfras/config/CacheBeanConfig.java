package com.amazingenergy.satamainfras.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.cache.CacheManager;
import javax.cache.Caching;

import com.amazingenergy.saitamadomain.reference.domain.Country;
import com.amazingenergy.saitamadomain.reference.domain.Currency;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamadomain.reference.domain.Zone;
import com.amazingenergy.satamainfras.seedwork.CacheEventLogger;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.ConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.PooledExecutionServiceConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.builders.WriteBehindConfigurationBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.event.EventType;
import org.ehcache.impl.config.event.CacheEventDispatcherFactoryConfiguration;
import org.ehcache.impl.config.loaderwriter.writebehind.WriteBehindProviderConfiguration;
import org.ehcache.impl.config.persistence.CacheManagerPersistenceConfiguration;
import org.ehcache.impl.config.store.disk.OffHeapDiskStoreProviderConfiguration;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.ehcache.xml.XmlConfiguration;
import org.ehcache.xml.exceptions.XmlConfigurationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;

@Configuration
@EnableCaching(mode = AdviceMode.PROXY, proxyTargetClass = false, order = Ordered.LOWEST_PRECEDENCE)
public class CacheBeanConfig {
    /**
     * we use CacheManager is auto-configured by Spring Boot.
     * we can further tune its configuration before it is fully initialized.
     *
     * @return
     */
    @Bean
    public CacheManagerCustomizer<ConcurrentMapCacheManager> cacheManagerCustomizer() {
        return (cacheManager) -> cacheManager.setAllowNullValues(false);
    }

    @Value("${my.cache.persistence.directory}")
    public String cachePersistenceDirectory;

    @Value("${spring.cache.jcache.config}")
    public Resource cacheManagerConfigFile;

    @Bean
    public CacheManager EhcacheManager() {
        var cachingProvider = (EhcacheCachingProvider) Caching.getCachingProvider();

        var cacheManagerConfigBuilder = ConfigurationBuilder.newConfigurationBuilder()
                .withService(new CacheManagerPersistenceConfiguration(getCachePersistenceDirectory()))
                .withService(PooledExecutionServiceConfigurationBuilder.newPooledExecutionServiceConfigurationBuilder()
                        .defaultPool("dflt", 0, 10)
                        .pool("defaultDiskPool", 1, 3)
                        .pool("defaultWriteBehindPool", 1, 3)
                        .pool("defaultEventPool", 1, 3)
                        .pool("twoConcurrentPools", 2, 2)
                        .build())
                .withService(new OffHeapDiskStoreProviderConfiguration("defaultDiskPool"))
                .withService(new WriteBehindProviderConfiguration("defaultWriteBehindPool"))
                .withService(new CacheEventDispatcherFactoryConfiguration("defaultEventPool"));

        var eventListenerConfig = CacheEventListenerConfigurationBuilder
                .newEventListenerConfiguration(CacheEventLogger.class,
                        EventType.CREATED, EventType.EVICTED, EventType.EXPIRED)
                .unordered().asynchronous();

        var writeBehindConfig =
                WriteBehindConfigurationBuilder.newBatchedWriteBehindConfiguration(1,
                        TimeUnit.SECONDS, 3).queueSize(3).concurrencyLevel(1);

        cacheManagerConfigBuilder = cacheManagerConfigBuilder
                .withCache("country", getJavaBaseCacheConfig(SimpleKey.class, Country.class)
                        .withDiskStoreThreadPool("twoConcurrentPools", 2)
                        .withService(writeBehindConfig)
                        .withService(eventListenerConfig)
                        .build())
                .withCache("countries", getJavaBaseCacheConfig(String.class, Country.class)
                        .withDiskStoreThreadPool("twoConcurrentPools", 2)
                        .withService(writeBehindConfig)
                        .withService(eventListenerConfig)
                        .build())
                .withCache("countries_language", getJavaBaseCacheConfig(UUID.class, Country.class)
                        .withDiskStoreThreadPool("twoConcurrentPools", 2)
                        .withService(writeBehindConfig)
                        .withService(eventListenerConfig)
                        .build())
                .withCache("countries_zones", getJavaBaseCacheConfig(UUID.class, Country.class)
                        .withDiskStoreThreadPool("twoConcurrentPools", 2)
                        .withService(writeBehindConfig)
                        .withService(eventListenerConfig)
                        .build())
                .withCache("language", getJavaBaseCacheConfig(SimpleKey.class, Language.class)
                        .withDiskStoreThreadPool("twoConcurrentPools", 2)
                        .withService(writeBehindConfig)
                        .withService(eventListenerConfig)
                        .build())
                .withCache("languages", getJavaBaseCacheConfig(SimpleKey.class, Language.class)
                        .withDiskStoreThreadPool("twoConcurrentPools", 2)
                        .withService(writeBehindConfig)
                        .withService(eventListenerConfig)
                        .build())
                .withCache("zone", getJavaBaseCacheConfig(SimpleKey.class, Zone.class)
                        .withDiskStoreThreadPool("twoConcurrentPools", 2)
                        .withService(writeBehindConfig)
                        .withService(eventListenerConfig)
                        .build())
                .withCache("zones", getJavaBaseCacheConfig(String.class, Zone.class)
                        .withDiskStoreThreadPool("twoConcurrentPools", 2)
                        .withService(writeBehindConfig)
                        .withService(eventListenerConfig)
                        .build())
                .withCache("zones_language", getJavaBaseCacheConfig(UUID.class, Zone.class)
                        .withDiskStoreThreadPool("twoConcurrentPools", 2)
                        .withService(writeBehindConfig)
                        .withService(eventListenerConfig)
                        .build());

        return cachingProvider.getCacheManager(cachingProvider.getDefaultURI(), cacheManagerConfigBuilder.build());
    }

    private <K, V> CacheConfigurationBuilder<K, V> getJavaBaseCacheConfig(Class<K> keyClazz, Class<V> valueClazz) {
        return CacheConfigurationBuilder
                .newCacheConfigurationBuilder(keyClazz,
                        valueClazz,
                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                .heap(10, EntryUnit.ENTRIES)
                                // .offheap(10, MemoryUnit.MB)
                                .disk(10, MemoryUnit.MB)
                                .build())
                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(10)));
    }

    private CacheConfiguration<?, ?> getXmlBaseCacheConfig(String alias)
            throws XmlConfigurationException, IOException {
        var xmlConfig = new XmlConfiguration(cacheManagerConfigFile.getURL());
        return xmlConfig.getCacheConfigurations().get(alias);
    }

    private File getCachePersistenceDirectory() {
        return Path.of(cachePersistenceDirectory).toFile();
    }
}
