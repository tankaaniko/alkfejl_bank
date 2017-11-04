package hu.elte.bank.repository;

import hu.elte.bank.entity.Account;
import hu.elte.bank.entity.Client;
import java.util.Optional;


public interface AccountRepository {
    
    Optional<Account> findByAccountnumber(String accountnumber);
    
    Optional<Account> findByUsername(String username);
    
    Optional<Account> findByClient(Client client);
    
    
}
