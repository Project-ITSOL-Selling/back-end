package com.example.selling.data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

import javax.annotation.Nullable;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler implements ProblemHandling, SecurityAdviceTrait {

    public GlobalExceptionHandler() {
    }
    @Override
    public ResponseEntity<Problem> process(@Nullable ResponseEntity<Problem> entity, NativeWebRequest request) {
        if (entity == null) {
            return entity;
        }
        return new ResponseEntity<>(entity.getHeaders(), entity.getStatusCode());
    }

    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException e, WebRequest request){
        ErrorDetails details = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
        return  new ResponseEntity(details, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> handlerException(Exception e, WebRequest request){
        ErrorDetails details = new ErrorDetails(new Date(),e.getMessage(),request.getDescription(false));
        return  new ResponseEntity(details,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public  ResponseEntity<?> handlerApiException(ApiError e, WebRequest request){
        ErrorDetails details = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
        return  new ResponseEntity(details,HttpStatus.NOT_FOUND);
    }
}
