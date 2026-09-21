package com.example.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void findAll_shouldReturnAllTodos() {
        Todo todo = new Todo("買い物", "牛乳を買う");
        when(todoRepository.findAll()).thenReturn(List.of(todo));

        List<Todo> result = todoService.findAll();

        assertEquals(1, result.size());
        assertEquals("買い物", result.get(0).getTitle());
    }

    @Test
    void create_shouldSaveTodoWhenTitleIsNotBlank() {
        Todo todo = new Todo("勉強", "Springを学ぶ");
        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        Todo result = todoService.create("勉強", "Springを学ぶ");

        assertEquals("勉強", result.getTitle());
        assertEquals("Springを学ぶ", result.getDescription());
        assertFalse(result.isCompleted());
    }

    @Test
    void create_shouldThrowWhenTitleIsBlank() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> todoService.create("   ", "説明")
        );

        assertEquals("タイトルは必須です", exception.getMessage());
    }

    @Test
    void update_shouldModifyTodo() {
        Todo existing = new Todo("旧タイトル", "旧説明");
        when(todoRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(todoRepository.save(existing)).thenReturn(existing);

        Todo result = todoService.update(1L, "新タイトル", "新説明", true);

        assertEquals("新タイトル", result.getTitle());
        assertEquals("新説明", result.getDescription());
        assertEquals(true, result.isCompleted());
    }

    @Test
    void delete_shouldCallRepositoryDeleteById() {
        when(todoRepository.existsById(1L)).thenReturn(true);

        todoService.delete(1L);

        verify(todoRepository, times(1)).deleteById(1L);
    }
}
