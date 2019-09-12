package com.marjina.hire_yourself.common.response;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO<T> {

    private String status;

    private T data;

    private String message;

    private List<ErrorDTO> errors;

    /**
     * Init constructor
     */
    public ResponseDTO() {
    }

    /**
     * Init constructor
     *
     * @param status  Response status: error || success
     * @param data    Extracted data which will go out to response
     * @param message Message about response
     * @param errors  Error list in case if status is error
     */
    public ResponseDTO(String status, T data, String message, List<ErrorDTO> errors) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.errors = errors;
    }
}
