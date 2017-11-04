package hu.elte.bank.repository;

import java.util.Date;
import java.util.Optional;
import hu.elte.bank.entity.Transaction;
import hu.elte.bank.entity.Transaction.Type;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {
    
    Optional<Transaction> findByDate (Date date);
    Iterable<Transaction> findByDate (long date);
    
    Optional<Transaction> findByType (Type type);
    Iterable<Transaction> findBySourceAccountNumber (String sourceAccountNumber);
    Optional<Transaction> findByTargetAccountNumber (String targetAccountNumber);
    
    
    
}
