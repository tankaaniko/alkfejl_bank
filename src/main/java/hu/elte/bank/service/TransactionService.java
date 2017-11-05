package hu.elte.bank.service;

import hu.elte.bank.entity.Transaction;
import hu.elte.bank.repository.TransactionRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    
    private TransactionRepository transactionRepository;
    
    public Transaction createTransaction(Transaction transaction){
        
        return transactionRepository.save(transaction);
    }
    
    public List<Transaction> listBySourceAccountNumber(String sourceAccountNumber){
        
        List<Transaction> transactions = new ArrayList<>();
        transactionRepository.findAllBySourceAccountNumber(sourceAccountNumber).iterator().forEachRemaining(transactions::add);
        
        return transactions;
    }
    
    public List<Transaction> listAllTransactions(){
        
        List<Transaction> allTransaction = new ArrayList<>();
        transactionRepository.findAll().iterator().forEachRemaining(allTransaction::add);
        
        return allTransaction;
    }
}
