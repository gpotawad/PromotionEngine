package com.company.engine.engine;

import com.company.engine.promotion.PromoType;
import com.company.engine.promotion.Promotion;
import com.company.engine.sku.Sku;
import java.util.Map;

public interface EngineUtility {
    void setup();
    boolean addSku(Sku sku);
    Map<Character, Sku> getSkuList();
    boolean mapSkuToPromotion(char sku, Promotion promotion);
    Map<Character, Promotion> getActivePromotionMap();
    Promotion createPromotion(PromoType promoType, int quantity, int unitPrice, char[] unitsInCombination);
}
