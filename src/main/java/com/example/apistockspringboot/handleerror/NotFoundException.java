package com.example.apistockspringboot.handleerror;

//teste

public class NotFoundException extends Exception{
    public NotFoundException(String errorMessage){
        super(errorMessage);
    }
}

