package com.example.farmerassist07.Modal;

public class BuyerRequest {

    private User postedBy;
    private String productName;
    private int quantity;
    private float unitPrice;

    public BuyerRequest() {
    }

    public BuyerRequest(User postedBy, String productName, int quntity, float unitPrice) {
        this.postedBy = postedBy;
        this.productName = productName;
        this.quantity = quntity;
        this.unitPrice = unitPrice;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "BuyerRequest{" +
                "postedBy=" + postedBy +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
