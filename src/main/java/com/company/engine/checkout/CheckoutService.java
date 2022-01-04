package com.company.engine.checkout;

import com.company.engine.cart.Cart;
import com.company.engine.promotion.Promotion;

import java.util.Map;

public interface CheckoutService {
    int checkoutCartWithPromo(Cart cart, Map<Character, Promotion> activePromotionMap);
}
