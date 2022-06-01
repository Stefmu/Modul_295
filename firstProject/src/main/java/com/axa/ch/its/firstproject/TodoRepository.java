    package com.axa.ch.its.firstproject;
    
    import org.springframework.stereotype.Repository;

    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;
    
    
    @Repository
    public class TodoRepository {
        private final List<Todo> todos;

        public TodoRepository() {
            todos = new ArrayList<>();
            todos.add(new Todo(1, 1L, "delectus aut autem", false, LocalDateTime.parse("2022-01-03T10:00:00")));
            todos.add(new Todo(2, 2L, "quis ut nam facilis et officia qui", false, LocalDateTime.parse("2017-03-03T10:17:30")));
            todos.add(new Todo(3, 3L, "fugiat veniam minus", false, LocalDateTime.parse("2021-06-03T10:15:05")));
        }
        public List<Todo> findAll() {
            return todos;
        }
        public Optional<Todo> findById(long id) {
            for (Todo todo : todos) {
                if (todo.getUserId() == id) {
                    return Optional.of(todo);
                }
            }
            return null;
        }
        public List<Todo> deleteById(long id) {
            todos.removeIf(e -> e.getId() == id);
            return todos;
        }
        public List<Todo> save(Todo todo) {
            todos.add(todo);
            return todos;
        }
        public List<Todo> updateTodo(Long id, Todo todo) {
            if(findById(id).isPresent()){
                return save(todo);
            }else{
                Optional<Todo> oldTodo = todos.stream().filter(t -> t.getId() == id).findFirst();
                todos.set(todos.indexOf(oldTodo.get()), todo);
                return todos;
            }
        }
    
        public Todo complete(Long id) {
            findById(id).get().setCompleted(true);
            return findById(id).get();
        }
    
        public Todo close(Long id) {
            findById(id).get().setCompleted(false);
            return findById(id).get();
        }

        public Optional<Todo> updateDeadline(Long id, LocalDateTime deadline) {
            Optional<Todo> todoToChange = findById(id);
            todoToChange.ifPresent(todo -> todo.setDeadline(deadline));
            return todoToChange;
        }

    }