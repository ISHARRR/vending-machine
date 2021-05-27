/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.service;

import com.mthree.vendingmachine.dao.ItemPersistenceException;
import com.mthree.vendingmachine.dao.VendingMachineDao;
import com.mthree.vendingmachine.models.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ishar
 */
public class ItemDaoStubImpl implements VendingMachineDao {

    public Item singleItem;

    public ItemDaoStubImpl() {
        singleItem = new Item("001", "Drink", 1, 10);
    }

    public ItemDaoStubImpl(Item testItem) {
        this.singleItem = testItem;
    }

    @Override
    public Item addItem(String itemId, Item item) throws ItemPersistenceException {
        if (itemId.equals(singleItem.getItemId())) {
            return singleItem;
        } else {
            return null;
        }
    }

    @Override
    public List<Item> getAllItems() throws ItemPersistenceException {
        List<Item> studentList = new ArrayList<>();
        studentList.add(singleItem);
        return studentList;
    }

    @Override
    public Item getItem(String itemId) throws ItemPersistenceException {
        if (itemId.equals(singleItem.getItemId())) {
            return singleItem;
        } else {
            return null;
        }
    }

    @Override
    public Item removeItem(String itemId) throws ItemPersistenceException {
        if (itemId.equals(singleItem.getItemId())) {
            return singleItem;
        } else {
            return null;
        }
    }

    @Override
    public Item updateItem(String itemId, Item Item) throws ItemPersistenceException {
        if (itemId.equals(singleItem.getItemId())) {
            return singleItem;
        } else {
            return null;
        }
    }

    @Override
    public Item purchase(double balance, String itemId) throws ItemPersistenceException {
        Item selectedItem = singleItem;
        double price = selectedItem.getItemPrice();

        if (balance < price) {
            return null;
        } else {
            return singleItem;
        }
    }
}
