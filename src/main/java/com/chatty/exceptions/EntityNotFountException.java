package com.chatty.exceptions;

public class EntityNotFountException extends ChattyRuntimeException{

    public EntityNotFountException(Class<?> clazz, long id) {
        super(String.format("%s with id %d is not found", clazz.getSimpleName(), id));
    }
}
