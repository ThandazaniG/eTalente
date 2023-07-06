package com.eviro.assessment.grad001.ThandazaniGwampa.exception;

import lombok.Builder;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Builder
@ControllerAdvice
public class ApplicationExceptionHandler  extends ResponseEntityExceptionHandler {

    /**
     * Handle the argument that are not valid for object
     * @param ex is the {@link MethodArgumentNotValidException }
     * @param headers  is httpHeaders of the  http request
     * @param status is HttpStatus of the http request
     * @param request is the web request itself
     * @return Details of the error with HttpStatus BAD_REQUEST
     */

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        ErrorDetails details= ErrorDetails.builder().message(ex.getMessage()).date(LocalDateTime.now()).build();
        return  new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         @NonNull HttpHeaders headers,
                                                                         @NonNull HttpStatusCode status,
                                                                         @NonNull WebRequest request) {

        ErrorDetails details = ErrorDetails.builder().message(ex.getMethod()+":"+ex.getMessage())
                .date(LocalDateTime.now()).build();
        return  new ResponseEntity<>(details, HttpStatus.METHOD_NOT_ALLOWED);
    }


    @ExceptionHandler(ApplicationConstrainViolationException.class)
    public ResponseEntity<Object> constrainViolationException(
            ApplicationConstrainViolationException ex, WebRequest request){
        ErrorDetails details = ErrorDetails.builder().message(ex.getConstraintViolations().toString())
                .date(LocalDateTime.now()).build();
        return  new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(AccountProfileNofFound.class)
    public ResponseEntity<Object> NotFoundException(
            AccountProfileNofFound ex, WebRequest request) {
        ErrorDetails details = ErrorDetails.builder().message(ex.getMessage())
                .date(LocalDateTime.now()).build();
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ImageNofFound.class)
    public ResponseEntity<Object> NotFoundException(
            ImageNofFound ex, WebRequest request) {
        ErrorDetails details = ErrorDetails.builder().message(ex.getMessage())
                .date(LocalDateTime.now()).build();
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(DuplicateAccountProfile.class)
    public ResponseEntity<Object> DuplicateException(
            DuplicateAccountProfile ex, WebRequest request) {
        ErrorDetails details = ErrorDetails.builder().message(ex.getMessage())
                .date(LocalDateTime.now()).build();
        return new ResponseEntity<>(details, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DuplicateImage.class)
    public ResponseEntity<Object> DuplicateException(
            DuplicateImage ex, WebRequest request) {
        ErrorDetails details = ErrorDetails.builder().message(ex.getMessage())
                .date(LocalDateTime.now()).build();
        return new ResponseEntity<>(details, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> NullPointerException(
            NullPointerException ex, WebRequest request) {
        ErrorDetails details = ErrorDetails.builder().message(ex.getMessage())
                .date(LocalDateTime.now()).build();
        return new ResponseEntity<>(details, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ImageNotValid.class)
    public ResponseEntity<Object> DuplicateException(
            ImageNotValid ex, WebRequest request) {
        ErrorDetails details = ErrorDetails.builder().message(ex.getMessage())
                .date(LocalDateTime.now()).build();
        return new ResponseEntity<>(details, HttpStatus.NOT_ACCEPTABLE);
    }


}
