package com.example.examen.repository;
import com.example.examen.model.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExamenRepository extends JpaRepository<Examen, Long> {
  List<Examen> findByTipo(String tipo);
}
