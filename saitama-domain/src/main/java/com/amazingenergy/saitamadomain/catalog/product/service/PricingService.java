package com.amazingenergy.saitamadomain.catalog.product.service;

import com.amazingenergy.core.Notification;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.domain.attribute.ProductAttribute;
import com.amazingenergy.saitamadomain.catalog.product.domain.price.FinalPrice;
import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Currency;
import com.amazingenergy.saitamadomain.seedwork.exception.ServiceException;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

public interface PricingService {
    /**
     * Calculates the FinalPrice of a Product taking into account
     * all defined prices and possible rebates
     */
    Pair<FinalPrice, Notification> calculateProductPrice(Product product);

    /**
     * Calculates the FinalPrice of a Product taking into account
     * all defined prices and possible rebates. It also applies other calculation
     * based on the customer
     */
    Pair<FinalPrice, Notification> calculateProductPrice(Product product, Customer customer);

    /**
     * Calculates the FinalPrice of a Product taking into account
     * all defined prices and possible rebates. This method should be used to calculate
     * any additional prices based on the default attributes or based on the user selected attributes.
     */
    Pair<FinalPrice, Notification> calculateProductPrice(Product product, List<ProductAttribute> attributes);

    /**
     * Calculates the FinalPrice of a Product taking into account
     * all defined prices and possible rebates. This method should be used to calculate
     * any additional prices based on the default attributes or based on the user selected attributes.
     * It also applies other calculation based on the customer
     */
    Pair<FinalPrice, Notification> calculateProductPrice(Product product, List<ProductAttribute> attributes, Customer customer);

    /**
     * Method to be used to print a displayable formatted amount to the end user
     */
    String getDisplayAmount(BigDecimal amount, MerchantStore store) throws ServiceException;

    /**
     * Method to be used when building an amount formatted with the appropriate currency
     */
    String getDisplayAmount(BigDecimal amount, Locale locale, Currency currency, MerchantStore store) throws ServiceException;

    /**
     * Converts a String amount to BigDecimal
     * Takes care of String amount validation
     */
    BigDecimal getAmount(String amount) throws ServiceException;

    /**
     * String format of the money amount without currency symbol
     */
    String getStringAmount(BigDecimal amount, MerchantStore store) throws ServiceException;

    /**
     * Method for calculating subTotal
     */
    BigDecimal calculatePriceQuantity(BigDecimal price, int quantity);
}
