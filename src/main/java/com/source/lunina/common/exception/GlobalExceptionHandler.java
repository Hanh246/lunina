package com.source.lunina.common.exception;

import com.exe201.tutorlink.common.dto.response.BaseResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseResponse<?>> handleRuntimeException(RuntimeException ex) {
        String message = ex.getMessage();
        Map<String, String> errors = new HashMap<>();

        if (message != null && message.startsWith("OTP_INVALID:")) {
            errors.put("otp", message.split(":")[1]);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseResponse.builder()
                    .success(false)
                    .message("Xác thực thất bại")
                    .errors(errors)
                    .build());
        }

        // Trả về lỗi chung cho các RuntimeException khác
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.builder()
                .success(false)
                .message(message)
                .build());
    }

    @ExceptionHandler({
            ExpiredJwtException.class,
            MalformedJwtException.class,
            UnsupportedJwtException.class,
            SignatureException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<BaseResponse<Object>> handleJwtExceptions(Exception e) {
        String message = "Lỗi xác thực Token: ";

        if (e instanceof ExpiredJwtException) {
            message = "Token đã hết hạn. Vui lòng sử dụng Refresh Token.";
        } else if (e instanceof MalformedJwtException) {
            message = "Định dạng Token không hợp lệ.";
        } else if (e instanceof SignatureException) {
            message = "Chữ ký Token không đúng.";
        } else {
            message += e.getMessage();
        }

        BaseResponse<Object> response = BaseResponse.builder()
                .success(false)
                .message(message)
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}

