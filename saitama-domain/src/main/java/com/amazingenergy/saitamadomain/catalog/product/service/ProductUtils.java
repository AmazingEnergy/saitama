package com.amazingenergy.saitamadomain.catalog.product.service;

import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.domain.availability.ProductAvailability;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamadomain.seedwork.constant.Constants;

import java.util.HashSet;
import java.util.Locale;
import java.util.stream.Collectors;

public class ProductUtils {
    /**
     * Filters descriptions and set the appropriate language
     */
    public static void setToLanguage(Product product, Language language) {
        for (var attribute : product.getAttributes()) {
            var optionDesc = attribute.getOption().getDescriptions()
                    .stream().filter(d -> d.getLanguage().getId() == language.getId()).collect(Collectors.toSet());
            attribute.getOption().setDescriptions(optionDesc);
        }
    }

    /**
     * Overwrites the availability in order to return 1 price / region
     *
     * @param product
     * @param locale
     */
    public static void setToAvailability(Product product, Locale locale) {
        var defaultAvailability = product.getAvailabilities()
                .stream().filter(a -> a.getRegion().equals(Constants.ALL_REGIONS)).findFirst();
        var localeAvailability = product.getAvailabilities()
                .stream().filter(a -> a.getRegion().equals(locale.getCountry())).findFirst();
        var productAvailabilities = new HashSet<ProductAvailability>();
        defaultAvailability.ifPresent(productAvailabilities::add);
        localeAvailability.ifPresent(productAvailabilities::add);
        product.setAvailabilities(productAvailabilities);
    }
}
