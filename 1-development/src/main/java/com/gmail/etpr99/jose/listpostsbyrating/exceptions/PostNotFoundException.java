package com.gmail.etpr99.jose.listpostsbyrating.exceptions;

/**
 * Signals that a post with the given ID was not found.
 */
public class PostNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@link PostNotFoundException}.
     */
    public PostNotFoundException() {
    }

    /**
     * Constructs a new {@link PostNotFoundException} with the given message.
     *
     * @param message The message.
     */
    public PostNotFoundException(String message) {
        super(message);
    }
}
