package com.example.shop_api.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.shop_api.dto.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // =========================
    // 404 -not found
    // =========================

//    @ExceptionHandler(ProductNotFoundException.class)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {
        log.warn("Resource not found: {}", ex.getMessage());
        ErrorResponse body = new ErrorResponse(
                LocalDateTime.now(),
                404,
                "RESOURCE_NOT_FOUND",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }


    // =========================
    // 400 - Validation
    // =========================

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
                        error.getField() + ": " + error.getDefaultMessage()
                )
                .collect(Collectors.joining(", "));

        ErrorResponse body = new ErrorResponse(
                LocalDateTime.now(),
                400,
                "VALIDATION_ERROR",
                fieldErrors,
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(body);
    }

    // =========================
    // 409 - Duplicate Product- dữ liệu trùng
    // =========================
    @ExceptionHandler(DuplicateProductException.class)
    public ResponseEntity<ErrorResponse> handleProductDuplicate(DuplicateProductException e, HttpServletRequest request){
        ErrorResponse body = new ErrorResponse(LocalDateTime.now(), 409,"PRODUCT_ALREADY_EXISTS",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
    // =========================
    // 409 - Duplicate Category- dữ liệu trùng
    // =========================
    @ExceptionHandler(DuplicateCategoryException.class)
    public ResponseEntity<ErrorResponse> handleCategoryDuplicate(DuplicateCategoryException e, HttpServletRequest request) {
        ErrorResponse body = new ErrorResponse(LocalDateTime.now(), 409, "CATEGORY_ALREADY_EXISTS", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
    // =========================
    // 409 - Category InUse - dữ liệu đã được sử dụng
    // =========================
    @ExceptionHandler(CategoryInUseException.class)
    public ResponseEntity<ErrorResponse> handleCategoryInUse(
            CategoryInUseException ex,
            HttpServletRequest request) {

        ErrorResponse body = new ErrorResponse(
                LocalDateTime.now(),
                409,
                "CATEGORY_IN_USE",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }


    // =========================
    // 409 - InsufficientStock - Hết hàng
    // =========================
    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientStock(
            InsufficientStockException ex,
            HttpServletRequest request) {

        ErrorResponse body = new ErrorResponse(
                LocalDateTime.now(),
                409,
                "INSUFFICIENT_STOCK",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
    // =========================
    // 500 - INTERNAL_ERROR -
    // =========================
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request){
        log.error("Unexpected server error", e);
        ErrorResponse body = new ErrorResponse(LocalDateTime.now(), 500,"INTERAL_SERVER_ERROR","ĐÃ XẢY RA LỖI PHÍA MÁY CHỦ", request.getRequestURI());
        return ResponseEntity.internalServerError().body(body);
    }




}
