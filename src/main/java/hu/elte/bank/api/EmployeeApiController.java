package hu.elte.bank.api;

import hu.elte.bank.entity.Account;
import hu.elte.bank.entity.Client;
import hu.elte.bank.entity.Employee;
import hu.elte.bank.repository.AccountRepository;
import hu.elte.bank.service.AccountService;
import hu.elte.bank.service.ClientService;
import hu.elte.bank.service.EmployeeService;
import hu.elte.bank.service.exceptions.ClientNotValidException;
import hu.elte.bank.service.exceptions.EmployeeNotValidException;
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
@RequestMapping("/api/employee")
public class EmployeeApiController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired    
    private AccountService accountService;      
     @Autowired    
    private AccountRepository accountRepository;      



    @GetMapping("")
    public ResponseEntity<Employee> employee() {
        if (employeeService.isLoggedIn()) {
            return ResponseEntity.ok(employeeService.getLoggedInEmployee());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Employee> login(@RequestBody Employee employee) {
        try {
            return ResponseEntity.ok(employeeService.login(employee));
        } catch (EmployeeNotValidException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Employee> logout(@RequestBody Employee employee) {
        employeeService.logout();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/lock/{targetAccountNumber}")
    @RolesAllowed("EMPLOYEE")
    public ResponseEntity<String> lock( @PathVariable String targetAccountNumber) {
       Account account = accountService.accountByNumber(targetAccountNumber);
        accountService.lock(account);
        return ResponseEntity.ok("Sikeres zárolás!");
    }

    @PutMapping("/unlock/{targetAccountNumber}")
    @RolesAllowed("EMPLOYEE")
    public ResponseEntity<String> unlock( @PathVariable String targetAccountNumber) {
    //public ResponseEntity<String> unlock(@RequestBody String  targetAccountnumber) {
        
        
        Account account = accountService.accountByNumber(targetAccountNumber);
        accountService.unlock(account);
        return ResponseEntity.ok("Zárolás sikeresen feloldva!");
    }
}
