package hu.elte.bank.api;

//import hu.elte.bank.entity.Account;
import hu.elte.bank.entity.Transaction;
import hu.elte.bank.service.AccountService;
import hu.elte.bank.service.ClientService;
import hu.elte.bank.service.EmployeeService;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountApiController {
    
    @Autowired    
    private AccountService accountService;
    
    @Autowired    
    private ClientService clientService;
    
    @Autowired    
    private EmployeeService employeeService;
    
    @GetMapping("/my/{accountnumber}/balance")
    @RolesAllowed("CLIENT")
    public ResponseEntity<Long> balanceQuery(@PathVariable String accountnumber){
        return ResponseEntity.ok(accountService.balanceQuery(accountnumber));
    }
    
    @GetMapping("/my/{id}/transactions")
    @RolesAllowed("CLIENT")
    public ResponseEntity<List<Transaction>> getMyTransactions(@PathVariable String sourceAccountNumber){
        return ResponseEntity.ok(accountService.getMyTransactions(sourceAccountNumber));
    }
    
    //@PutMapping("/transfer")
    
    
}
