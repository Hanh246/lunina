package com.source.lunina.common.exception;

import com.source.lunina.common.dto.response.BaseResponse;
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
}

