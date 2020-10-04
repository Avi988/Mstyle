package com.example.buyanything.models;

public class Order {
    private int id;
    private String itemName;
    private String name;
    private String contact;
    private String place;
    private int qty;
    private int size;
    private String paymentMethod;
    private double price;

    public Order() {
    }

    public Order(int id, String itemName, String name, String contact, String place, int qty, int size, String paymentMethod, double price) {
        this.id = id;
        this.itemName = itemName;
        this.name = name;
        this.contact = contact;
        this.place = place;
        this.qty = qty;
        this.size = size;
        this.paymentMethod = paymentMethod;
        this.price = price;
    }

    public Order(String itemName, String name, String contact, String place, int qty, int size, String paymentMethod, double price) {
        this.itemName = itemName;
        this.name = name;
        this.contact = contact;
        this.place = place;
        this.qty = qty;
        this.size = size;
        this.paymentMethod = paymentMethod;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
