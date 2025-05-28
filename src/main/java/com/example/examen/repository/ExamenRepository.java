package com.example.examen.repository;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.examen.model.Examen;

public interface ExamenRepository extends JpaRepository<Examen, Long> {
  List<Examen> findByTipo(String tipo);

  List<Examen> findByCreadoEnAfter(LocalDateTime from);

}
