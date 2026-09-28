package com.theodore.javingthontest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theodore.javingthontest.entity.User;
import com.theodore.javingthontest.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional 
public class UserService {
    private final UserRepository userRepository;

    @Autowired 
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }
    
    public boolean login(String name, String password) {
        return userRepository.findByNameAndPassword(name, password).isPresent();
    }
}
