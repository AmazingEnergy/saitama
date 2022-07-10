package com.amazingenergy.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass=true)
@Configuration("core-config")
public class CoreBeanConfig {
}
