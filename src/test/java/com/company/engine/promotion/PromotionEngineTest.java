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
        promotionService = new PromotionService();
        a = new Sku('A',50);
        b = new Sku('B',30);
        c = new Sku('C',20);
        d = new Sku('D',15);
        Promotion promotion = PromotionFactory.createPromotion(PromoType.N_ITEM,3,130, null);
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

    /**
     * Scenario : Calculate total cartItem amount after promotion
     * Given : sku 'A' Cart Item and Promotion applied as 3 A's for 130
     * when : Cart has 3 Items of sku 'A'
     * then : total cost after applying promotion is 130
     */
    @Test
    public void whenThreeAExistsTotalAfterPromotionIs130() {
        int cartPrice = promotionService.computeItemPromotionTotal(new CartItem(a.getSkuId(),3,a.getUnitPrice()), activePromotionMap.get(a.getSkuId()));
        Assert.assertEquals(130,cartPrice);
    }


    /**
     * Scenario : Calculate total cartItem amount after promotion
     * Given : sku 'B' Cart Item and Promotion applied as 2 B's for 45
     * when : Cart has 2 Items of sku 'B'
     * then : total cost after applying promotion is 45
     */
    @Test
    public void whenTwoBExistsTotalAfterPromotionIs45() {
        int cartPrice = promotionService.computeItemPromotionTotal(new CartItem(b.getSkuId(),2,b.getUnitPrice()), activePromotionMap.get(b.getSkuId()));
        Assert.assertEquals(45,cartPrice);
    }

    /**
     * Scenario : Calculate total cartItem amount after promotion
     * Given : sku 'C' and 'D' Cart Item and Promotion applied as combination of C and D is for 30
     * when : Cart has 1 Item of sku 'C' and 1 Item of sku 'D'
     * then : total cost after applying promotion is 30
     */
    @Test
    public void whenCAndDExistsCombinedTotalAfterPromotionIs30() {
        Cart cart = new Cart();
        cart.add(new CartItem(c.getSkuId(),1,c.getUnitPrice()));
        cart.add(new CartItem(d.getSkuId(),1,d.getUnitPrice()));
        int cartPrice = promotionService.computeItemPromotionTotal(new CartItem(c.getSkuId(),1,c.getUnitPrice()),activePromotionMap.get(c.getSkuId()));
        Assert.assertEquals(30,cartPrice);
    }

    /**
     * Scenario : Calculate total cartItem amount after promotion
     * Given : sku 'A' cart item without any promotion applied
     * when : Cart has 3 'A' Items and no promotion applied
     * then : total cost after applying promotion is 150
     */
    @Test
    public void whenNoPromotionExists() {
        activePromotionMap.remove(a.getSkuId());
        int cartPrice = promotionService.computeItemPromotionTotal(new CartItem(a.getSkuId(),3,a.getUnitPrice()), activePromotionMap.get(a.getSkuId()));
        Assert.assertEquals(150,cartPrice);
    }
}