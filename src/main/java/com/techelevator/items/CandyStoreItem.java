package com.techelevator.items;

import com.techelevator.CandyStore;

/*
    This represents a single catering item in the system

    This is an abstract class that should be used as a superclass for the items.
 */
public abstract class CandyStoreItem {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private boolean isIndividuallyWrapped;
    private String type;

    public CandyStoreItem(String id, String name, double price, boolean isIndividuallyWrapped, String type ){
        // Defaults quantity to 100.
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = 100;
        this.isIndividuallyWrapped = isIndividuallyWrapped;
        this.type = type;
    }
    public CandyStoreItem(String id, String name, double price, int quantity, boolean isIndividuallyWrapped, String type ) throws IllegalArgumentException {
        if (quantity > 100 || quantity < 0){
            throw new IllegalArgumentException("Invalid quantity. Value must be between 0 and 100.");
        } else {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.isIndividuallyWrapped = isIndividuallyWrapped;
            this.type = type;
        }
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isIndividuallyWrapped() {
        return isIndividuallyWrapped;
    }

    public void sellItem(int amountSold) {
        this.quantity -= amountSold;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getType() {
        return type;
    }


    public CandyStoreItem(CandyStoreItem candyItem){
        this.id = candyItem.getId();
        this.name = candyItem.getName();
        this.isIndividuallyWrapped = candyItem.isIndividuallyWrapped();
        this.quantity = candyItem.getQuantity();
        this.price = candyItem.getPrice();
        this.type = candyItem.getType();
    }




    // Could override the toString method, but that seems... wrong.
    // Should do all our formatting with printf, or none of it.
//    @Override
//    public String toString(){
//        String wrapper = "";
//        String qty = "";
//
//        if(isIndividuallyWrapped){
//            wrapper = "Y";
//        } else {
//            wrapper = "N";
//        }
//        if(quantity == 0){
//            qty = "SOLD OUT";
//        } else {
//            qty =  this.price + "";
//        }
//
//        return this.id + " " + this.name + " " + wrapper + " " +
//    }


}
