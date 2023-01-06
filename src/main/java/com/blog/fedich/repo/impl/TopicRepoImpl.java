package com.blog.fedich.repo.impl;

import com.blog.fedich.model.Topic;
import com.blog.fedich.repo.TopicRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TopicRepoImpl {
    private final TopicRepo topicRepo;

    public Topic saveTopic(Topic topic) {
        return topicRepo.save(topic);
    }

    public Topic getTopic(Long id) {
        Optional<Topic> topic = topicRepo.findById(id);
        if (topic.isEmpty()) {
            throw new NoSuchElementException(String.format("Topic with id %s, not found", id));
        }
        return topic.get();
    }

    public Topic updateTopic(Topic topic, Long id) {
        Optional<Topic> topicByIdOptional = topicRepo.findById(id);
        if (topicByIdOptional.isEmpty()) {
            throw new NoSuchElementException(String.format("Topic with id %s, not found", id));
        }
        Topic topicById = topicByIdOptional.get();
        topicById.setContent(topic.getContent());
        return topicRepo.save(topicById);
    }

    public void deleteTopic(Long id) {
        topicRepo.deleteById(id);
    }
}
