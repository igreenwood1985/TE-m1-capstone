package com.techelevator;

import com.techelevator.items.CandyStoreItem;
import com.techelevator.items.Chocolate;
import com.techelevator.items.Sour;
import com.techelevator.view.Menu;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import static java.awt.SystemColor.menu;

public class CandyStoreTests {
    // Idk if this test class setup is legit, but whatever. No cases modify this data, so we don't need to create new data between cases.
    // for now, at least.
    private CandyStore target = new CandyStore();
    private String makeChocolate = "CH|C1|TestChoc|0.0|T";
    private String makeHardCandy = "HC|H1|TestHardCandy|0.0|F";
    private String makeSour = "SR|S1|TestSour|0.0|T";
    private String makeLicorice = "LI|L1|TestLicorice|0.0|F";
    private CandyStoreItem testChoc = new Chocolate("C1", "TestChoc", 1.0, true, "Chocolate Confectionery");
    private CandyStoreItem testHC = target.parseCandyStoreItemFromInventoryString(makeHardCandy);
    private CandyStoreItem testSour = target.parseCandyStoreItemFromInventoryString(makeSour);
    private CandyStoreItem testLicorice = target.parseCandyStoreItemFromInventoryString(makeLicorice);
    private List<CandyStoreItem> itemList = new ArrayList<>();

//    @Before
//    public void setup(){
//         this.target = new CandyStore();
//    }

