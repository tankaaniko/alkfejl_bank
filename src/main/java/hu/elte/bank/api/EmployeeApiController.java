package hu.elte.bank.api;

import hu.elte.bank.entity.Client;
import hu.elte.bank.entity.Employee;
import hu.elte.bank.service.ClientService;
import hu.elte.bank.service.EmployeeService;
import hu.elte.bank.service.exceptions.ClientNotValidException;
import hu.elte.bank.service.exceptions.EmployeeNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeApiController {
    
    @Autowired
    private EmployeeService employeeService;
    
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
        }
        catch (EmployeeNotValidException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Employee> logout(@RequestBody Employee employee) {
        employeeService.logout();
        return ResponseEntity.ok().build();
    }
}
