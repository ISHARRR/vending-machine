/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.service;

import com.mthree.vendingmachine.dao.ItemAuditDao;
import com.mthree.vendingmachine.dao.ItemPersistenceException;

/**
 *
 * @author ishar
 */
public class ItemAuditDaoStubImpl implements ItemAuditDao {

    @Override
    public void writeAuditEntry(String entry) throws ItemPersistenceException {
        //do nothing . . .
    }
}

