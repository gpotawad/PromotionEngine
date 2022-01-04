package com.company.engine.engine;

import com.company.engine.promotion.PromoType;
import com.company.engine.promotion.Promotion;
import com.company.engine.promotion.PromotionFactory;
import com.company.engine.sku.Sku;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EngineUtilityTest {
    EngineUtility engineUtility = null;
    Promotion promotion1 = null, promotion2 = null;
    Sku a = null;
    @Before
    public void setUp() throws Exception {
        engineUtility = new EngineUtilityImpl();
        a = new Sku('A',50);
        promotion1 = PromotionFactory.createPromotion(PromoType.N_ITEM,3,130, null);
        promotion2 = PromotionFactory.createPromotion(PromoType.N_ITEM,2,80, null);
    }

    @After
    public void tearDown() throws Exception {
        engineUtility = null;
        a = null;
        promotion1 = null;
        promotion2 = null;
    }

    /**
     * Scenario : Promotion is mapping to Sku
     * Given : Sku and Promotion Object
     * when : Promotion 1 is mapped to Sku A
     * then : returns true i.e. mapping is successful
     */
    @Test
    public void mapSkuToPromotion_whenPromoMapsOnceToSKU_returnsTrue() {
        boolean mapped = engineUtility.mapSkuToPromotion(a.getSkuId(), promotion1);
        Assert.assertEquals(true,mapped);
    }

    /**
     * Scenario : If sku has 1 active promotion mapped then 2nd can't be mapped/override without removing the mapping
     * Given : Sku, Promotion1 and Promotion2 Object
     * when : Promotion 1 is mapped to Sku A and without removing trying to map Promotion 2
     * then : returns false i.e. mapping is unsuccessful
     */
    @Test
    public void mapSkuToPromotion_whenPromoMaps2ndTimeToSKU_returnsFalse() {
        engineUtility.mapSkuToPromotion(a.getSkuId(), promotion1);
        boolean mapped = engineUtility.mapSkuToPromotion(a.getSkuId(), promotion2);
        Assert.assertEquals(false,mapped);
    }

    /**
     * Scenario : If sku has 1 active promotion mapped then 2nd can't be mapped/override without removing the mapping
     * Given : Sku, Promotion1 and Promotion2 Object
     * when : Promotion 1 is mapped to Sku A and after removing Promotion 1, trying to map Promotion 2
     * then : returns true i.e. mapping is successful
     */
    @Test
    public void mapSkuToPromotion_whenPromoMaps2ndTimeAfterRemovingExistingPromoToSKU_returnsTrue() {
        engineUtility.mapSkuToPromotion(a.getSkuId(), promotion1);
        engineUtility.getActivePromotionMap().remove(a.getSkuId());
        boolean mapped = engineUtility.mapSkuToPromotion(a.getSkuId(), promotion2);
        Assert.assertEquals(true,mapped);
    }

    @Test
    public void setup_whenSetup_thenSkuListAndActivePromotionMapGetsStubbed(){
        engineUtility.setup();
        Assert.assertEquals(4,engineUtility.getActivePromotionMap().size());
        Assert.assertEquals(4,engineUtility.getSkuList().size());
    }
}