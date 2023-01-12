package com.blog.fedich.repo.impl;

import com.blog.fedich.model.Blog;
import com.blog.fedich.model.Topic;
import com.blog.fedich.model.User;
import com.blog.fedich.repo.BlogRepo;
import com.blog.fedich.repo.TopicRepo;
import com.blog.fedich.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final BlogRepo blogRepo;
    private final TopicRepo topicRepo;

    @Transactional
    public User saveUser(User user) {
        List<Blog> blogs = user.getBlogs();
        blogRepo.saveAll(blogs);
        return userRepo.save(user);
    }

    public User getUser(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new NoSuchElementException(String.format("User with id %s, not found", id));
        }
        return user.get();
    }

    @Transactional
    public User updateUser(User user, Long id) {
        Optional<User> userByIdOptional = userRepo.findById(id);
        if (userByIdOptional.isEmpty()) {
            throw new NoSuchElementException(String.format("User with id %s, not found", id));
        }
        User userById = userByIdOptional.get();
        List<Blog> blogsFromDb = userById.getBlogs();
        List<Blog> blogsNeedUpdate = user.getBlogs();
        blogsFromDb.forEach(blog -> {
            boolean present = blogsNeedUpdate.stream().anyMatch(blogUpdated -> blogUpdated
                    .getId().equals(blog.getId()));
            if (present) {
                Blog blogToUpdate = blogsNeedUpdate.stream().filter(blogUpdated -> blogUpdated
                        .getId().equals(blog.getId())).findAny().get();
                blog.setName(blogToUpdate.getName());
                blog.setTopics(blogToUpdate.getTopics());
            }
        });
        return userRepo.save(userById);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
