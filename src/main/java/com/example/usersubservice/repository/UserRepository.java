package com.example.usersubservice.repository;

import com.example.usersubservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String userEmail);
    boolean existsByEmail(String userEmail);

}
