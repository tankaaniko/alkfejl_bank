package hu.elte.bank.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
   
    @Column(nullable=false,  unique = true)
    private String username;
   
    @Column(nullable=false)
    private String accountNumber;
   
    @Column(nullable=false, unique = true)
    private Date creationDate;
    
    
}
