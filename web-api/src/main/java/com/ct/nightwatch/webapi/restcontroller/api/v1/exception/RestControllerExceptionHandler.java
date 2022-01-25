package com.ct.nightwatch.webapi.restcontroller.api.v1.exception;

import com.ct.nightwatch.webapi.restcontroller.api.v1.exception.model.ErrorCode;
import com.ct.nightwatch.webapi.restcontroller.api.v1.exception.model.ErrorResponse;
import com.ct.nightwatch.webapi.service.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
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

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse<String>> handleAccessDeniedException(AccessDeniedException e) {
        ErrorResponse<String> errorResponse = new ErrorResponse<>(
                ErrorCode.ACCESS_DENIED,
                "Access denied",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse<String>> handleBadCredentialsException(BadCredentialsException e) {
        ErrorResponse<String> errorResponse = new ErrorResponse<>(
                ErrorCode.BAD_CREDENTIALS,
                "Username or password is incorrect",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
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
