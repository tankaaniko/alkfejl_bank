package hu.elte.bank.repository;

import hu.elte.bank.entity.Account;
import hu.elte.bank.entity.Client;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;


public interface AccountRepository extends CrudRepository<Account,Long>{
    
    Account findByAccountNumber(String accountnumber);
    
   // Optional<Account> findByUsername(String username);
    
    Optional<Account> findByClient(Client client);
    
    
}
