package com.techelevator.items;

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

    public CandyStoreItem(String id, String name, double price, boolean isIndividuallyWrapped ){
        // Defaults quantity to 100.
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = 100;
        this.isIndividuallyWrapped = isIndividuallyWrapped;
    }
    public CandyStoreItem(String id, String name, double price, int quantity, boolean isIndividuallyWrapped ) throws IllegalArgumentException {
        if (quantity > 100 || quantity < 0){
            throw new IllegalArgumentException("Invalid quantity. Value must be between 0 and 100.");
        } else {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.isIndividuallyWrapped = isIndividuallyWrapped;
        }
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isIndividuallyWrapped() {
        return isIndividuallyWrapped;
    }

}
