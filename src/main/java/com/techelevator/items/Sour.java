package com.techelevator.items;

public class Sour extends CandyStoreItem {
    public Sour(String iD, String name, double price, boolean isIndividuallyWrapped, String type){
        super(iD, name, price, isIndividuallyWrapped, type);
    }
    public Sour(Sour sour) {
        super(sour);
    }
}
