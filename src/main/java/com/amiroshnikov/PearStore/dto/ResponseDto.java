package com.amiroshnikov.PearStore.dto;

public class ResponseDto {

    private String status;
    private String message;

    public ResponseDto() {
    }

    public String getStatus() {
        return status;
    }

    public ResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
