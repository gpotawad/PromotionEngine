package com.company.engine.sku;

public class Sku {
    private char skuId;
    private int unitPrice;

    public Sku(char skuId, int unitPrice) {
        setSkuId(skuId);
        setUnitPrice(unitPrice);
    }

    public char getSkuId() {
        return skuId;
    }

    public void setSkuId(char skuId) {
        this.skuId = skuId;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
}
