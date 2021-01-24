package com.mixfar.posts.validations;

import com.mixfar.posts.model.Post;
import com.mixfar.posts.service.PostService;
import com.mixfar.posts.utils.errors.FieldErrorMessage;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class PostValidation implements BasicValidation<Post> {

    private PostService postService;
    private List<FieldErrorMessage> fieldErrorMessages;

    public PostValidation(PostService postService) {
        this.postService = postService;
        this.fieldErrorMessages = new LinkedList<>();
    }

    @Override
    public List<FieldErrorMessage> validCreate(Post view) {
        if (view.getPostTitle() == null || view.getPostTitle().trim().isEmpty())
            addFieldErrorMessage("postTitle", "Post title isn't a valid String");
        if (view.getPostBody() == null || view.getPostBody().trim().isEmpty())
            addFieldErrorMessage("postBody", "Post body isn't a valid String");
        if (view.getUpVotes() == null || view.getUpVotes() < 0)
            addFieldErrorMessage("upVotes", "Up Votes must be 0 or greater then 0");
        if (view.getDownVotes() == null || view.getDownVotes() < 0)
            addFieldErrorMessage("downVotes", "Down Votes must be 0 or greater then 0");
        if (view.getPublishDate().isAfter(LocalDate.now()))
            addFieldErrorMessage("publishDate", "Publish date can't be after Actual Date");

        return fieldErrorMessages.isEmpty() ? null : fieldErrorMessages;
    }

    @Override
    public List<FieldErrorMessage> validRead(Long id) {
        Post post = postService.findByPostId(id);
        if (post == null)
            addFieldErrorMessage("id", "Post with id: " + id + " not found");

        return fieldErrorMessages.isEmpty() ? null : fieldErrorMessages;
    }

    @Override
    public List<FieldErrorMessage> validUpdate(Post view) {
        validRead(view.getPostId());
        return validCreate(view);
    }

    @Override
    public List<FieldErrorMessage> validDelete(Long id) {
        return validRead(id);
    }

    @Override
    public void addFieldErrorMessage(String field, String message) {
        FieldErrorMessage fieldErrorMessage = new FieldErrorMessage();
        fieldErrorMessage.setField(field);
        fieldErrorMessage.setErrorMessage(message);

        fieldErrorMessages.add(fieldErrorMessage);
    }
}
