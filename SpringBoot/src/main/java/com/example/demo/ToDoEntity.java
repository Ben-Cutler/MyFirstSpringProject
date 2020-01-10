package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class ToDoEntity {
    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    private String description;

    private boolean completed;
    ToDoEntity(){
        super();
    }
    ToDoEntity(long id, String description, boolean completed){
        super();
        this.id = id;
        this.description = description;
        this.completed = completed;
    }
    public long getId(){return this.id;}
    public void setId(long id){this.id = id;}

    public String getDescription(){return this.description;}
    public void setDescription(String s){this.description = s;}

    public boolean getCompleted(){return this.completed;}
    public void setCompleted(boolean isCompleted){this.completed = isCompleted;}

}
