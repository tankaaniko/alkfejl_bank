package hu.elte.bank.entity;

import java.util.Date;
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
@Data // lombok miatt getter setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
   
    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private Type type;
    
    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Column(nullable = true)
    private String sourceAccountNumber;
   
    
    
    @Column(nullable = true)
    private String targetAccountNumber;
   
    @Column(nullable=true)
    private String targetClientName;
    
    @Column(nullable=false)
    private Date date;
    
    @Column(nullable = false)
    private int amount;    
     

    
    public enum Type{
        IN,OUT,TRANSFER
        // betét, kivét, utalás
    }
    
    public enum Status{
        ACTIVE, EXECUTED
    }
}
