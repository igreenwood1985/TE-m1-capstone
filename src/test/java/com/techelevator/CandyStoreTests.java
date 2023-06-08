package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CandyStoreTests {

    private CandyStore target;

    @Before
    public void setup() {
        target = new CandyStore();
    }

    @Test
    public void throws_exception_if_string_is_invalid(){

        boolean threwException = false;

        try {
            target.parseCandyStoreItemFromInventoryString(null);
        } catch (IllegalArgumentException e){
            threwException = true;
        } finally {
            Assert.assertTrue("Attempted to parse invalid string.", threwException);
        }
    }
}
