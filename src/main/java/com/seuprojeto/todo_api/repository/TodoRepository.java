package com.seuprojeto.todo_api.repository;

import com.seuprojeto.todo_api.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    // versão case-insensitive
    List<Todo> findByTituloContainingIgnoreCase(String palavra);

    // versão case-sensitive (se precisar)
    List<Todo> findByTituloContaining(String palavra);

    // título + status
    List<Todo> findByTituloContainingIgnoreCaseAndConcluida(String palavra, boolean concluida);

    List<Todo> findByConcluida(boolean concluida);

    List<Todo> findByDataCriacaoBetween(LocalDateTime inicio, LocalDateTime fim);
}
