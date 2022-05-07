package com.amazingenergy.saitamadomain.shoppingcart.repository;

import com.amazingenergy.core.repository.AggregateRootRepository;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.shipping.domain.ShippingProduct;
import com.amazingenergy.saitamadomain.shoppingcart.domain.ShoppingCart;
import com.amazingenergy.saitamadomain.shoppingcart.domain.ShoppingCartItem;

import java.util.List;
import java.util.UUID;

public interface ShoppingCartRepository extends AggregateRootRepository<UUID, ShoppingCart> {
    ShoppingCart getShoppingCart(Customer customer);

    void saveOrUpdate(ShoppingCart shoppingCart);

    ShoppingCart getById(Long id, MerchantStore store);

    ShoppingCart getByCode(String code, MerchantStore store);

    //ShoppingCart getByCustomer(Customer customer);

    /**
     * Creates a list of ShippingProduct based on the ShoppingCart if items are
     * virtual return list will be null
     */
    List<ShippingProduct> createShippingProduct(ShoppingCart cart);

    /**
     * Populates a ShoppingCartItem from a Product and attributes if any
     */
    ShoppingCartItem populateShoppingCartItem(Product product);

    void deleteCart(ShoppingCart cart);

    void removeShoppingCart(ShoppingCart cart);

    ShoppingCart mergeShoppingCarts(final ShoppingCart userShoppingCart,
                                    final ShoppingCart sessionCart, final MerchantStore store) throws Exception;

    /**
     * Removes a shopping cart item
     */
    void deleteShoppingCartItem(Long id);
}
