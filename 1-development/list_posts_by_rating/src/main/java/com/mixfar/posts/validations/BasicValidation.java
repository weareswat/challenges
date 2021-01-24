package com.mixfar.posts.validations;

import com.mixfar.posts.utils.errors.FieldErrorMessage;

import java.util.List;

public interface BasicValidation<T> {
    List<FieldErrorMessage> validCreate(T view);
    List<FieldErrorMessage> validRead(Long id);
    List<FieldErrorMessage> validUpdate(T view);
    List<FieldErrorMessage> validDelete(Long id);

    void addFieldErrorMessage(String field, String message);
}
