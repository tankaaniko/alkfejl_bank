package hu.elte.bank.service;

import hu.elte.bank.entity.Account;
import hu.elte.bank.entity.Client;
import hu.elte.bank.entity.Transaction;
import hu.elte.bank.repository.AccountRepository;
import hu.elte.bank.repository.TransactionRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    
    public long balanceQuery(String accountnumber){
        return accountRepository.findByAccountnumber(accountnumber).get().getBalance();
    }
    
    public List<Transaction> getMyTransactions(String sourceAccountNumber) {
        List<Transaction> list = new ArrayList<>();
        List<Transaction> allTransaction = new ArrayList<>();
        transactionRepository.findAllBySourceAccountNumber(sourceAccountNumber).iterator().forEachRemaining(allTransaction::add);
        long currentDate = System.currentTimeMillis();
        long dayInMillis = 86400000;
        for(Transaction transaction : allTransaction){
            if((currentDate - transaction.getDate().getTime()) < (dayInMillis * 30)){
                list.add(transaction);
            }
        }
        return list;
    }
    
    public void transfer(Account account, Account targetAccount, long transferAmount){
        if(account.getBalance() - transferAmount > 0 && account.isBlocked() == false){
            account.setBalance(account.getBalance() - transferAmount);
        }
        targetAccount.setBalance(targetAccount.getBalance() + transferAmount);
    }
    
    public void deposit(Account account, long amount){
        account.setBalance(account.getBalance() + amount);
    }
    
    public void withdraw(Account account, long amount){
        if(account.getBalance() - amount > 0){
            account.setBalance(account.getBalance() - amount);
        }
    }
    
    public void lock(Account targetAccount){
        targetAccount.setBlocked(true);
    }
    
    public void unlock(Account targetAccount){
        targetAccount.setBlocked(false);
    }
    
}
