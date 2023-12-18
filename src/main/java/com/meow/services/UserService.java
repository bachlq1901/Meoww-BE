package com.meow.services;

import com.meow.Utils.Utils;
import com.meow.dtos.request.UserRequest;
import com.meow.entities.User;
import com.meow.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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

    public User findByEmailAndPhone(String email, String phone) {
        return repository.findByEmailAndPhone(email, phone).orElseThrow(() -> new RuntimeException("Người dùng không tồn tại."));
    }

    public void save(User user) {
        repository.save(user);
    }

    public void update(UserRequest request) {
        User user = findById(request.getId());
        BeanUtils.copyProperties(request, user, Utils.getNullPropertyNames(request));
        repository.save(user);
    }
}
