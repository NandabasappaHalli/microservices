package com.product.exceptions;

import lombok.Data;

@Data
public class ProductCustomException extends RuntimeException {

    private  String errorCode;

    public ProductCustomException(String message, String errorCode) {

        super(message);
        this.errorCode = errorCode;
    }
}
