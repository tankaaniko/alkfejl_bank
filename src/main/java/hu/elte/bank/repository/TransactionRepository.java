/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.bank.repository;

import java.util.Date;
import java.util.Optional;
import hu.elte.bank.entity.Transaction;
import hu.elte.bank.entity.Transaction.Type;

/**
 *
 * @author t_ani
 */
public interface TransactionRepository {
    
    Optional<Transaction> findByDate (Date date);
    
    Optional<Transaction> findByType (Type type);
    Optional<Transaction> findBySourceaccountnumber (String sourceAccountNumber);
    Optional<Transaction> findByTargetaccountnumber (String targetAccountNumber);
    
}
