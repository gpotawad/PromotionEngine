package com.company.engine.promotion;
import com.company.engine.cart.Cart;
import com.company.engine.cart.CartItem;

public class PromotionService {
    public int computeItemPromotionTotal(CartItem cartItem, Promotion promotion, Cart cart){
        if(promotion != null) return promotion.computePromotionTotal(cartItem,cart);
        if(cartItem != null) return cartItem.getTotalPrice();
        return  0;
    }
}
