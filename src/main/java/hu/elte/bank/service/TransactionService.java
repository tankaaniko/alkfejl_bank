package hu.elte.bank.service;

import hu.elte.bank.entity.Account;
import hu.elte.bank.entity.Transaction;
import hu.elte.bank.entity.Transaction.Status;
import hu.elte.bank.repository.AccountRepository;
import hu.elte.bank.repository.TransactionRepository;
import hu.elte.bank.service.exceptions.NotFoundAccountException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    public Transaction createTransaction(Transaction transaction) throws NotFoundAccountException {
        Account source = accountRepository.findByAccountNumber(transaction.getSourceAccountNumber());
        Account target = accountRepository.findByAccountNumber(transaction.getTargetAccountNumber());

        switch (transaction.getType()) {
            case IN:
                if (source != null) {
                    accountService.deposit(source, transaction.getAmount());
                } else {
                    throw new NotFoundAccountException();
                }
                break;
            case OUT:
                if (source != null) {
                    accountService.deposit(source, transaction.getAmount());
                } else {
                    throw new NotFoundAccountException();
                }
                break;
            case TRANSFER:
                
                if (source != null && target != null) {
                    accountService.transfer(source, target,transaction.getAmount());
                } else {
                    throw new NotFoundAccountException();
                }
                break;
        }

        return transactionRepository.save(transaction);
    }

    public List<Transaction> listBySourceAccountNumber(String sourceAccountNumber) {

        List<Transaction> transactions = new ArrayList<>();
        transactionRepository.findAllBySourceAccountNumber(sourceAccountNumber).iterator().forEachRemaining(transactions::add);

        return transactions;
    }

    public List<Transaction> listAllTransactions() {

        List<Transaction> allTransaction = new ArrayList<>();
        transactionRepository.findAll().iterator().forEachRemaining(allTransaction::add);

        return allTransaction;
    }

    public List<Transaction> listByStatus(Status status) {

        List<Transaction> transactions = new ArrayList<>();
        transactionRepository.findAllByStatus(status).iterator().forEachRemaining(transactions::add);

        return transactions;
    }

}
