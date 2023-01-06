package com.blog.fedich.configure;

import com.blog.fedich.model.Blog;
import com.blog.fedich.model.Topic;
import com.blog.fedich.model.User;
import com.blog.fedich.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class Startup {

    private final UserRepo userRepo;

    @Bean
    void createUser() {
        User user = new User();
        user.setId(1L);
        Blog blog = new Blog();
        blog.setId(1L);
        blog.setName("My blog");
        Topic topic = new Topic();
        topic.setContent("Security");
        blog.setTopics(List.of(topic));
        user.setBlog(blog);
        userRepo.save(user);
    }
}
