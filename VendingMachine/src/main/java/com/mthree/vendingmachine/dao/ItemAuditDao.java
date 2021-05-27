/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.dao;

/**
 *
 * @author ishar
 */
public interface ItemAuditDao {

    public void writeAuditEntry(String entry) throws ItemPersistenceException;

}
