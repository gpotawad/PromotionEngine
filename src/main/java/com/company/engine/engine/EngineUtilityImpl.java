package com.company.engine.engine;

public class EngineUtilityImpl implements EngineUtility{
    @Override
    public void setup() {

    }


    @Override
    public boolean mapSkuToPromotion(char sku, Promotion promotion) {
        return false;
    }

    @Override
    public Map<Character, Promotion> getActivePromotionMap() {
        return activePromotionMap;
    }
}
