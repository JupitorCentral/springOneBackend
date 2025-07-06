package personnel.jupitorsendsme.springOneBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import personnel.jupitorsendsme.springOneBackend.entity.TestUser;
import personnel.jupitorsendsme.springOneBackend.repository.TestUserRepository;

@RestController
@RequestMapping("/api/transaction-test")
@CrossOrigin(origins = "*")
public class TransactionTestController {

    @Autowired
    private TestUserRepository testUserRepository;

    @PostMapping("/test-rollback")
    @Transactional
    public ResponseEntity<String> testTransactionRollback() {
        try {
            // Create first user
            TestUser user1 = new TestUser("tx_user1", "tx1@test.com");
            testUserRepository.save(user1);
            
            // Create second user
            TestUser user2 = new TestUser("tx_user2", "tx2@test.com");
            testUserRepository.save(user2);
            
            // Intentionally cause an error to test rollback
            throw new RuntimeException("Simulated error for transaction rollback test");
            
        } catch (Exception e) {
            // Re-throw to trigger rollback
            throw new RuntimeException("Transaction failed: " + e.getMessage());
        }
    }

    @PostMapping("/test-success")
    @Transactional
    public ResponseEntity<String> testTransactionSuccess() {
        // Create two users in a transaction
        TestUser user1 = new TestUser("tx_success1", "success1@test.com");
        TestUser user2 = new TestUser("tx_success2", "success2@test.com");
        
        testUserRepository.save(user1);
        testUserRepository.save(user2);
        
        return ResponseEntity.ok("Transaction successful - 2 users created");
    }
}