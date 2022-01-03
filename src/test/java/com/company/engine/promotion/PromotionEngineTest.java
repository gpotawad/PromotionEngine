package com.company.engine.promotion;

import com.company.engine.cart.Cart;
import com.company.engine.cart.CartItem;
import com.company.engine.sku.Sku;
import org.junit.*;
import java.util.HashMap;
import java.util.Map;

public class PromotionEngineTest {
    Map<Character, Promotion> activePromotionMap = null;
    PromotionService promotionService = null;
    Sku a = null,b = null,c = null,d = null;

    @Before
    public void setUp() throws Exception {
        activePromotionMap = new HashMap<>();
        a = new Sku('A',50);
        b = new Sku('B',30);
        c = new Sku('C',20);
        d = new Sku('D',15);
        Promotion promotion = PromotionFactory.createPromotion(PromoType.N_ITEM,3,100, null);
        activePromotionMap.put(a.getSkuId(),promotion);
        promotion = PromotionFactory.createPromotion(PromoType.N_ITEM,2,45, null);
        activePromotionMap.put(b.getSkuId(),promotion);
        promotion = PromotionFactory.createPromotion(PromoType.COMBINED,1,30, new char[]{'D'});
        activePromotionMap.put(c.getSkuId(),promotion);
    }

    @After
    public void tearDown() throws Exception {
        promotionService = null;
        a = null; b = null; c = null; d = null;
        activePromotionMap = null;
    }

    @Test
    public void whenThreeAExistsTotalAfterPromotionIs130() {
        int cartPrice = promotionService.calculatePromotionTotal(new CartItem(a.getSkuId(),3,a.getUnitPrice()), activePromotionMap.get(a.getSkuId()));
        Assert.assertEquals(130,cartPrice);
    }

    @Test
    public void whenTwoBExistsTotalAfterPromotionIs45() {
        int cartPrice = promotionService.calculatePromotionTotal(new CartItem(b.getSkuId(),2,b.getUnitPrice()), activePromotionMap.get(b.getSkuId()));
        Assert.assertEquals(45,cartPrice);
    }

    @Test
    public void whenCAndDExistsCombinedTotalAfterPromotionIs30() {
        Cart cart = new Cart();
        cart.add(new CartItem(c.getSkuId(),1,c.getUnitPrice()));
        cart.add(new CartItem(d.getSkuId(),1,d.getUnitPrice()));
        int cartPrice = promotionService.calculatePromotionTotal(new CartItem(c.getSkuId(),1,c.getUnitPrice()),activePromotionMap.get(c.getSkuId()));
        Assert.assertEquals(30,cartPrice);
    }

    @Test
    public void whenNoPromotionExists() {
        activePromotionMap.remove(a.getSkuId());
        int cartPrice = promotionService.calculatePromotionTotal(new CartItem(a.getSkuId(),3,a.getUnitPrice()), activePromotionMap.get(a.getSkuId()));
        Assert.assertEquals(150,cartPrice);
    }
}