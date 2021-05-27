/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.dao;

import com.mthree.vendingmachine.models.CurrentAccount;
import com.mthree.vendingmachine.models.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ishar
 */
public class VendingMachineDaoImpl implements VendingMachineDao {

    public final String ITEM_FILE;
    public static final String DELIMITER = "::";

    Map<String, Item> itemCollection = new HashMap<>();
    CurrentAccount balance = new CurrentAccount();

    public VendingMachineDaoImpl() {
        ITEM_FILE = "ItemCollection.txt";
    }

    public VendingMachineDaoImpl(String rosterTextFile) {
        ITEM_FILE = rosterTextFile;
    }

    @Override
    public Item addItem(String itemId, Item item) throws ItemPersistenceException {
        loadItems();
        Item newItem = itemCollection.put(itemId, item);
        writItems();
        return newItem;
    }

    @Override
    public List<Item> getAllItems() throws ItemPersistenceException {
        loadItems();
        return new ArrayList(itemCollection.values());
    }

    @Override
    public Item getItem(String itemId) throws ItemPersistenceException {
        loadItems();
        Item gotItem = itemCollection.get(itemId);
        return gotItem;
    }

    @Override
    public Item removeItem(String itemId) throws ItemPersistenceException {
        loadItems();
        Item removedItem = itemCollection.remove(itemId);
        writItems();
        return removedItem;
    }

    @Override
    public Item updateItem(String itemId, Item item) throws ItemPersistenceException {
        loadItems();
        Item updatedItem = itemCollection.put(itemId, item);
        writItems();
        return updatedItem;
    }

    @Override
    public Item purchase(double balance, String itemId) throws ItemPersistenceException {
        loadItems();
        int itemUnits = itemCollection.get(itemId).getUnits();
        itemCollection.get(itemId).setUnits(itemUnits - 1);
        writItems();
        Item currentItem = itemCollection.get(itemId);

        return currentItem;
    }

    // daaaattaaaaa stuff
    private String marshallItem(Item aItemAsText) {
        String itemAsText = aItemAsText.getItemId() + DELIMITER;
        itemAsText += aItemAsText.getItemName() + DELIMITER;
        itemAsText += aItemAsText.getItemPrice() + DELIMITER;
        itemAsText += aItemAsText.getUnits();

        return itemAsText;
    }

    private Item unmarshallItem(String itemAsText) {
        String[] itemtokens = itemAsText.split(DELIMITER);

        String itemId = itemtokens[0];
        String itemName = itemtokens[1];
        double itemPrice = Double.parseDouble(itemtokens[2]);
        int itemUnits = Integer.parseInt(itemtokens[3]);

        Item itemFromFile = new Item(itemId, itemName, itemPrice, itemUnits);

        return itemFromFile;
    }

    private void loadItems() throws ItemPersistenceException {
        Scanner scanner = null;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ITEM_FILE)));
        } catch (FileNotFoundException e) {
            throw new ItemPersistenceException("Could not load Item data into memory.", e);
        }

        String currentLine;

        Item currentItem;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            itemCollection.put(currentItem.getItemId(), currentItem);
        }
        scanner.close();
    }

    private void writItems() throws ItemPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ITEM_FILE));
        } catch (IOException e) {
            throw new ItemPersistenceException("Could not save Item data.", e);
        }

        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for (Item currentDvd : itemList) {
            itemAsText = marshallItem(currentDvd);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }

//
//    public Map<String, Item> getItems() {
//        return itemCollection;
//    }
}
