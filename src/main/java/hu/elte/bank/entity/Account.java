package hu.elte.bank.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


public class Account {
    
    @JoinColumn
    @OneToOne(targetEntity = Client.class, optional = false)
    private Client client;
    
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
