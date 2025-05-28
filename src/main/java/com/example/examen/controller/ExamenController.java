package com.example.examen.controller;
import com.example.examen.model.Examen;
import com.example.examen.repository.ExamenRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/examen")
public class ExamenController {
  private final ExamenRepository repo;
  public ExamenController(ExamenRepository repo) { this.repo = repo; }

  @GetMapping("/{tipo}")
  public List<Examen> getByTipo(@PathVariable String tipo) {
    return repo.findByTipo(tipo);
  }
}
