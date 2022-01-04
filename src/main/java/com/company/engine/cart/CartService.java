package com.company.engine.cart;

import com.company.engine.sku.Sku;

public interface CartService {
    boolean addToCart(Sku sku, Integer quantity);
    Cart getMyCart();
}
