package com.company.engine.cart;

import com.company.engine.sku.Sku;

public class CartServiceImpl implements CartService {
    Cart myCart = new Cart();

    /**
     * method : addToCart
     * Param : Sku and Quantity
     * This method adds product and its quantity in Cart (Set<CartItem>)
     * if item is already present in the cart then adds the quantity
     */
    @Override
    public boolean addToCart(Sku sku, Integer quantity) {
        return false;
    }

    @Override
    public Cart getMyCart() {
        return myCart;
    }
}
