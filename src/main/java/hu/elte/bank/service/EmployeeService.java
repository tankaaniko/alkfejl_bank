package hu.elte.bank.service;

import hu.elte.bank.entity.Employee;
import hu.elte.bank.repository.EmployeeRepository;
import hu.elte.bank.service.exceptions.EmployeeNotValidException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
@Data
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    public Employee login(Employee employee) throws EmployeeNotValidException {
        if (isValid(employee)) {
            return this.employee = employeeRepository.findByUsername(employee.getUsername());
        }
        throw new EmployeeNotValidException();
    }
    
    //public void logout(Employee employee) {
    public void logout() {
        employee = null;
    }

    public boolean isValid(Employee employee) {
        return employeeRepository.findByUsernameAndPassword(employee.getUsername(), employee.getPassword()).isPresent();
    }

    public boolean isLoggedIn() {
        return employee != null;
    }
    
    public Employee getLoggedInEmployee() {
        return employee;
    }
    
}
