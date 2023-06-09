package com.techelevator;

import com.techelevator.items.CandyStoreItem;
import com.techelevator.items.Chocolate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CandyStoreTests {
    // Idk if this test class setup is legit, but whatever. No cases modify this data, so we don't need to create new data between cases.
    // for now, at least.
    private CandyStore target = new CandyStore();
    private String makeChocolate = "CH|C1|TestChoc|0.0|T";
    private String makeHardCandy = "HC|H1|TestHardCandy|0.0|F";
    private String makeSour = "SR|S1|TestSour|0.0|T";
    private String makeLicorice = "LI|L1|TestLicorice|0.0|F";
    private CandyStoreItem testChoc = target.parseCandyStoreItemFromInventoryString(makeChocolate);
    private CandyStoreItem testHC = target.parseCandyStoreItemFromInventoryString(makeHardCandy);
    private CandyStoreItem testSour = target.parseCandyStoreItemFromInventoryString(makeSour);
    private CandyStoreItem testLicorice = target.parseCandyStoreItemFromInventoryString(makeLicorice);
    private List<CandyStoreItem> itemList = new ArrayList<>();


    @Test
    public void parser_catches_invalid_strings(){

            try {
                CandyStoreItem nullTestItem =  target.parseCandyStoreItemFromInventoryString(null);
                Assert.assertEquals(nullTestItem, null);
            }
            catch (NullPointerException e) {
                System.out.println("it worked yay");
            }

            try {
                CandyStoreItem wrongPipeCountTestItem = target.parseCandyStoreItemFromInventoryString("1|2|3");
                Assert.assertEquals(wrongPipeCountTestItem, null);
            } catch (NullPointerException e){
                System.out.println("wooo this also worked");;
            }
            catch (IllegalArgumentException e){
                System.out.println("wahoo she works");
            }



    }
    @Test
    public void parser_creates_correct_items_based_on_codes() {

        Assert.assertEquals("Our chocolate isn't a chocolate.", "class com.techelevator.items.Chocolate", testChoc.getClass().toString());
        Assert.assertEquals("Our hard candy isn't a hard candy.", "class com.techelevator.items.HardCandy", testHC.getClass().toString());
        Assert.assertEquals("Our sour isn't a sour.", "class com.techelevator.items.Sour", testSour.getClass().toString());
        Assert.assertEquals("Our licorice isn't a licorice.", "class com.techelevator.items.Licorice", testLicorice.getClass().toString());
        }
    @Test
    public void parser_sets_item_data_correctly () {
        Assert.assertEquals("ID failed to parse.", "L1", testLicorice.getId());
        Assert.assertEquals("Name failed to parse.", "TestLicorice", testLicorice.getName());
        Assert.assertEquals("Price failed to parse.", 0.0, testLicorice.getPrice(), 0.009);
        Assert.assertFalse("isWrapped failed to parse", testLicorice.isIndividuallyWrapped());
        Assert.assertEquals("Quantity didn't default to 100", 100, testLicorice.getQuantity());
    }
    //  @Test
//    public void buildInventory_creates_valid_map() {
//        List<String> inventoryStringList = new ArrayList<String>();
//        inventoryStringList.add(makeChocolate);
//        inventoryStringList.add(makeLicorice);
//        inventoryStringList.add(makeHardCandy);
//        inventoryStringList.add(makeSour);
//
//        Map<String, CandyStoreItem> actualValue = ;
//
//        Map<String, CandyStoreItem> expectedValue = new HashMap<>();
//        expectedValue.put(testChoc.getId(), testChoc);
//        expectedValue.put(testHC.getId(), testHC);
//        expectedValue.put(testLicorice.getId(), testLicorice);
//        expectedValue.put(testSour.getId(), testSour);
//
//        Assert.assertEquals("Didn't set ID correctly.", expectedValue.get("L1").getId(), actualValue.get("L1").getId());
//        Assert.assertEquals("Didn't set name correctly.", expectedValue.get("C1").getName(), actualValue.get("C1").getName());
//        Assert.assertEquals("Didn't set quantity correctly.", expectedValue.get("H1").getQuantity(), actualValue.get("H1").getQuantity());
//        Assert.assertEquals("Didn't set price correctly.", expectedValue.get("S1").getPrice(), actualValue.get("S1").getPrice(), .009);
//    }
}
