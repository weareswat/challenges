package com.gmail.etpr99.jose.listpostsbyrating.exceptions;

/**
 * Signals that a post with the given ID was not found.
 */
public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String message) {
        super(message);
    }
}
