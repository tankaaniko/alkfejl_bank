package hu.elte.bank.api;

import hu.elte.bank.entity.Client;
import hu.elte.bank.service.ClientService;
import hu.elte.bank.service.exceptions.ClientNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ClientApiController {
    
    @Autowired
    private ClientService clientService;
    
    @GetMapping("")
    public ResponseEntity<Client> client() {
        if (clientService.isLoggedIn()) {
            return ResponseEntity.ok(clientService.getLoggedInClient());
        }
        return ResponseEntity.badRequest().build();
    }    
    
    @PostMapping("/login")
    public ResponseEntity<Client> login(@RequestBody Client client) {
        try {
            return ResponseEntity.ok(clientService.login(client));
        }
        catch (ClientNotValidException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Client> logout(@RequestBody Client client) {
        clientService.logout();
        return ResponseEntity.ok().build();
    }
}
