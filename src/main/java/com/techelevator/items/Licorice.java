package com.techelevator.items;

public class Licorice extends CandyStoreItem {
    public Licorice(String iD, String name, double price, boolean isIndividuallyWrapped, String type){
        super(iD, name, price, isIndividuallyWrapped, type);
    }

    public Licorice(Licorice licorice){
        super(licorice);
    }
}
