/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.service;

import com.mthree.vendingmachine.dao.ItemPersistenceException;
import com.mthree.vendingmachine.models.Item;
import java.util.List;

/**
 *
 * @author ishar
 */
public interface VendingMachineService {

    void addItem(Item item) throws
            ItemDuplicateIdException,
            ItemDataValidationException,
            ItemPersistenceException;

    List<Item> getAllItems() throws
            ItemPersistenceException;

    Item getItem(String itemId) throws
            ItemPersistenceException;

    Item removeItem(String ItemId) throws
            ItemPersistenceException;

    void updateItem(String itemId, Item item) throws 
            ItemDuplicateIdException,
            ItemDataValidationException,
            ItemPersistenceException;

    void getAllItemsDisplay();
    
    String purchase(double balance, String itemId) throws
            ItemDataValidationException, 
            ItemPersistenceException;
    

}

//Item updateItem(String itemId, Item item) throws ItemDaoExceptions;

