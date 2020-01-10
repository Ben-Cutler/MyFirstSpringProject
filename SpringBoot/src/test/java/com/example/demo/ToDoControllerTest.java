package com.example.demo;


import com.sun.tools.javac.comp.Todo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


//@RunWith(SpringRunner.class)
//@WebMvcTest
@RunWith(MockitoJUnitRunner.class)
public class ToDoControllerTest {
    @Mock
    ToDoRepository mockRepo;


    ToDoController subject;

    @Before
    public void setup() {
        subject = new ToDoController(mockRepo);
    }

    @Test
    public void test_greeting(){
        Model mockedModel = mock(Model.class);
        String toGreet = "Mr Cutler";
        this.subject.greeting(toGreet, mockedModel);

        verify(mockedModel).addAttribute("name", toGreet);
    }
    @Test
    public void test_adding_todos(){
        ToDoEntity entry1 = new ToDoEntity(1, "Test Update Methods", false);
        when(mockRepo.save(Mockito.any(ToDoEntity.class))).thenReturn(null);
        List<ToDoEntity> expected_list = new ArrayList<ToDoEntity>();
        expected_list.add(entry1);
        when(mockRepo.findAll()).thenReturn(expected_list);

        this.subject.addtodo(entry1);

        for (ToDoEntity todo : subject.getAllToDo()){ // Loop only runs once
            assertEquals(todo.getId(), entry1.getId());
            assertEquals(todo.getDescription(), entry1.getDescription());
            assertEquals(todo.getCompleted(), entry1.getCompleted());
        }
    }
    @Test
    public void test_get_by_id(){
        ToDoEntity entry1 = new ToDoEntity(1, "Test add methods", true);
        ToDoEntity entry2 = new ToDoEntity(2, "Test get by ID", false);
        ToDoEntity entry3 = new ToDoEntity(3, "Test delete by ID", false);

    }
}