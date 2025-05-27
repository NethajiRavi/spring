package com.own.jsonschemavalidator.exception;

import com.own.jsonschemavalidator.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequestException(BadRequestException badRequestException, WebRequest webRequest){


        ErrorResponseDto errorResponseDto =  new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                badRequestException.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);


    }






}
