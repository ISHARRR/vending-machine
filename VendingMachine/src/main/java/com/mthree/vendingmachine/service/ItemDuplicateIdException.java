/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.service;

/**
 *
 * @author ishar
 */
public class ItemDuplicateIdException extends Exception{
    
    public ItemDuplicateIdException(String message) {
        super(message);
    }

    public ItemDuplicateIdException(String message,
            Throwable cause) {
        super(message, cause);
    }
    
}
