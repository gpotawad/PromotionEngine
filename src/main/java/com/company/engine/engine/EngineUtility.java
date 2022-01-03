package com.company.engine.engine;


import java.util.HashMap;
import java.util.Map;

public interface EngineUtility {
    Map<Character, Promotion> activePromotionMap = new HashMap<>();
    public void setup();
    public boolean mapSkuToPromotion(char sku, Promotion promotion);
    public Map<Character, Promotion> getActivePromotionMap();
}
