package com.heycar.heycardealerslisting.domain.error;

public class NonExistentDealerException extends RuntimeException{
    public NonExistentDealerException(Long id){
        super(String.format("No dealer with id: %s exists", id));
    }
}
