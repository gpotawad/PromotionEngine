package com.company.engine.cart;

import java.util.Objects;

public class CartItem {
    private char sku;
    private int quantity;
    private int unitPrice;
    private int totalPrice;

    public CartItem(char skuId, int quantity, int unitPrice) {
        setSku(skuId);
        setQuantity(quantity);
        setUnitPrice(unitPrice);
        setTotalPrice(quantity * unitPrice);
    }

    public char getSku() {
        return sku;
    }

    public void setSku(char sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        setTotalPrice(quantity * unitPrice);
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
        setTotalPrice(quantity * unitPrice);
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;
        CartItem cartItem = (CartItem) o;
        return getSku() == cartItem.getSku();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSku());
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "sku=" + sku +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
