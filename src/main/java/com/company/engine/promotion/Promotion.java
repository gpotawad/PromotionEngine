package com.company.engine.promotion;

import com.company.engine.cart.Cart;
import com.company.engine.cart.CartItem;

public abstract class  Promotion {
    private PromoType promoType;
    private char[] unitsInCombination;
    private int quantity;
    private int promoPrice;

    public Promotion(PromoType promoType, int quantity , int promoPrice, char[] unitsInCombination){
        setPromoPrice(promoPrice);
        setPromoType(promoType);
        setUnitsInCombination(unitsInCombination);
        setQuantity(quantity);
    }

    public abstract int computePromotionTotal(CartItem cartItem, Cart cart);

    public PromoType getPromoType() {
        return promoType;
    }

    public void setPromoType(PromoType promoType) {
        this.promoType = promoType;
    }

    public int getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(int promoPrice) {
        this.promoPrice = promoPrice;
    }

    public char[] getUnitsInCombination() {
        return unitsInCombination;
    }

    public void setUnitsInCombination(char[] unitsInCombination) {
        this.unitsInCombination = unitsInCombination;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "promoType=" + promoType +
                ", unitsInPromo=" + unitsInCombination +
                ", quantity=" + quantity +
                ", promoPrice=" + promoPrice +
                '}';
    }
}
