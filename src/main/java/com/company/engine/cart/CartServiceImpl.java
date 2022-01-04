package com.company.engine.cart;

import com.company.engine.sku.Sku;

public class CartServiceImpl implements CartService {
    Cart myCart = new Cart();

    /**
     * method : addToCart
     * Param : Sku and Quantity
     * This method adds product and its quantity in Cart (Set<CartItem>)
     * if item is already present in the cart then adds the quantity
     */
    @Override
    public boolean addToCart(Sku sku, Integer quantity) {
        CartItem item = myCart.getCartItem(sku.getSkuId());
        //checks if sku / item is already in cart
        if(item!=null){
            item.setQuantity(item.getQuantity()+quantity);
        }
        return myCart.add( new CartItem(sku.getSkuId(), quantity, sku.getUnitPrice()));
    }

    @Override
    public Cart getMyCart() {
        return myCart;
    }
}
