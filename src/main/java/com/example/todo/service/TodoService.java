package com.example.todo.service;

import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo create(String title, String description) {
        String normalizedTitle = title == null ? "" : title.trim();
        if (normalizedTitle.isEmpty()) {
            throw new IllegalArgumentException("タイトルは必須です");
        }

        return todoRepository.save(new Todo(normalizedTitle, description));
    }

    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo update(Long id, String title, String description, boolean completed) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TODOが見つかりません: " + id));

        String normalizedTitle = title == null ? "" : title.trim();
        if (normalizedTitle.isEmpty()) {
            throw new IllegalArgumentException("タイトルは必須です");
        }

        todo.setTitle(normalizedTitle);
        todo.setDescription(description);
        todo.setCompleted(completed);
        return todoRepository.save(todo);
    }

    public void delete(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new IllegalArgumentException("TODOが見つかりません: " + id);
        }
        todoRepository.deleteById(id);
    }
}
