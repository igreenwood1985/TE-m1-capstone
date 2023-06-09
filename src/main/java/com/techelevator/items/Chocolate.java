package com.techelevator.items;

public class Chocolate extends CandyStoreItem {
    public Chocolate(String iD, String name, double price, boolean isIndividuallyWrapped, String type) {
        super(iD, name, price, isIndividuallyWrapped, type);
    }

    public Chocolate(Chocolate chocolate) {
        super(chocolate);
    }
}
