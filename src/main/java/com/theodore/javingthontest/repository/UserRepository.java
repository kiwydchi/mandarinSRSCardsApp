package com.theodore.javingthontest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theodore.javingthontest.entity.User;

@Repository 
public interface UserRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findByNameAndPassword(String name, String password);
}
