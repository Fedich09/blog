package com.blog.fedich.repo.impl;

import com.blog.fedich.model.Blog;
import com.blog.fedich.repo.BlogRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogRepoImpl {
    private final BlogRepo blogRepo;

    public Blog saveBlog(Blog blog) {
        return blogRepo.save(blog);
    }

    public Blog getBlog(Long id) {
        Optional<Blog> blog = blogRepo.findById(id);
        if (blog.isEmpty()) {
            throw new NoSuchElementException(String.format("Blog with id %s, not found", id));
        }
        return blog.get();
    }

    public Blog updateBlog(Blog blog, Long id) {
        Optional<Blog> blogByIdOptional = blogRepo.findById(id);
        if (blogByIdOptional.isEmpty()) {
            throw new NoSuchElementException(String.format("Blog with id %s, not found", id));
        }
        Blog blogById = blogByIdOptional.get();
        blogById.setName(blog.getName());
        blogById.setTopics(blog.getTopics());
        return blogRepo.save(blogById);
    }

    public void deleteBlog(Long id) {
        blogRepo.deleteById(id);
    }
}
