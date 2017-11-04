package hu.elte.bank.service;

import hu.elte.bank.entity.Account;
import hu.elte.bank.entity.Client;
import hu.elte.bank.repository.ClientRepository;
import hu.elte.bank.service.exceptions.ClientNotValidException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
@Data
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    private Client client;

    public Client login(Client client) throws ClientNotValidException {
        if (isValid(client)) {
            return this.client = clientRepository.findByName(client.getUsername());
        }
        throw new ClientNotValidException();
    }
    
    public void logout(Client client) {
        client = null;
    }

    public boolean isValid(Client client) {
        return clientRepository.findByUsernameAndPassword(client.getUsername(), client.getPassword()).isPresent();
    }

    public boolean isLoggedIn() {
        return client != null;
    }
    
}
