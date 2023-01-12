package com.blog.fedich.exception;

public class BlogDoesntSaveException extends RuntimeException {
    public BlogDoesntSaveException(String message) {
        super(message);
    }
}
