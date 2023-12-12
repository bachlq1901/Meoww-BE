package com.meow.services;

import com.meow.entities.User;
import com.meow.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository repository;

    public User getCurrentUser() {
        try {
            long userId = (long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = new User();
            user.setId(userId);
            return user ;
        } catch (Exception e) {
            return null;
        }
    }

    public User findById(long sub) {
        return repository.findById(sub).orElseThrow(() -> new RuntimeException("Người dùng không tồn tại."));
    }

    public User findByUserName(String userName) {
        return repository.findByUserName(userName).orElseThrow(() -> new RuntimeException("Người dùng không tồn tại."));
    }

    public void save(User user) {
        repository.save(user);
    }
}
