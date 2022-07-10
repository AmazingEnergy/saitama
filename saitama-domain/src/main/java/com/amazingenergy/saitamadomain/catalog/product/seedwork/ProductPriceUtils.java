package com.amazingenergy.saitamadomain.catalog.product.seedwork;

import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.domain.attribute.ProductAttribute;
import com.amazingenergy.saitamadomain.catalog.product.domain.price.FinalPrice;
import com.amazingenergy.saitamadomain.seedwork.constant.Constants;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.order.domain.orderproduct.OrderProduct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.CurrencyValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class ProductPriceUtils {
    private final static char DECIMAL_COUNT = '2';
    private final static char DECIMAL_POINT = '.';
    private final static char THOUSAND_POINT = ',';

    /**
     * This method calculates the final price taking into account
     * all attributes included having a specified default attribute with an attribute price gt 0
     * in the product object. The calculation is based
     * on the default price.
     * Attributes may be null
     */
    public Optional<FinalPrice> getFinalProductPrice(Product product, List<ProductAttribute> attributes) {
        var finalPriceOpt = product.calculateFinalPrice();
        if (finalPriceOpt.isEmpty())
            return finalPriceOpt;

        var finalPrice = finalPriceOpt.get();
        var attributePrice = BigDecimal.ZERO;
        if (attributes != null && attributes.size() > 0)
            attributePrice = attributes.stream().filter(a -> a.getPrice() != null && a.getPrice().doubleValue() > 0)
                    .map(ProductAttribute::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        finalPrice.addAdvance(attributePrice);
        return Optional.of(finalPrice);
    }

    /**
     * This is the final price calculated from all configured prices
     * and all possibles discounts. This price does not calculate the attributes
     * or other prices than the default one
     */
    public Optional<FinalPrice> getFinalPrice(Product product) {
        var finalPriceOpt = product.calculateFinalPrice();
        if (finalPriceOpt.isEmpty())
            return finalPriceOpt;

        var finalPrice = finalPriceOpt.get();
        var attributePrice = product.getSumOfAttributePrices();
        finalPrice.addAdvance(attributePrice);
        finalPrice.setStringPrice(this.getStringAmount(finalPrice.getFinalPrice()));
        return Optional.of(finalPrice);
    }

    /**
     * This is the format that will be displayed
     * in the admin input text fields when editing
     * an entity having a BigDecimal to be displayed
     * as a raw amount 1,299.99
     * The admin user will also be force to input
     * the amount using that format
     */
    public String getAdminFormattedAmount(MerchantStore store, BigDecimal amount) {
        return getStringAmount(amount);
    }

    public String getStringAmount(BigDecimal amount) {
        if (amount == null)
            return "";

        NumberFormat nf = NumberFormat.getInstance(Constants.DEFAULT_LOCALE);
        nf.setMaximumFractionDigits(Integer.parseInt(Character.toString(DECIMAL_COUNT)));
        nf.setMinimumFractionDigits(Integer.parseInt(Character.toString(DECIMAL_COUNT)));
        return nf.format(amount);
    }

    /**
     * This method has to be used to format store front amounts
     * It will display national format amount ex:
     * $1,345.99
     * Rs.1.345.99
     * or international format
     * USD1,345.79
     * INR1,345.79
     */
    public String getStoreFormattedAmountWithCurrency(MerchantStore store, BigDecimal amount) throws Exception {
        if (amount == null) {
            return "";
        }

        Currency currency = Constants.DEFAULT_CURRENCY;
        Locale locale = Constants.DEFAULT_LOCALE;

        try {
            currency = store.getCurrency().getCurrency();
            locale = new Locale(store.getDefaultLanguage().getCode(), store.getCountry().getIsoCode());
        } catch (Exception e) {
            log.error("Cannot create currency or locale instance for store {}", store.getCode());
        }

        NumberFormat currencyInstance = store.isCurrencyFormatNational()
                ? NumberFormat.getCurrencyInstance(locale)//national
                : NumberFormat.getCurrencyInstance();//international
        currencyInstance.setCurrency(currency);
        return currencyInstance.format(amount.doubleValue());
    }


    public String getFormattedAmountWithCurrency(Locale locale, com.amazingenergy.saitamadomain.reference.domain.Currency currency, BigDecimal amount) throws Exception {
        if (amount == null) {
            return "";
        }

        Currency curr = currency.getCurrency();
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(locale);
        currencyInstance.setCurrency(curr);
        return currencyInstance.format(amount.doubleValue());
    }

    /**
     * This method will return the required formated amount
     * with the appropriate currency
     */
    public String getAdminFormattedAmountWithCurrency(MerchantStore store, BigDecimal amount) {
        if (amount == null) {
            return "";
        }

        Currency currency = store.getCurrency().getCurrency();
        var nf = NumberFormat.getInstance(Constants.DEFAULT_LOCALE);
        nf.setMaximumFractionDigits(Integer.parseInt(Character.toString(DECIMAL_COUNT)));
        nf.setMinimumFractionDigits(Integer.parseInt(Character.toString(DECIMAL_COUNT)));
        nf.setCurrency(currency);
        return nf.format(amount);
    }

    /**
     * Returns a formatted amount using Shopizer Currency
     * requires internal java.util.Currency populated
     */
    public String getFormattedAmountWithCurrency(com.amazingenergy.saitamadomain.reference.domain.Currency currency, BigDecimal amount) {
        if (amount == null) {
            return "";
        }

        Validate.notNull(currency.getCurrency(), "Currency must be populated with java.util.Currency");

        Currency curr = currency.getCurrency();
        var nf = NumberFormat.getInstance(Constants.DEFAULT_LOCALE);
        nf.setMaximumFractionDigits(Integer.parseInt(Character.toString(DECIMAL_COUNT)));
        nf.setMinimumFractionDigits(Integer.parseInt(Character.toString(DECIMAL_COUNT)));
        nf.setCurrency(curr);
        return nf.format(amount);
    }

    /**
     * This amount will be displayed to the end user
     */
    public String getFormattedAmountWithCurrency(MerchantStore store, BigDecimal amount, Locale locale) {
        Currency currency = store.getCurrency().getCurrency();
        var nf = NumberFormat.getInstance(locale);
        nf.setCurrency(currency);
        nf.setMaximumFractionDigits(Integer.parseInt(Character.toString(DECIMAL_COUNT)));
        nf.setMinimumFractionDigits(Integer.parseInt(Character.toString(DECIMAL_COUNT)));
        return nf.format(amount);
    }

    /**
     * Transformation of an amount of money submitted by the admin
     * user to be inserted as a BigDecimal in the database
     */
    public BigDecimal getAmount(String amount) throws Exception {

        // validations
        /*
         * 1) remove decimal and thousand
         *
         * String.replaceAll(decimalPoint, ""); String.replaceAll(thousandPoint,
         * "");
         *
         * Should be able to parse to Integer
         */
        StringBuilder newAmount = new StringBuilder();
        for (int i = 0; i < amount.length(); i++) {
            if (amount.charAt(i) != DECIMAL_POINT
                    && amount.charAt(i) != THOUSAND_POINT) {
                newAmount.append(amount.charAt(i));
            }
        }

        try {
            Integer.parseInt(newAmount.toString());
        } catch (Exception e) {
            throw new Exception("Cannot parse " + amount);
        }

        if (!amount.contains(Character.toString(DECIMAL_POINT))
                && !amount.contains(Character.toString(THOUSAND_POINT))
                && !amount.contains(" ")) {

            if (matchPositiveInteger(amount)) {
                BigDecimalValidator validator = CurrencyValidator.getInstance();
                BigDecimal bdamount = validator.validate(amount, Locale.US);
                if (bdamount == null) {
                    throw new Exception("Cannot parse " + amount);
                } else {
                    return bdamount;
                }
            } else {
                throw new Exception("Not a positive integer "
                        + amount);
            }

        } else {
            // should not go this path in this current release
            StringBuilder pat = new StringBuilder();

            if (!StringUtils.isBlank(Character.toString(THOUSAND_POINT))) {
                pat.append("\\d{1,3}(" + THOUSAND_POINT + "?\\d{3})*");
            }

            pat.append("(\\" + DECIMAL_POINT + "\\d{1," + DECIMAL_COUNT + "})");

            Pattern pattern = Pattern.compile(pat.toString());
            Matcher matcher = pattern.matcher(amount);

            if (matcher.matches()) {

                Locale locale = Constants.DEFAULT_LOCALE;
                // validate amount using old test case
                if (DECIMAL_POINT == ',') {
                    locale = Locale.GERMAN;
                }

                BigDecimalValidator validator = CurrencyValidator.getInstance();

                return validator.validate(amount, locale);
            } else {
                throw new Exception("Cannot parse " + amount);
            }
        }

    }

    public BigDecimal getOrderProductTotalPrice(MerchantStore store, OrderProduct orderProduct) {
        BigDecimal finalPrice = orderProduct.getOneTimeCharge();
        finalPrice = finalPrice.multiply(new BigDecimal(orderProduct.getProductQuantity()));
        return finalPrice;
    }

    private boolean matchPositiveInteger(String amount) {
        Pattern pattern = Pattern.compile("^[+]?\\d*$");
        Matcher matcher = pattern.matcher(amount);
        return matcher.matches();
    }
}
