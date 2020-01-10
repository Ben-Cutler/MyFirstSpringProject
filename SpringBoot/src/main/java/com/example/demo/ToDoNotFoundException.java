package com.example.demo;

public class ToDoNotFoundException extends Exception {
    public long id;
    ToDoNotFoundException(long id){
        this.id = id;
    }
}
