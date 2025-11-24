package support.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import support.project.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}