package com.yodishtr.LibraryManagementSystem.Repository;

import com.yodishtr.LibraryManagementSystem.Entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findById(Long id);
    Optional<List<User>> findByFirstName(String firstName);
    Optional<List<User>> findByLastName(String lastName);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsernameAndPassword(String username, String password);
    boolean existsByEmailAndPassword(String email, String password);
    boolean existsById(Long id);

    @EntityGraph(attributePaths = {"borrowedBooks"})
    Optional<User> findByIdWithBorrowedBooks(Long id);

    @EntityGraph(attributePaths = {"borrowedBooks"})
    Optional<User> findByUsernameAndPasswordWithBorrowedBooks(String username, String password);
}
