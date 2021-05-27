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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ishar
 */
public class ItemServiceLayerImpl implements VendingMachineService {

    private ItemAuditDao auditDao;
    VendingMachineDao dao;

    public ItemServiceLayerImpl(VendingMachineDao dao, ItemAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void addItem(Item item) throws ItemDuplicateIdException, ItemDataValidationException, ItemPersistenceException {
        // First check to see if there is alreay a student 
        // associated with the given student's id
        // If so, we're all done here - 
        // throw a ClassRosterDuplicateIdException
        if (dao.getItem(item.getItemId()) != null) {
            throw new ItemDuplicateIdException(
                    "ERROR: Could not create student. Item Id "
                    + item.getItemId()
                    + " already exists");
        }

        // Now validate all the fields on the given Student object.  
        // This method will throw an
        // exception if any of the validation rules are violated.
        validateItemData(item);

        // We passed all our business rules checks so go ahead 
        // and persist the Student object
        dao.addItem(item.getItemId(), item);
        // The student was successfully created, now write to the audit log
        auditDao.writeAuditEntry(
                "Item " + item.getItemId() + " CREATED.");

    }

    @Override
    public void updateItem(String itemId, Item item) throws ItemDuplicateIdException, ItemDataValidationException, ItemPersistenceException {
        if (dao.getItem(itemId) == null) {
            throw new ItemDuplicateIdException(
                    "ERROR: Could not create student. Item Id "
                    + item.getItemId()
                    + " does not exists - Try Again");
        }
        validateItemData(item);
        dao.updateItem(item.getItemId(), item);
        auditDao.writeAuditEntry(
                "Item " + item.getItemId() + " UPDATED.");

    }

    @Override
    public List<Item> getAllItems() throws ItemPersistenceException {
        return dao.getAllItems();
    }

    @Override
    public void getAllItemsDisplay() {

        List<Item> list = null;
        try {
            list = dao.getAllItems();
        } catch (ItemPersistenceException ex) {
            Logger.getLogger(ItemServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        list.stream().filter(i -> i.getUnits() != 0).forEachOrdered(System.out::println);

//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getUnits() > 0) {
//                System.out.println(list.get(i));
//            }
//        }
    }

    @Override
    public Item getItem(String itemId) throws ItemPersistenceException {
        return dao.getItem(itemId);
    }

    @Override
    public Item removeItem(String itemId) throws ItemPersistenceException {
        Item removedItem = dao.removeItem(itemId);
        // Write to audit log
        auditDao.writeAuditEntry("Item " + itemId + " REMOVED.");
        return removedItem;
    }

    @Override
    public String purchase(double balance, String itemId) throws ItemDataValidationException, ItemPersistenceException {
        Item selectedItem = dao.getItem(itemId);
//        int stock = selectedItem.getUnits();
        double price = selectedItem.getItemPrice();

        if (balance < price) {
            return "ERROR: Insufficent Funds.";
        } else {
            System.out.println("Purchase Completed");
            dao.purchase(balance, itemId);
            return changeCalculator(balance, price);

        }
//        return null;
    }

    public String changeCalculator(double balanace, double price) {
        BigDecimal bdBalance = new BigDecimal(balanace);
        BigDecimal bdPrice = new BigDecimal(price);

        bdBalance = bdBalance.setScale(2, RoundingMode.CEILING);
        bdPrice = bdPrice.setScale(2, RoundingMode.CEILING);

        BigDecimal pounds = bdBalance.subtract(bdPrice);
        BigDecimal pence = pounds.remainder(BigDecimal.ONE).movePointRight(2);

        pounds = pounds.setScale(0, RoundingMode.FLOOR);
//        pence  = pence.setScale(2, RoundingMode.FLOOR);

        String change = pounds + " " + Coins.POUND + ", " + pence + " " + Coins.PENCE;
        return change;
    }

    private void validateItemData(Item item) throws
            ItemDataValidationException {

        if (item.getItemName() == null
                || item.getItemPrice() == 0
                || item.getUnits() == 0) {

            throw new ItemDataValidationException(
                    "ERROR: All fields [Name, Price, Units] are required.");
        }
    }
}
