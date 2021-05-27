/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine;

import com.mthree.vendingmachine.controller.VendingMachineController;
import com.mthree.vendingmachine.dao.ItemAuditDao;
import com.mthree.vendingmachine.dao.ItemAuditDaoFileImpl;
import com.mthree.vendingmachine.dao.ItemPersistenceException;
import com.mthree.vendingmachine.dao.VendingMachineDao;
import com.mthree.vendingmachine.dao.VendingMachineDaoImpl;
import com.mthree.vendingmachine.service.ItemDataValidationException;
import com.mthree.vendingmachine.service.ItemDuplicateIdException;
import com.mthree.vendingmachine.service.ItemServiceLayerImpl;
import com.mthree.vendingmachine.service.VendingMachineService;
import com.mthree.vendingmachine.view.UserIO;
import com.mthree.vendingmachine.view.UserIOConsoleImpl;
import com.mthree.vendingmachine.view.VendingMachineView;

/**
 *
 * @author ishar
 */
public class App {

    public static void main(String[] args) throws ItemPersistenceException, ItemDuplicateIdException, ItemDataValidationException {
        // Instantiate the UserIO implementation
        UserIO io = new UserIOConsoleImpl();
        // Instantiate the View and wire the UserIO implementation into it
        VendingMachineView view = new VendingMachineView(io);
        // Instantiate the DAO
        VendingMachineDao dao = new VendingMachineDaoImpl();
        // Instantiate the Audit DAO
        ItemAuditDao AuditDao = new ItemAuditDaoFileImpl();
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        VendingMachineService service = new ItemServiceLayerImpl(dao, AuditDao);
        // Instantiate the Controller and wire the Service Layer into it
        VendingMachineController controller = new VendingMachineController(view, service);
        
        // Kick off the Controller
        controller.run();
    }
}
