package com.heycar.heycardealerslisting.domain.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiError {
    private HttpStatus status;
    private String message;

    private ApiError(){
    }

    public static ApiError fromException(Exception exception){
        ApiError error = new ApiError();
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return error;
    }
}
