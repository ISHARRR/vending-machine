/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.service;

import com.mthree.vendingmachine.dao.ItemAuditDao;
import com.mthree.vendingmachine.dao.ItemPersistenceException;
import com.mthree.vendingmachine.dao.VendingMachineDao;
import com.mthree.vendingmachine.models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ishar
 */
public class ItemServiceLayerImplTest {

    VendingMachineService service;

    public ItemServiceLayerImplTest() {
        VendingMachineDao dao = new ItemDaoStubImpl();
        ItemAuditDao auditDao = new ItemAuditDaoStubImpl();

        service = new ItemServiceLayerImpl(dao, auditDao);
    }

//    @BeforeEach
//    public void setUp() throws ItemDuplicateIdException, ItemDataValidationException, ItemPersistenceException {
//        String itemId = "001";
//        Item item = new Item(itemId, "Drink", 1, 10);
//        service.addItem(item);
//    }
    @Test
    public void testAddvalidItem() throws ItemDuplicateIdException, ItemDataValidationException, ItemPersistenceException {
        //Arrage
        String itemId = "002";
        Item item = new Item(itemId, "Drink", 1, 10);
        //Act
        try {
            service.addItem(item);
        } catch (ItemDuplicateIdException
                | ItemDataValidationException
                | ItemPersistenceException e) {
            // ASSERT
            fail("Student was valid. No exception should have been thrown.");
        }
    }

    @Test
    public void testAddDuplicateItem() {
        // ARRANGE
        String itemId = "001";
        Item item = new Item(itemId, "Drink", 1, 10);

        // ACT
        try {
            service.addItem(item);
            fail("Expected DupeId Exception was not thrown.");
        } catch (ItemDataValidationException
                | ItemPersistenceException e) {
            // ASSERT
            fail("Incorrect exception was thrown.");
        } catch (ItemDuplicateIdException e) {
            return;
        }
    }

    @Test
    public void testGetAllItem() throws Exception {
        // ARRANGE
        String itemId = "001";
        Item item = new Item(itemId, "Drink", 1, 10);

        // ACT & ASSERT
        assertEquals(1, service.getAllItems().size(),
                "Should only have one item.");
        assertTrue(service.getAllItems().contains(item),
                "The one student should be Ada.");
    }

    @Test
    public void testGetItem() throws Exception {
        // ARRANGE
        String itemId = "001";
        Item item = new Item(itemId, "Drink", 1, 10);

        // ACT & ASSERT
        Item shouldBeAda = service.getItem("001");
        assertNotNull(shouldBeAda, "Getting 001 should be not null.");
        assertEquals(item, shouldBeAda,
                "Item stored under 001 should be Ada.");

        Item shouldBeNull = service.getItem("042");
        assertNull(shouldBeNull, "Getting 0042 should be null.");

    }

    @Test
    public void testRemoveItem() throws Exception {
        // ARRANGE
        String itemId = "001";
        Item item = new Item(itemId, "Drink", 1, 10);

        // ACT & ASSERT
        Item shouldBeAda = service.removeItem("001");
        assertNotNull(shouldBeAda, "Removing 0001 should be not null.");
        assertEquals(item, shouldBeAda, "Student removed from 0001 should be Ada.");

        Item shouldBeNull = service.removeItem("023");
        assertNull(shouldBeNull, "Removing 0042 should be null.");

    }
    
    public void testUdateItem() throws Exception {
        // ARRANGE
        String itemId = "001";
        Item item = new Item(itemId, "Soda", 1, 10);

        // ACT & ASSERT
        Item shouldBeAda = service.getItem("001");
        service.updateItem(itemId, item);
        Item orginal = service.getItem(itemId);
        assertEquals(orginal,item);

    }

}
