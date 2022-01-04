package com.company.engine.cart;

import com.company.engine.engine.EngineUtilityImpl;
import com.company.engine.promotion.PromoType;
import com.company.engine.promotion.PromotionFactory;
import com.company.engine.sku.Sku;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartServiceImplTest {
    CartService cartService = null;
    Sku a;

    @Before
    public void setUp() throws Exception {
        cartService = new CartServiceImpl();
        a = new Sku('A',50);
    }

    @After
    public void tearDown() throws Exception {
        cartService = null;
        a = null;
    }

    @Test
    public void addToCart_whenAddedThreeSkuOfAToCart_thenCartHasOneItemWithThreeA() {
        cartService.addToCart(a,3);
        Assert.assertEquals(1, cartService.getMyCart().size());
        Assert.assertEquals(3, cartService.getMyCart().getCartItem(a.getSkuId()).getQuantity());
    }

    @Test
    public void addToCart_whenAddedThreeSkuOfAandTwoSkuOfAagainToCart_thenCartHasOneItemWithFiveA() {
        cartService.addToCart(a,3);
        cartService.addToCart(a,2);
        Assert.assertEquals(1, cartService.getMyCart().size());
        Assert.assertEquals(5, cartService.getMyCart().getCartItem(a.getSkuId()).getQuantity());
    }
}