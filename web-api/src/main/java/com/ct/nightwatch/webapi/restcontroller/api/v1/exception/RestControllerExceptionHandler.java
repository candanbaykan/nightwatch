package com.ct.nightwatch.webapi.restcontroller.api.v1.exception;

import com.ct.nightwatch.webapi.restcontroller.api.v1.exception.model.ErrorCode;
import com.ct.nightwatch.webapi.restcontroller.api.v1.exception.model.ErrorResponse;
import com.ct.nightwatch.webapi.service.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestControllerExceptionHandler.class);

    private final HttpServletRequest request;

    public RestControllerExceptionHandler(HttpServletRequest request) {
        this.request = request;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse<String>> handleException(Exception e) {
        ErrorResponse<String> errorResponse = new ErrorResponse<>(
                ErrorCode.UNEXPECTED_EXCEPTION,
                "An unexpected exception occurred.",
                request.getRequestURI()
        );

        logger.error(errorResponse.getMessage(), e);
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse<String>> handleEntityNotFoundException(EntityNotFoundException e) {
        ErrorResponse<String> errorResponse = new ErrorResponse<>(
                ErrorCode.ENTITY_NOT_FOUND,
                e.getMessage(),
                request.getRequestURI()
        );

        logger.trace(errorResponse.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}
