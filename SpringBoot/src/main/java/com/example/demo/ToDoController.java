package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ToDoController {
    ToDoRepository toDoRepository;

    @Autowired
    public ToDoController(ToDoRepository mockRepo) {
        toDoRepository = mockRepo;
    }


    @GetMapping("/greeting")
    public void greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
    }
    // Get all available ToDos
    @GetMapping("/todo")
    public List<ToDoEntity> getAllToDo(){
        return toDoRepository.findAll();
    }
    // Add a new To Do
    @PutMapping("/todo")
    public ToDoEntity addtodo(@Valid @RequestBody ToDoEntity toDoEntity){
        return toDoRepository.save(toDoEntity);
    }
    // Get a specific To Do
    @GetMapping("/todo/{id}")
    public ToDoEntity getNoteById(@PathVariable(value = "id") Long todoId) throws ToDoNotFoundException {
        return toDoRepository.findById(todoId)
                .orElseThrow(() -> new ToDoNotFoundException(todoId));
    }
    // Update a To Do
    @PutMapping("/todo/{id}")
    public ToDoEntity updateToDo(@PathVariable(value = "id") Long bookId,
                           @Valid @RequestBody ToDoEntity new_todo) throws ToDoNotFoundException {

        ToDoEntity todo_to_update = toDoRepository.findById(bookId)
                .orElseThrow(() -> new ToDoNotFoundException(bookId));

        todo_to_update.setCompleted(new_todo.getCompleted());
        todo_to_update.setDescription(new_todo.getDescription());

        return toDoRepository.save(todo_to_update);
    }
    // Delete a to do
    @DeleteMapping("/todo/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long todoId) throws ToDoNotFoundException {
        ToDoEntity todo_to_delete = toDoRepository.findById(todoId)
                .orElseThrow(() -> new ToDoNotFoundException(todoId));

        toDoRepository.delete(todo_to_delete);

        return ResponseEntity.ok().build();
    }


}