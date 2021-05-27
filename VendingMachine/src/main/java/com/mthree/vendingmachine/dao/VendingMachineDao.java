/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.dao;

import com.mthree.vendingmachine.models.Item;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ishar
 */
public interface VendingMachineDao {

    Item addItem(String itemId, Item item) throws ItemPersistenceException;

    List<Item> getAllItems() throws ItemPersistenceException;

    Item getItem(String itemId) throws ItemPersistenceException;

    Item removeItem(String itemId) throws ItemPersistenceException;
    
    Item updateItem(String itemId, Item item) throws ItemPersistenceException;
    
    Item purchase(double balance, String itemId) throws ItemPersistenceException;

}
