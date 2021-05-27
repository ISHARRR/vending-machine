/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.controller;

import com.mthree.vendingmachine.dao.ItemPersistenceException;
import com.mthree.vendingmachine.models.Item;
import com.mthree.vendingmachine.service.ItemDataValidationException;
import com.mthree.vendingmachine.service.ItemDuplicateIdException;
import com.mthree.vendingmachine.service.VendingMachineService;
import com.mthree.vendingmachine.view.VendingMachineView;

/**
 *
 * @author ishar
 */
public class VendingMachineController {

    private VendingMachineView view;
    private VendingMachineService service;

    public VendingMachineController(VendingMachineView view, VendingMachineService service) {
        this.view = view;
        this.service = service;
    }

    public void run() throws ItemPersistenceException, ItemDuplicateIdException, ItemDataValidationException {
        service.getAllItemsDisplay();

        boolean flag = true;

        while (flag) {
            int menuSelection = view.printMenuAndGetSelection();
            switch (menuSelection) {
                case 1:
                    service.getAllItemsDisplay();
                    break;
                case 2:
                    createItem();
                    break;
                case 3:
                    viewItem();
                    break;
                case 4:
                    removeItem();
                    break;
                case 5:
                    updateItem();
                    break;
                case 6:
                    purchaseItem();
                    break;
                case 7:
                    System.out.println("exit");
                    flag = false;
                    break;
                default:
                    System.out.println("error");
            }

        }
    }

    public void createItem() throws ItemPersistenceException {
        view.createNewItemBanner();
        boolean hasErrors = false;
        do {
            Item createItemInputs = view.createNewItem();
            try {
                service.addItem(createItemInputs);
                view.taskCompletedBanner();
                hasErrors = false;
            } catch (ItemDuplicateIdException | ItemDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    public void listItems() throws ItemPersistenceException {
        service.getAllItems();
    }

    public void listItemsDisplay() throws ItemPersistenceException {
        view.listItemsBanner();
        service.getAllItemsDisplay();
        view.listItemsBanner();
    }

    public void viewItem() throws ItemPersistenceException {
        view.viewItemsBanner();
        String id = view.selectItem();
        System.out.println(service.getItem(id));
        view.taskCompletedBanner();
    }

    public void updateItem() throws ItemPersistenceException, ItemDuplicateIdException {
        view.updateItemsBanner();
        boolean hasErrors = false;
        do {
            Item createUpdatedItemInputs = view.updateItem();
            try {
                service.updateItem(createUpdatedItemInputs.getItemId(), createUpdatedItemInputs);
                view.taskCompletedBanner();
                hasErrors = false;
            } catch (ItemDuplicateIdException | ItemDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    public void removeItem() throws ItemPersistenceException {
        view.removeItemsBanner();
        String id = view.selectItem();
        service.removeItem(id);
        view.taskCompletedBanner();
    }
    
    public void purchaseItem() throws ItemPersistenceException, ItemDataValidationException {
        view.PurchaseItemsBanner();
        String id = view.selectItem();
        double deposit = view.deposit();
        String change = service.purchase(deposit, id);
        view.changeMessage(change);
        view.taskCompletedBanner();
    }
}
