package com.students.handler;


import com.students.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentnotValidException(MethodArgumentNotValidException ex,WebRequest request){

            return ResponseEntity.badRequest().body(createApiError(ex.getMessage(),request));
    }

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ApiError>handleBaseException(BaseException exception , WebRequest request){

        return ResponseEntity.badRequest().body(createApiError(exception.getMessage(),request));
    }

    private  String getHostname(){
        try {
         return  InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            System.out.println("Hata oluştu "+ e.getMessage());
        }
        return null;
    }

        private <E> ApiError<E> createApiError(E message,WebRequest request){
        ApiError<E> apiError= new ApiError<>();
        apiError.setStatus(HttpStatus.BAD_REQUEST.value());

        Exception<E> exception=new Exception<>();
        exception.setCreateTime(new Date());
        exception.setHostName(getHostname());
        exception.setPath(request.getDescription(false).substring(4));
        exception.setMessage(message);

        apiError.setException(exception);


        return apiError;
    }


}
