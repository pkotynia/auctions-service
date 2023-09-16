package com.sda.auctionsservice;

import jakarta.validation.ConstraintDeclarationException;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ProblemDetail handleObjectNotFoundException(ObjectNotFoundException exception){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleConstraintValidationException(MethodArgumentNotValidException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, getValidationErrors(exception));
    }

    private String getValidationErrors(MethodArgumentNotValidException exception) {
        return exception.getFieldErrors()
                .stream()
                //.map(error -> error.getField() + " " + error.getDefaultMessage())
                .map(new Function<FieldError, String>() {

                    @Override
                    public String apply(FieldError error) {
                        return error.getField() + " " + error.getDefaultMessage();
                    }
                })
                .collect(Collectors.joining(" ; "));
    }
}
