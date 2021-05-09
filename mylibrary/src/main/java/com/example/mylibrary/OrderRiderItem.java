package com.example.mylibrary;

public class OrderRiderItem {
    private String keyRestaurant;
    private String keyfooddelivery;
    private String addrfooddelivery;
    private String addrRestaurant;
    private String totPrice;
    private Long time;

    public OrderRiderItem() {

    }

    public OrderRiderItem(String keyRestaurant, String keyfooddelivery, String addrfooddelivery, String addrRestaurant, Long time, String totPrice) {
        this.keyRestaurant = keyRestaurant;
        this.keyfooddelivery = keyfooddelivery;
        this.addrfooddelivery = addrfooddelivery;
        this.addrRestaurant = addrRestaurant;
        this.time = time;
        this.totPrice = totPrice;
    }

    public String getKeyRestaurant() {
        return keyRestaurant;
    }

    public String getKeyfooddelivery() {
        return keyfooddelivery;
    }

    public String getAddrfooddelivery() {
        return addrfooddelivery;
    }

    public String getAddrRestaurant() {
        return addrRestaurant;
    }

    public Long getTime() {
        return time;
    }

    public String getTotPrice() {
        return totPrice;
    }
}
