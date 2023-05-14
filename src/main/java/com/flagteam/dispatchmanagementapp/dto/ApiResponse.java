package com.flagteam.dispatchmanagementapp.dto;

import java.util.List;

public class ApiResponse<T> {
    private int status;
    private T data;

    public ApiResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }
}
