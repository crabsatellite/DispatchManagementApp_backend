package com.flagteam.dispatchmanagementapp.dto;

public class ApiErrorResponse {
    private String message;
    private String code;

    public ApiErrorResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

}
