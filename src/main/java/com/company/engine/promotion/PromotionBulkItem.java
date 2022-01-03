package com.company.engine.promotion;

import com.company.engine.cart.Cart;
import com.company.engine.cart.CartItem;

public class PromotionBulkItem extends Promotion {

    public PromotionBulkItem(PromoType promoType, int quantity, int promoPrice, char[] unitsInCombination) {
        super(promoType, quantity, promoPrice, unitsInCombination);
    }

    @Override
    public int computePromotionTotal(CartItem cartItem) {
    }
}
