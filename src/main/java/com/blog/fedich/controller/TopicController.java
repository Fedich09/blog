package com.blog.fedich.controller;

import com.blog.fedich.model.Topic;
import com.blog.fedich.repo.impl.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @PostMapping(path = "/topic/{blogId}",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Topic> saveTopic(@PathVariable Long blogId, @RequestBody Topic topic) {
        return new ResponseEntity<>(topicService.saveTopic(blogId, topic), HttpStatus.OK);
    }

    @GetMapping(path = "/topic/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Topic> getTopic(@PathVariable Long id) {
        return new ResponseEntity<>(topicService.getTopic(id), HttpStatus.OK);
    }

    @PutMapping(path = "/topic/{id}",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody Topic topic) {
        return new ResponseEntity<>(topicService.updateTopic(topic, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/topic/{id}")
    public void deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
    }
}
