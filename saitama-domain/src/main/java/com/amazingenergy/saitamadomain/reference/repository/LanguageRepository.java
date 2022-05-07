package com.amazingenergy.saitamadomain.reference.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public interface LanguageRepository extends EntityRepository<UUID, Language> {
    Language getByCode(String code);

    Map<String, Language> getLanguagesMap();

    List<Language> getLanguages();

    Locale toLocale(Language language, MerchantStore store);

    Language toLanguage(Locale locale);

    Language defaultLanguage();
}