    @Test
    public void parser_catches_invalid_strings(){

            try {
                CandyStoreItem nullTestItem =  target.parseCandyStoreItemFromInventoryString(null);
                Assert.assertEquals(nullTestItem, null);
            }
            catch (NullPointerException e) {
                // do nothing - it's fine.
                // this test fails if we don't throw an exception.
                // we should silently pass to the next case if we catch.
            }

            try {
                CandyStoreItem wrongPipeCountTestItem = target.parseCandyStoreItemFromInventoryString("1|2|3");
                Assert.assertEquals(wrongPipeCountTestItem, null);
            } catch (NullPointerException e){
                // do nothing - it's fine.
                // this test fails if we don't throw an exception.
                // we should silently pass to the next case if we catch.
            }
            catch (IllegalArgumentException e){
                // do nothing - it's fine.
                // this test fails if we don't throw an exception.
                // we should silently pass to the next case if we catch.
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

    @Test
    public void updateCart_handles_null_item_input(){
        boolean threwException = false;
        try {
            target.updateCart(null, 12);
        } catch (IllegalArgumentException e){
            threwException = true;
        }
        Assert.assertTrue("Didn't throw an exception for a null string.", threwException);
        // target and test store shouldn't be any different from each other; since this cart update oughta throw an exception.
    }
    @Test
    public void updateCart_handles_invalid_item_input(){
        boolean threwException = false;
        try {
            target.updateCart("12345", 12);
        } catch (IllegalArgumentException e){
            threwException = true;
        }
        Assert.assertTrue("Didn't throw an exception for an item not in inventory.", threwException);
        // target and test store shouldn't be any different from each other; since this cart update oughta throw an exception.
    }



    @Test
    public void updateCart_does_not_run_if_quantity_negative(){
        boolean threwException = false;
        try {
            target.updateCart("C1", -1);
        } catch (IllegalArgumentException e){
            threwException = true;
        }
        Assert.assertTrue("Didn't throw an exception for a negative cart number.", threwException);
    }
    @Test
    public void updateCart_does_not_run_if_quantity_exceeds_stock(){
        boolean threwException = false;
        try {
            target.updateCart("C1", 101);
        } catch (IllegalArgumentException e){
            threwException = true;
        }
        Assert.assertTrue("Didn't throw an exception when trying to buy more than stock.", threwException);
    }

    @Test
    public void updateCart_adds_correct_item_and_updates_inventory_quantity() {
        CandyStore testStore = new CandyStore();
        List<String> inventoryStringList = new ArrayList<String>();
        inventoryStringList.add(makeChocolate);
        inventoryStringList.add(makeLicorice);
        inventoryStringList.add(makeHardCandy);
        inventoryStringList.add(makeSour);

        testStore.buildInventory(inventoryStringList);
        testStore.buildInventory(inventoryStringList);
        testStore.addMoneyToCustomerBalance(100);
        testStore.updateCart("C1", 10);

        Assert.assertEquals( "Cart count is off.",  10, testStore.getCart().get(0).getQuantity());
        Assert.assertEquals("Inventory count is off.", 90, testStore.getCandyStoreItemInventory().get("C1").getQuantity());
        Assert.assertEquals("Item in cart is of the wrong type.", "C1", testStore.getCart().get(0).getId());
    }

    @Test
    public void addMoney_does_not_allow_amounts_greater_than_100(){
        CandyStore testStore = new CandyStore();
        boolean threwException = false;
        try {
            testStore.addMoneyToCustomerBalance(101);
        } catch (IllegalArgumentException e){
            threwException = true;
        }
        Assert.assertTrue("Added more than $100 at once.", threwException);
    }

    @Test
    public void addMoney_allows_exactly_100_to_be_added(){
        CandyStore testStore = new CandyStore();
        boolean threwException = false;
        try {
            testStore.addMoneyToCustomerBalance(100);
        } catch (IllegalArgumentException e){
            threwException = true;
        }
        Assert.assertFalse("Did not allow user to add $100.", threwException);
    }

    @Test
    public void addMoney_does_not_allow_balance_to_exceed_1000() {
        CandyStore testStore = new CandyStore();
        boolean threwException = false;
        testStore.addMoneyToCustomerBalance(100);
        testStore.addMoneyToCustomerBalance(100);
        testStore.addMoneyToCustomerBalance(100);
        testStore.addMoneyToCustomerBalance(100);
        testStore.addMoneyToCustomerBalance(100);
        testStore.addMoneyToCustomerBalance(100);
        testStore.addMoneyToCustomerBalance(100);
        testStore.addMoneyToCustomerBalance(100);
        testStore.addMoneyToCustomerBalance(100);
        testStore.addMoneyToCustomerBalance(99);
        try {
            testStore.addMoneyToCustomerBalance(2);
        } catch (IllegalArgumentException e){
            threwException = true;
        }
        Assert.assertTrue("Allowed balance to exceed $1000.", threwException);
    }

    @Test
    public void addMoney_allows_balance_of_exactly_1000() {
        CandyStore testStore = new CandyStore();
        boolean threwException = false;
        testStore.addMoneyToCustomerBalance(100);
        testStore.addMoneyToCustomerBalance(100);
        testStore.addMoneyToCustomerBalance(100);
        testStore.addMoneyToCustomerBalance(100);
        testStore.addMoneyToCustomerBalance(100);
        testStore.addMoneyToCustomerBalance(100);
        testStore.addMoneyToCustomerBalance(100);
        testStore.addMoneyToCustomerBalance(100);
        testStore.addMoneyToCustomerBalance(100);
        testStore.addMoneyToCustomerBalance(99);
        try {
            testStore.addMoneyToCustomerBalance(1);
        } catch (IllegalArgumentException e){
            threwException = true;
        }
        Assert.assertFalse("Did not allow balance to equal $1000.", threwException);
    }




      @Test
    public void buildInventory_creates_valid_map() {
        CandyStore testStore = new CandyStore();
        List<String> inventoryStringList = new ArrayList<String>();
        inventoryStringList.add(makeChocolate);
        inventoryStringList.add(makeLicorice);
        inventoryStringList.add(makeHardCandy);
        inventoryStringList.add(makeSour);

        testStore.buildInventory(inventoryStringList);

        Map<String, CandyStoreItem> expectedValue = new HashMap<>();
        expectedValue.put(testChoc.getId(), testChoc);
        expectedValue.put(testHC.getId(), testHC);
        expectedValue.put(testLicorice.getId(), testLicorice);
        expectedValue.put(testSour.getId(), testSour);

        Assert.assertEquals("Didn't set ID correctly.", expectedValue.get("L1").getId(), testStore.getCandyStoreItemInventory().get("L1").getId());
        Assert.assertEquals("Didn't set name correctly.", expectedValue.get("C1").getName(), testStore.getCandyStoreItemInventory().get("C1").getName());
        Assert.assertEquals("Didn't set quantity correctly.", expectedValue.get("H1").getQuantity(), testStore.getCandyStoreItemInventory().get("H1").getQuantity());
        Assert.assertEquals("Didn't set price correctly.", expectedValue.get("S1").getPrice(), testStore.getCandyStoreItemInventory().get("S1").getPrice(), .009);
    }

    @Test
    public void completeSale_resets_customer_balance() {
        CandyStore testStore = new CandyStore();
        testStore.addMoneyToCustomerBalance(100);
        Menu menu1 = new Menu();
        menu1.completeSale(testStore);
        Assert.assertEquals("Balance wasn't reset.", 0,  testStore.getCurrentCustomerBalance());
    }


}
