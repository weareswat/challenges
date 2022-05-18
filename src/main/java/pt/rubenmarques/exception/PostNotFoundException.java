package pt.rubenmarques.exception;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(final Long postId) {
        super(String.format("Post with ID(%d) not found.", postId));
    }

    public PostNotFoundException(final String message) {
        super(message);
    }
}
