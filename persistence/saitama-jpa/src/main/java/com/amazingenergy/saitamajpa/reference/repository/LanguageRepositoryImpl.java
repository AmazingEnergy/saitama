package com.amazingenergy.saitamajpa.reference.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamadomain.reference.repository.LanguageRepository;
import com.amazingenergy.saitamadomain.seedwork.constant.Constants;
import com.amazingenergy.saitamajpa.reference.ReferenceMapper;
import com.amazingenergy.saitamajpa.reference.entity.LanguageEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Repository
public class LanguageRepositoryImpl extends BaseRepositoryImpl<UUID, Language, LanguageEntity> implements LanguageRepository {
    private final JpaLanguageRepository jpaRepository;
    private final ReferenceMapper mapper;

    public LanguageRepositoryImpl(JpaLanguageRepository jpaRepository, ReferenceMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    @Cacheable("language")
    public Optional<Language> getByCode(String code) {
        return jpaRepository.findByCode(code).map(this::from);
    }

    @Override
    @Cacheable("languages")
    public List<Language> getLanguages() {
        return this.findAll();
    }

    @Override
    public Optional<Language> toLanguage(Locale locale) {
        var language = this.getLanguages().stream()
                .filter(l -> l.getCode() == locale.getLanguage())
                .findFirst();
        if(language.isEmpty())
            return Optional.of(new Language(Constants.DEFAULT_LANGUAGE));
        return language;
    }

    @Override
    public Language defaultLanguage() {
        return toLanguage(Locale.ENGLISH).get();
    }

    @Override
    public LanguageEntity to(Language source) {
        return mapper.to(source);
    }

    @Override
    public Language from(LanguageEntity destination) {
        return mapper.from(destination);
    }
}
