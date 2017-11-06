package hu.elte.bank.api;

import hu.elte.bank.entity.Transaction;
import hu.elte.bank.entity.Transaction.Status;
import hu.elte.bank.service.TransactionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/transaction")
public class TransactionApiController {
    
    @Autowired    
    private TransactionService transactionService;  
    
    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction saved = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(saved);
    }
    
    @GetMapping("/list/{sourceAccountNumber}")
    public ResponseEntity<List<Transaction>> listBySourceAccountNumber(@PathVariable String sourceAccountNumber) {
        return ResponseEntity.ok(transactionService.listBySourceAccountNumber(sourceAccountNumber));
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<Transaction>> listAllTransactions() {
        return ResponseEntity.ok(transactionService.listAllTransactions());
    }
    
    @GetMapping("/list/{status}")
    public ResponseEntity<List<Transaction>> listByStatus(@PathVariable Status status) {
        return ResponseEntity.ok(transactionService.listByStatus(status));
    }
}
