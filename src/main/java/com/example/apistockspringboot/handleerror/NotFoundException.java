package com.example.apistockspringboot.handleerror;


public class NotFoundException extends Exception{
    public NotFoundException(String errorMessage){
        super(errorMessage);
    }
}

