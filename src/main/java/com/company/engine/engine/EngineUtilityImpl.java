package com.company.engine.engine;

import com.company.engine.promotion.PromoType;
import com.company.engine.promotion.Promotion;
import com.company.engine.promotion.PromotionFactory;
import com.company.engine.sku.Sku;

import java.util.HashMap;
import java.util.Map;

public class EngineUtilityImpl implements EngineUtility{
    Map<Character, Sku> skuList  = new HashMap<>();
    Map<Character, Promotion> activePromotionMap = new HashMap<>();

    /**
     * Method: setup()
     * Returns : void
     * Method setups the promotion engine application with default values
     * Stubs SKU Unit for A, B, C, D and its cost
     * Creates promotion and maps it to respective sku
     */
    @Override
    public void setup() {

    }

    @Override
    public boolean addSku(Sku sku) {
        return skuList.put(sku.getSkuId(),sku) != null ?  true :  false;
    }

    @Override
    public Map<Character, Sku> getSkuList() {
        return skuList;
    }

    @Override
    public boolean mapSkuToPromotion(char sku, Promotion promotion) {
        if(!activePromotionMap.containsKey(sku)) {
            activePromotionMap.put(sku, promotion);
            if(promotion.getPromoType() == PromoType.COMBINED)
                for (char s: promotion.getUnitsInCombination())
                    if(s != sku) activePromotionMap.put(s, promotion);
            return true;
        }
        return false;
    }

    @Override
    public Map<Character, Promotion> getActivePromotionMap() {
        return activePromotionMap;
    }

    @Override
    public Promotion createPromotion(PromoType promoType, int quantity, int unitPrice, char[] unitsInCombination) {
        return PromotionFactory.createPromotion(promoType,quantity,unitPrice,unitsInCombination);
    }
}
