package com.company.engine.promotion;

public class PromotionFactory {

    public static Promotion createPromotion(PromoType promoType, int quantity, int promoPrice, char[] unitsInCombination){
        switch (promoType){
            case N_ITEM:
                return new PromotionBulkItem(promoType, quantity, promoPrice, unitsInCombination);
            case COMBINED:
                return new PromotionCombination(promoType, quantity, promoPrice, unitsInCombination);
            case PERCENT_OFF:
                return new PromotionPercentage(promoType, quantity, promoPrice, unitsInCombination);
            default:
                return null;
        }
    }
}
