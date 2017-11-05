package hu.elte.bank.repository;

import java.util.Date;
import java.util.Optional;
import hu.elte.bank.entity.Transaction;
import hu.elte.bank.entity.Transaction.Status;
import hu.elte.bank.entity.Transaction.Type;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {
    
    Optional<Transaction> findByDate (Date date);
    Iterable<Transaction> findAllByDate (long date);
    
    Iterable<Transaction> findAllByType (Type type);
    Iterable<Transaction> findAllByStatus (Status status);
    
    Iterable<Transaction> findAllBySourceAccountNumber (String sourceAccountNumber);
    Iterable<Transaction> findAllByTargetAccountNumber (String targetAccountNumber);
    
    
    
}
