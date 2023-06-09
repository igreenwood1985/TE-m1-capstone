package com.techelevator.items;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LicoriceTests {

    private Licorice target;
    @Before
    public void setup() {
        this.target = new Licorice("LI","ValidTestLicorice", 10.0, false, "Licorice and Jellies");
    }

    @Test
    public void constructor_values_are_preserved() {
        String expectedName = "ValidTestLicorice";
        double expectedPrice = 10.0;
        boolean expectedIsWrapped = false;
        Assert.assertEquals("Name was changed in construction process.", expectedName, target.getName());
        Assert.assertEquals("Price was changed in construction process.", expectedPrice, target.getPrice(), .009);
        Assert.assertEquals("isWrapped was changed in construction process.", expectedIsWrapped, target.isIndividuallyWrapped());
    }
}
