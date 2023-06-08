package com.techelevator.items;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HardCandyTests {
    private HardCandy target;
    @Before
    public void setup() {
        this.target = new HardCandy("ValidTestHardCandy", 10.0, true);
    }

    @Test
    public void constructor_values_are_preserved() {
        String expectedName = "ValidTestHardCandy";
        double expectedPrice = 10.0;
        boolean expectedIsWrapped = true;
        Assert.assertEquals("Name was changed in construction process.", expectedName, target.getName());
        Assert.assertEquals("Price was changed in construction process.", expectedPrice, target.getPrice(), .009);
        Assert.assertEquals("isWrapped was changed in construction process.", expectedIsWrapped, target.isIndividuallyWrapped());
    }

}
