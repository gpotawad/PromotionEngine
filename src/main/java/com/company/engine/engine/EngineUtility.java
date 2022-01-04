package com.company.engine.engine;

import com.company.engine.promotion.PromoType;
import com.company.engine.promotion.Promotion;
import com.company.engine.promotion.PromotionFactory;
import com.company.engine.sku.Sku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EngineUtility {
    public void setup();
    public boolean addSku(Sku sku);
    public Map<Character, Sku> getSkuList();
    public boolean mapSkuToPromotion(char sku, Promotion promotion);
    public Map<Character, Promotion> getActivePromotionMap();
    public Promotion createPromotion(PromoType promoType, int quantity, int unitPrice, char[] unitsInCombination);
}
