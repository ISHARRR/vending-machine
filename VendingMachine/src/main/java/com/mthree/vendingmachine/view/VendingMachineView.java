/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.view;

import com.mthree.vendingmachine.models.Item;

/**
 *
 * @author ishar
 */
public class VendingMachineView {

    UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    // ----- Banners
    public void createNewItemBanner() {
        io.print("=== Adding Item ===");
    }

    public void createdItemBanner() {
        io.print("=== Item Added ===");
    }

    public void listItemsBanner() {
        io.print("=== All Item ===");
    }

    public void viewItemsBanner() {
        io.print("=== Selected Item ===");
    }

    public void removeItemsBanner() {
        io.print("=== Removing Item ===");
    }

    public void updateItemsBanner() {
        io.print("=== Update Item ===");
    }

    public void PurchaseItemsBanner() {
        io.print("=== Purchase Item ===");
    }

    public void taskCompletedBanner() {
        io.print("=== Task Compeleted ===");
    }

    public void changeMessage(String message) {
        io.print("=== Your Change ===");
        io.print(message);
    }
    public void displayErrorMessage(String message) {
        io.print(message);
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Items");
        io.print("2. Add new Item");
        io.print("3. View an Item");
        io.print("4. Remove an Item");
        io.print("5. Update an Item");
        io.print("6. Purchase an Item");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    public Item createNewItem() {
        String id = io.readId("Enter Item ID");
        String name = io.readString("Enter Item Name");
        double price = io.readDouble("Enter Item Price", 0.00, 10.00);
        int units = io.readInt("Enter Number of Items", 0, 100);

        Item createdItem = new Item(id, name, price, units);

        return createdItem;
    }

    public String selectItem() {
        String id = io.readId("Enter Id");
        return id;
    }

    public String removeItem() {
        String id = io.readId("Enter Id");
        return id;
    }

    public Item updateItem() {
        String id = io.readId("Enter ID");
        String name = io.readString("Enter Item Name");
        double price = io.readDouble("Enter Item Price", 0.00, 10.00);
        int units = io.readInt("Enter Number of Items", 0, 100);

        Item createdItem = new Item(id, name, price, units);

        return createdItem;
    }

    public double deposit() {
        double deposit = io.readDouble("Enter desposit amount - Min = £0.10, Max = £10 "
                , 0.10 , 10.00);
        return deposit;
    }

}
