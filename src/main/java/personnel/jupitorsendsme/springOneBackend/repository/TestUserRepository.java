package personnel.jupitorsendsme.springOneBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personnel.jupitorsendsme.springOneBackend.entity.TestUser;

import java.util.List;

@Repository
public interface TestUserRepository extends JpaRepository<TestUser, Long> {
    List<TestUser> findByUsername(String username);
    List<TestUser> findByEmail(String email);
}