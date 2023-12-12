package com.meow.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllException(Exception ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<?> TodoException(ChangeSetPersister.NotFoundException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<?> UnAuthorized(HttpClientErrorException.Unauthorized ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
    }
}
