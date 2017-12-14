package hu.elte.bank.entity;

import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "CLIENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DeclareRoles("CLIENT")
public class Client {
    
    @OneToMany(targetEntity = Account.class, mappedBy = "client")
    // @JsonIgnoreProperties("articles")
    private List<Account> accounts;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(nullable=false)
    private String name;
    
    @Column(nullable=false,  unique = true)
    private String username;
    
    @Column(nullable=false)
    private String password;
    
    @Column(nullable=false)
    private long pin;
    
   // private String accountnumber = "123"; //firstAccountNumber(accounts);
    
    private String firstAccountNumber(List<Account> accounts){
        Account account = accounts.get(0);
        return account.getAccountNumber();
    }
    
    /*@Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private Role role = CLIENT;*/
    
    
}
