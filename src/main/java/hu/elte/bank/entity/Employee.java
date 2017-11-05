package hu.elte.bank.entity;

import static hu.elte.bank.entity.Role.EMPLOYEE;
import javax.annotation.security.DeclareRoles;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DeclareRoles("EMPLOYEE")
public class Employee  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(nullable=false)
    private String name;
    
    @Column(nullable=false,  unique = true)
    private String username;
    
    @Column(nullable=false)
    private String password;
    
    /*@Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private Role role = EMPLOYEE;*/
    
       
    
}
