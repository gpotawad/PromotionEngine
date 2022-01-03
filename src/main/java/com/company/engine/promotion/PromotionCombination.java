package com.company.engine.promotion;

import com.company.engine.cart.Cart;
import com.company.engine.cart.CartItem;

public class PromotionCombination extends Promotion {

    public PromotionCombination(PromoType promoType, int quantity, int promoPrice, char[] unitsInCombination) {
        super(promoType, quantity, promoPrice, unitsInCombination);
    }

    @Override
    public int calculatePromoPrice(CartItem cartItem) {
        return 0;
    }
}
