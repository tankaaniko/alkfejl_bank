package hu.elte.bank.repository;

import hu.elte.bank.entity.Account;
import java.util.Optional;


public interface AccountRepository {
    
    Optional<Account> findByAccountnumber(String accountnumber);
    
    Optional<Account> findByUsername(String username);
    
    
}
