package com.ct.nightwatch.webapi.service.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id, Class<?> entity) {
        super(String.format("%s not found with id %d", entity.getSimpleName(), id));
    }

}
