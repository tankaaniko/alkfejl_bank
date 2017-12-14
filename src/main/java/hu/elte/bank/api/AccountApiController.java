package hu.elte.bank.api;

import hu.elte.bank.entity.Account;
import hu.elte.bank.entity.Transaction;
import hu.elte.bank.service.AccountService;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountApiController {
    
    @Autowired    
    private AccountService accountService;      
    
    @GetMapping("/list")
    public ResponseEntity<List<Account>> listAllAccount() {
        return ResponseEntity.ok(accountService.listAllAccount());
    }    
    /*
    @GetMapping("/balance")
    @RolesAllowed("CLIENT")
    public ResponseEntity<Long> balanceQuery(@RequestBody Account account){
        return ResponseEntity.ok(accountService.balanceQuery(account.getAccountNumber()));
    }
    
    @GetMapping("/transactions")
    @RolesAllowed("CLIENT")
    public ResponseEntity<List<Transaction>> getMyTransactions(@RequestBody Account account){
        return ResponseEntity.ok(accountService.getMyTransactions(account.getAccountNumber()));
    }
    */
    @GetMapping("/balance/{accountNumber}")
    @RolesAllowed("CLIENT")
    public ResponseEntity<Long> balanceQuery(@PathVariable String accountNumber){
        return ResponseEntity.ok(accountService.balanceQuery(accountNumber));
    }
    
    @GetMapping("/transactions/{accountNumber}")
    @RolesAllowed("CLIENT")
    public ResponseEntity<List<Transaction>> getMyTransactions(@PathVariable String accountNumber){
        return ResponseEntity.ok(accountService.getMyTransactions(accountNumber));
    }
    
    @PutMapping("/transfer/{transferAmount}")
    @RolesAllowed("CLIENT")
    public ResponseEntity<String> transfer(@RequestBody Account account, @RequestBody Account targetAccount, @PathVariable long transferAmount){
        accountService.transfer(account, targetAccount, transferAmount);
        return ResponseEntity.ok("Sikeres átutalás!");
    }
    
    @PutMapping("/deposit/{amount}")
    @RolesAllowed("EMPLOYEE")
    public ResponseEntity<String> deposit(@RequestBody Account targetAccount, @PathVariable long amount){
        accountService.deposit(targetAccount, amount);
        return ResponseEntity.ok("Sikeres betét!");
    }
    
    @PutMapping("/withdraw/{amount}")
    @RolesAllowed("EMPLOYEE")
    public ResponseEntity<String> withdraw(@RequestBody Account targetAccount, @PathVariable long amount){
        accountService.withdraw(targetAccount, amount);
        return ResponseEntity.ok("Sikeres kivétel!");
    }
    
    @PutMapping("/lock")
    @RolesAllowed("EMPLOYEE")
    public ResponseEntity<String> lock(@RequestBody Account targetAccount){
        accountService.lock(targetAccount);
        return ResponseEntity.ok("Sikeres zárolás!");
    }
    
    @PutMapping("/unlock")
    @RolesAllowed("EMPLOYEE")
    public ResponseEntity<String> unlock(@RequestBody Account targetAccount){
        accountService.unlock(targetAccount);
        return ResponseEntity.ok("Zárolás sikeresen feloldva!");
    }
    
}
