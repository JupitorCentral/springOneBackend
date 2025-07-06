package personnel.jupitorsendsme.springOneBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personnel.jupitorsendsme.springOneBackend.entity.TestUser;
import personnel.jupitorsendsme.springOneBackend.repository.TestUserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test-users")
@CrossOrigin(origins = "*")
public class TestUserController {

    @Autowired
    private TestUserRepository testUserRepository;

    @GetMapping
    public List<TestUser> getAllUsers() {
        return testUserRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestUser> getUserById(@PathVariable Long id) {
        Optional<TestUser> user = testUserRepository.findById(id);
        return user.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TestUser createUser(@RequestBody TestUser user) {
        return testUserRepository.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestUser> updateUser(@PathVariable Long id, @RequestBody TestUser userDetails) {
        Optional<TestUser> optionalUser = testUserRepository.findById(id);
        
        if (optionalUser.isPresent()) {
            TestUser user = optionalUser.get();
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            return ResponseEntity.ok(testUserRepository.save(user));
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (testUserRepository.existsById(id)) {
            testUserRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/username/{username}")
    public List<TestUser> getUsersByUsername(@PathVariable String username) {
        return testUserRepository.findByUsername(username);
    }

    @GetMapping("/email/{email}")
    public List<TestUser> getUsersByEmail(@PathVariable String email) {
        return testUserRepository.findByEmail(email);
    }
}