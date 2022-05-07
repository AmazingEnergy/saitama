package com.amazingenergy.saitamadomain.catalog.product.service;

import com.amazingenergy.core.Notification;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.domain.attribute.ProductAttribute;
import com.amazingenergy.saitamadomain.catalog.product.domain.price.FinalPrice;
import com.amazingenergy.saitamadomain.catalog.product.seedwork.ProductPriceUtils;
import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Currency;
import com.amazingenergy.saitamadomain.seedwork.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class PricingServiceImpl implements PricingService {

    private final ProductPriceUtils priceUtils;

    public PricingServiceImpl(ProductPriceUtils priceUtils) {
        this.priceUtils = priceUtils;
    }

    @Override
    public Pair<FinalPrice, Notification> calculateProductPrice(Product product) {
        var notification = Notification.instance();
        var finalPriceOpt = priceUtils.getFinalPrice(product);
        if (finalPriceOpt.isEmpty())
            return ImmutablePair.of(null,
                    notification.addErrorCode("FAIL_TO_CALCULATE_FINAL_PRICE",
                            Product.FAIL_TO_CALCULATE_FINAL_PRICE, product.getId()));
        return ImmutablePair.of(finalPriceOpt.get(), notification);
    }

    @Override
    public Pair<FinalPrice, Notification> calculateProductPrice(Product product, Customer customer) {
        return calculateProductPrice(product);
    }

    @Override
    public Pair<FinalPrice, Notification> calculateProductPrice(Product product, List<ProductAttribute> attributes) {
        var notification = Notification.instance();
        var finalPriceOpt = priceUtils.getFinalProductPrice(product, attributes);
        if (finalPriceOpt.isEmpty())
            return ImmutablePair.of(null,
                    notification.addErrorCode("FAIL_TO_CALCULATE_FINAL_PRICE",
                            Product.FAIL_TO_CALCULATE_FINAL_PRICE, product.getId()));
        return ImmutablePair.of(finalPriceOpt.get(), notification);
    }

    @Override
    public Pair<FinalPrice, Notification> calculateProductPrice(Product product, List<ProductAttribute> attributes, Customer customer) {
        return calculateProductPrice(product, attributes);
    }

    @Override
    public String getDisplayAmount(BigDecimal amount, MerchantStore store) throws ServiceException {
        try {
            return priceUtils.getStoreFormattedAmountWithCurrency(store, amount);
        } catch (Exception e) {
            log.error("An error occurred when trying to format an amount {}", amount);
            throw new ServiceException(e);
        }
    }

    @Override
    public String getDisplayAmount(BigDecimal amount, Locale locale, Currency currency, MerchantStore store) throws ServiceException {
        try {
            return priceUtils.getFormattedAmountWithCurrency(locale, currency, amount);
        } catch (Exception e) {
            log.error("An error occurred when trying to format an amount {} using locale {} and currency {}", amount, locale, currency);
            throw new ServiceException(e);
        }
    }

    @Override
    public BigDecimal getAmount(String amount) throws ServiceException {
        try {
            return priceUtils.getAmount(amount);
        } catch (Exception e) {
            log.error("An error occurred when trying to format an amount {}", amount);
            throw new ServiceException(e);
        }
    }

    @Override
    public String getStringAmount(BigDecimal amount, MerchantStore store) throws ServiceException {
        try {
            return priceUtils.getAdminFormattedAmount(store, amount);
        } catch (Exception e) {
            log.error("An error occurred when trying to format an amount {}", amount);
            throw new ServiceException(e);
        }
    }

    @Override
    public BigDecimal calculatePriceQuantity(BigDecimal price, int quantity) {
        return price.multiply(new BigDecimal(quantity));
    }
}
