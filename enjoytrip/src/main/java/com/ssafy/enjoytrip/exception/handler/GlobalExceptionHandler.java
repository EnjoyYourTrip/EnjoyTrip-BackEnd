package com.ssafy.enjoytrip.exception.handler;

import com.ssafy.enjoytrip.exception.EmailNotFoundException;
import com.ssafy.enjoytrip.exception.InvalidTokenException;
import com.ssafy.enjoytrip.exception.UnAuthorizedException;
import com.ssafy.enjoytrip.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ApiResponse<?>> handleUnAuthorizedException(UnAuthorizedException ex) {
        ApiResponse<?> response = ApiResponse.createError(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleEmailNotFoundException(EmailNotFoundException ex) {
        ApiResponse<?> response = ApiResponse.createError(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MailSendException.class)
    public ResponseEntity<ApiResponse<?>> handleMailSendException(MailSendException ex) {
        ApiResponse<?> response = ApiResponse.createError(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalidTokenException(InvalidTokenException ex) {
        ApiResponse<?> response = ApiResponse.createError(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGenericException(Exception ex) {
        ApiResponse<?> response = ApiResponse.createError("서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(UnAuthorizedException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ApiResponse<?> handleUnAuthorizedException(UnAuthorizedException ex) {
//        return ApiResponse.createError(ex.getMessage());
//    }
//
//    @ExceptionHandler(EmailNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ApiResponse<?> handleEmailNotFoundException(EmailNotFoundException ex) {
//        return ApiResponse.createError(ex.getMessage());
//    }
//
//    @ExceptionHandler(MailSendException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ApiResponse<?> handleMailSendException(MailSendException ex) {
//        return ApiResponse.createError(ex.getMessage());
//    }
//
//    @ExceptionHandler(InvalidTokenException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ApiResponse<?> handleInvalidTokenException(InvalidTokenException ex) {
//        return ApiResponse.createError(ex.getMessage());
//    }
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ApiResponse<?> handleGenericException(Exception ex) {
//        return ApiResponse.createError("서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
//    }
//}
