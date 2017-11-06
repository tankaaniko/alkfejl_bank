package hu.elte.bank.entity;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // lombok miatt getter setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    
    @JoinColumn
    @ManyToOne(targetEntity = Client.class, optional = false)
    private Client client;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
   
    //@Column(nullable=false,  unique = true)
    //private String username;
   
    @Column(nullable=false)
    private String accountNumber;
   
    @Column(nullable=false, unique = true)
    private Date creationDate;
    
    @Column(nullable=false)
    private long balance;
    
    @Column(nullable=false)
    private boolean blocked;
    
    
}
