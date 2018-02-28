package ru.akm.testtask.animals.jetty.web;

import java.nio.file.AccessDeniedException;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.akm.testtask.animals.jetty.models.ErrorInfo;

/**
 * Кастомная обработка ошибок
 * 
 * @author akm
 */
@ControllerAdvice
@EnableWebMvc
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
        
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final ErrorInfo error = new ErrorInfo(1L, "Неверный аргумент",ex.getLocalizedMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final ErrorInfo error = new ErrorInfo(2L, "Контроллер отсутствует",ex.getLocalizedMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final ErrorInfo error = new ErrorInfo(3L, "Запрошенный метод не поддерживается",ex.getLocalizedMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
        final ErrorInfo error = new ErrorInfo(4L, "Нарушение оганичений",ex.getLocalizedMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    } 
    
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final ErrorInfo error = new ErrorInfo(5L, "Обработчик не найден",ex.getLocalizedMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
 
    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        final ErrorInfo error = new ErrorInfo(6L, "Доступ запрещён",ex.getLocalizedMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler({ Exception.class })
    protected ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {        
        final ErrorInfo error = new ErrorInfo(999L, "Неизвестгая ошибка",ex.getLocalizedMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}