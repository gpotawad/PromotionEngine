package com.company.engine.promotion;

import com.company.engine.cart.Cart;
import com.company.engine.cart.CartItem;

public class PromotionCombination extends Promotion {

    public PromotionCombination(PromoType promoType, int quantity, int promoPrice, char[] unitsInCombination) {
        super(promoType, quantity, promoPrice, unitsInCombination);
    }

    @Override
    public int computePromotionTotal(CartItem cartItem, Cart cart) {
        char[] promoUnitList = this.getUnitsInCombination();
        int quantity = cartItem.getQuantity();
        int sum = 0;
        if (promoUnitList.length > 1) {
            int minQuantityPromo = cart.getCartItem(promoUnitList[0]).getQuantity();
            int promoSKUsFound = 0;

            /* check if combination offer skus are in cart*/
            for (char skuInPromo : promoUnitList) {
                CartItem itemInPromo = cart.getCartItem(skuInPromo);
                if (itemInPromo== null) break;
                if(minQuantityPromo > itemInPromo.getQuantity()) minQuantityPromo = itemInPromo.getQuantity();
                promoSKUsFound++;
            }

            /* if combination skus are in cart then calculate offer price*/
            if (promoSKUsFound == promoUnitList.length) {
                sum += (cartItem.getQuantity() - minQuantityPromo) * cartItem.getUnitPrice();
                if(promoUnitList[promoSKUsFound-1] == cartItem.getSku())
                    sum += minQuantityPromo * this.getPromoPrice();
                return sum;
            }
        }
        sum = (quantity * cartItem.getUnitPrice());
        return sum;
    }
}
