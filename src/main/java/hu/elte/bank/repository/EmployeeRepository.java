package hu.elte.bank.repository;

import hu.elte.bank.entity.Employee;
import java.io.Serializable;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    
    Optional<Employee> findByUsernameAndPassword(String username, String password); 
    
}

