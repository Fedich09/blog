package com.blog.fedich.repo.impl;

import com.blog.fedich.exception.BlogDoesntSaveException;
import com.blog.fedich.model.Blog;
import com.blog.fedich.model.Topic;
import com.blog.fedich.repo.BlogRepo;
import com.blog.fedich.repo.TopicRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TopicService {
    private final TopicRepo topicRepo;
    private final BlogRepo blogRepo;

    @Transactional
    public Topic saveTopic(Long blogId, Topic topic) {
        Optional<Blog> optionalBlog = blogRepo.findById(blogId);
        if (optionalBlog.isEmpty()) {
            throw new NoSuchElementException(String.format("Can't find Blog with id %s", blogId));
        }
        Blog blog = optionalBlog.get();
        blog.setTopics(Collections.singletonList(topic));
        Topic topicSaved = topicRepo.save(topic);
        blogRepo.save(blog);
        return topicSaved;
    }

    public Topic getTopic(Long id) {
        Optional<Topic> topic = topicRepo.findById(id);
        if (topic.isEmpty()) {
            throw new NoSuchElementException(String.format("Topic with id %s, not found", id));
        }
        return topic.get();
    }

    @Transactional
    public Topic updateTopic(Topic topic, Long id) {
        Optional<Topic> topicByIdOptional = topicRepo.findById(id);
        if (topicByIdOptional.isEmpty()) {
            throw new NoSuchElementException(String.format("Topic with id %s, not found", id));
        }
        Topic topicById = topicByIdOptional.get();
        topicById.setContent(topic.getContent());
        return topicRepo.save(topicById);
    }

    @Transactional
    public void deleteTopic(Long id) {
        topicRepo.deleteById(id);
    }
}
