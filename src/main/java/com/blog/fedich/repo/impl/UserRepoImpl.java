package com.blog.fedich.repo.impl;

import com.blog.fedich.model.User;
import com.blog.fedich.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserRepoImpl {
    private final UserRepo userRepo;

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public User getUser(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new NoSuchElementException(String.format("User with id %s, not found", id));
        }
        return user.get();
    }

    public User updateUser(User user, Long id) {
        Optional<User> userByIdOptional = userRepo.findById(id);
        if (userByIdOptional.isEmpty()) {
            throw new NoSuchElementException(String.format("User with id %s, not found", id));
        }
        User userById = userByIdOptional.get();
        userById.setBlog(user.getBlog());
        return userRepo.save(userById);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
