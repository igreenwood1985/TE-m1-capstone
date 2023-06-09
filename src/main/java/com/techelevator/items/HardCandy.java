package com.techelevator.items;

public class HardCandy extends CandyStoreItem {
    public HardCandy (String iD, String name, double price, boolean isIndividuallyWrapped, String type){
        super(iD, name, price, isIndividuallyWrapped, type);
    }
    public HardCandy(HardCandy hardCandy){
        super(hardCandy);
    }

}
