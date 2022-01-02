package com.company.engine.promotion;

public class PromotionFactory {

    public static Promotion createPromotion(PromoType promoType){
        switch (promoType){
            case N_ITEM:
                return new PromotionBulkItem();
            case COMBINED:
                return new PromotionCombination();
            case PERCENT_OFF:
                return new PromotionPercentage();
            default:
                return null;
        }
    }
}
