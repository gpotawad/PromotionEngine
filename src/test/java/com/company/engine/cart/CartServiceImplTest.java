package com.company.engine.cart;

import com.company.engine.sku.Sku;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartServiceImplTest {
    CartService cartService = null;
    Sku a;

    @Before
    public void setUp()  {
        cartService = new CartServiceImpl();
        a = new Sku('A',50);
    }

    @After
    public void tearDown() {
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