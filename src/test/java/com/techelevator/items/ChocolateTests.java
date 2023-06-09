package com.techelevator.items;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChocolateTests {
    private Chocolate target;

    @Before
    public void setup() {
        this.target = new Chocolate("CH","ValidTestChocolate", 10.0, true, "Chocolate Confectionery");
    }

    @Test
    public void constructor_values_are_preserved() {
        String expectedName = "ValidTestChocolate";
        double expectedPrice = 10.0;
        boolean expectedIsWrapped = true;
        Assert.assertEquals("Name was changed in construction process.", expectedName, target.getName());
        Assert.assertEquals("Price was changed in construction process.", expectedPrice, target.getPrice(), .009);
        Assert.assertEquals("isWrapped was changed in construction process.", expectedIsWrapped, target.isIndividuallyWrapped());
    }
}
