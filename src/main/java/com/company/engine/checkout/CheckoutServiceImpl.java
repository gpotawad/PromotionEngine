package com.company.engine.checkout;

import com.company.engine.cart.Cart;
import com.company.engine.promotion.Promotion;
import com.company.engine.promotion.PromotionService;

import java.util.Map;

public class CheckoutServiceImpl implements CheckoutService {
    PromotionService promotionService = new PromotionService();

    @Override
    public int checkoutCartWithPromo(Cart cart, Map<Character, Promotion> activePromotionMap) {
        int cartTotalPrice = 0;
        for (var cartItem : cart) {
            char sku = cartItem.getSku();
            Promotion activePromotion = activePromotionMap.get(sku);
            int itemTotal = promotionService.computeItemPromotionTotal(cartItem, activePromotion, cart);
            cartItem.setTotalPrice(itemTotal);
            cartTotalPrice += cartItem.getTotalPrice();
        }
        return cartTotalPrice;
    }
}
