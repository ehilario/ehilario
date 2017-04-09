package com.challenge.Model;

import org.springframework.stereotype.Component;

/**
 * Created by ehilario on 4/7/2017.
 */
@Component
public class CustomException extends Exception {
    private static final long sericalVersionUID = 1L;

    private int code;
    private String message;

    public CustomException(){
        super();
    }

    public CustomException(String message){
        super(message);
        this.message = message;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
