package hu.elte.bank.repository;

import hu.elte.bank.entity.Client;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;


public interface ClientRepository extends CrudRepository<Client,Long> {
    
    Optional<Client> findByUsernameAndPassword(String username, String password); 
    Optional<Client> findByUsernameAndPasswordAndPin(String username, String password, Long pin); 
    
    Client findByName(String name);
    Client findByUsername(String username);
}
