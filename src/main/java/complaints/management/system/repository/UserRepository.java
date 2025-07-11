package complaints.management.system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import complaints.management.system.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email);
    Optional<User> findByCpf(String cpf);
}
