package com.company.engine.checkout;

import com.company.engine.cart.Cart;
import com.company.engine.cart.CartItem;
import com.company.engine.promotion.PromoType;
import com.company.engine.promotion.Promotion;
import com.company.engine.promotion.PromotionFactory;
import com.company.engine.promotion.PromotionService;
import com.company.engine.sku.Sku;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CheckoutServiceTest extends Object {
    Cart cart = null;
    Map<Character, Promotion> activePromotionMap = null;
    PromotionService promotionService = null;
    Sku a = null,b = null,c = null,d = null;
    CheckoutService checkoutService = null;
    @Before
    public void setUp() throws Exception {
        activePromotionMap = new HashMap<>();
        checkoutService = new CheckoutService();
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
        cart = null;
    }


    @Test
    public void whenOneAOneBOneCExistsTotalAfterPromotionIs100() {
        cart.add(new CartItem(a.getSkuId(),1, a.getUnitPrice()));
        cart.add(new CartItem(b.getSkuId(),1, b.getUnitPrice()));
        cart.add(new CartItem(c.getSkuId(),1, c.getUnitPrice()));
        int cartTotal = checkoutService.checkoutCartWithPromo(cart,activePromotionMap);
        Assert.assertEquals(100,cartTotal);
    }

    @Test
    public void whenFiveAFiveBOneCExistsTotalAfterPromotionIs370() {
        cart.add(new CartItem(a.getSkuId(),5, a.getUnitPrice()));
        cart.add(new CartItem(b.getSkuId(),5, b.getUnitPrice()));
        cart.add(new CartItem(c.getSkuId(),1, c.getUnitPrice()));
        int cartTotal = checkoutService.checkoutCartWithPromo(cart,activePromotionMap);
        Assert.assertEquals(100,cartTotal);
    }

    @Test
    public void whenThreeAFiveBOneCOneDExistsTotalAfterPromotionIs280() {
        cart.add(new CartItem(a.getSkuId(),3, a.getUnitPrice()));
        cart.add(new CartItem(b.getSkuId(),5, b.getUnitPrice()));
        cart.add(new CartItem(c.getSkuId(),1, c.getUnitPrice()));
        cart.add(new CartItem(d.getSkuId(),1, d.getUnitPrice()));
        int cartTotal = checkoutService.checkoutCartWithPromo(cart,activePromotionMap);
        Assert.assertEquals(100,cartTotal);
    }

    @Test
    public void testEmptyCart() {
        cart = new Cart();
        int cartTTotal = checkoutService.checkoutCartWithPromo(cart,activePromotionMap);
        Assert.assertEquals(100,cartTTotal);
    }
}