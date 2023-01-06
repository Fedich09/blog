package com.blog.fedich.repo;

import com.blog.fedich.model.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepo extends CrudRepository<Blog, Long> {
}
