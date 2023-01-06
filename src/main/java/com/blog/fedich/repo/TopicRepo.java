package com.blog.fedich.repo;

import com.blog.fedich.model.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepo extends CrudRepository<Topic, Long> {

}
