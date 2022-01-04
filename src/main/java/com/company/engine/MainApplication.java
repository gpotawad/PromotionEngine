package com.company.engine;

import com.company.engine.cart.CartService;
import com.company.engine.cart.CartServiceImpl;
import com.company.engine.checkout.CheckoutService;
import com.company.engine.checkout.CheckoutServiceImpl;
import com.company.engine.engine.EngineUtility;
import com.company.engine.engine.EngineUtilityImpl;
import com.company.engine.sku.Sku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class MainApplication {
    public static void main(String... args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CartService cartService = new CartServiceImpl();
        CheckoutService checkoutService = new CheckoutServiceImpl();
        EngineUtility engine = new EngineUtilityImpl();
        engine.setup();
        System.out.println("************ PROMOTION ENGINE **************");
        while(true){
            System.out.println("Select Product from the list.");
            engine.getSkuList().forEach((character, sku) -> System.out.print(character + " = " + sku.getUnitPrice() + "/- \t"));
            System.out.println("\nType 'X' to apply promotion to this cart");
            String sCharProduct = reader.readLine();
            if("X".equalsIgnoreCase(sCharProduct)) break;
            System.out.print("Enter Product quantity. \n");
            String quantityStr = reader.readLine();
            Integer quantity = 0;
            if(!quantityStr.isBlank()){
                quantity = Integer.parseInt(quantityStr);
            }
            Sku selectedSku = engine.getSkuList().get(sCharProduct.toUpperCase(Locale.ROOT).toCharArray()[0]);
            cartService.addToCart(selectedSku,quantity);
        }
        System.out.println( "Total cart price = " + checkoutService.checkoutCartWithPromo(cartService.getMyCart(),engine.getActivePromotionMap() ));
        System.out.println("Cart Items = " + cartService.getMyCart());
    }
}
