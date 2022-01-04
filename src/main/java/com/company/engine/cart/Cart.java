package com.company.engine.cart;

import java.util.HashSet;

public class Cart extends HashSet<CartItem>{
    /**
     method : getCartItem
     param : sku
     This method gets specific CartItem as per sku from Cart Set
     */
    public CartItem getCartItem(char sku){
        try {
            return this.stream().filter(item -> item.getSku() == sku).findFirst().get();
        }catch (Exception exception){
            return null;
        }
    }
}
