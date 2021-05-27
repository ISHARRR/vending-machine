/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.dao;

import com.mthree.vendingmachine.models.Item;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ishar
 */
public class VendingMachineDaoImplTest {
    
    VendingMachineDao testDao;

    public VendingMachineDaoImplTest() throws IOException {

    }


    @BeforeEach
    public void setUp() throws Exception{
        String testFile = "test.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        this.testDao = new VendingMachineDaoImpl(testFile);
        System.out.println("SDFDASDFASDFasd");

    }

//    @AfterEach
//    public void tearDown() {
//    }
    
    @Test
    public void testAddGetItem() throws Exception {
        //arrange
        String itemId = "001";
        Item item = new Item(itemId, "Drink", 1, 10);
        //act
        this.testDao.addItem(itemId, item);
        Item gotItem = this.testDao.getItem(itemId);
        //assert
        assertEquals(item.getItemId(), gotItem.getItemId());
        assertEquals(item.getItemName(), gotItem.getItemName());
        assertEquals(item.getItemPrice(), gotItem.getItemPrice());
        assertEquals(item.getUnits(), gotItem.getUnits());
        
    }
    
    @Test
    public void testRemoveItem() throws Exception {
        //arrange
        String itemId = "001";
        Item item = new Item(itemId, "Drink", 1, 10);
        this.testDao.addItem(itemId, item);
        //act
        this.testDao.removeItem(itemId);
        Item gotItem = this.testDao.getItem(itemId);    
        //assert
        assertNull(gotItem);
        
    }
    
    @Test
    public void testUpdateItem() throws Exception {
        //arrange
        String itemId = "001";
        Item item = new Item(itemId, "Drink", 1, 10);
        Item item1 = new Item(itemId, "Crisp", 1, 10);
        this.testDao.addItem(itemId, item);
        //act
        this.testDao.updateItem(itemId, item1);
        Item gotItem = this.testDao.getItem(itemId);    
        //assert
        assertEquals(item1, gotItem);
        
    }
    
    @Test
    public void testPurchaseItem() throws Exception {
        //arrange
        String itemId = "001";
        Item item = new Item(itemId, "Drink", 1, 10);
        this.testDao.addItem(itemId, item);
        //act        
        Item purchasedItem = this.testDao.purchase(5, itemId);
        int expectedUnit = 9; 
        //assert
        assertEquals(purchasedItem.getUnits(), expectedUnit);
        
    }

}
