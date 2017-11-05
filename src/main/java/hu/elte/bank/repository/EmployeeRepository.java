package hu.elte.bank.repository;

import hu.elte.bank.entity.Employee;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    
    Optional<Employee> findByUsernameAndPassword(String username, String password); 
      
    Employee findByName(String name);
    Employee findByUsername(String username);
}

