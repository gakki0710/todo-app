package com.example.todo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

class TodoControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(new TodoController())
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    void index_shouldReturnIndexView() throws Exception {
        mockMvc.perform(get("/todo"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void add_shouldReturnAddView() throws Exception {
        mockMvc.perform(get("/todo/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add"));
    }

    @Test
    void edit_shouldReturnEditView() throws Exception {
        mockMvc.perform(get("/todo/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    void showHistory_shouldReturnHistoryView() throws Exception {
        mockMvc.perform(get("/todo/history/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("history"));
    }
}
