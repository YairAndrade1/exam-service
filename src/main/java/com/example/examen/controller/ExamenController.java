package com.example.examen.controller;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.examen.model.Examen;
import com.example.examen.repository.ExamenRepository;

@RestController
@RequestMapping("/examen")
public class ExamenController {
  private final ExamenRepository repo;
  public ExamenController(ExamenRepository repo) {
    this.repo = repo;
  }

  // 1) Exámenes del último mes (o periodo dado)
  @GetMapping("/recent")
  public List<Examen> getRecent(
      @RequestParam("from")
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
      LocalDateTime from) {
    return repo.findByCreadoEnAfter(from);
  }

  // 2) Sólo anomalías
  @GetMapping("/recent-anomalies")
  public List<Examen> getRecentAnomalies(
      @RequestParam("from")
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
      LocalDateTime from) {
    return repo.findByCreadoEnAfter(from).stream()
               .filter(e -> e.getResultado().toLowerCase().contains("anomaly"))
               .collect(Collectors.toList());
  }

  // (Opcional) Para agrupar por tipo, podrías devolver un Map<String,Long>

  @GetMapping("/{tipo}")
  public List<Examen> getByTipo(@PathVariable String tipo) {
    return repo.findByTipo(tipo);
  }

  @GetMapping("/recent-anomalies/grouped")
  public Map<String, Long> getAnomaliesGrouped(
      @RequestParam("from")
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
      LocalDateTime from) {

    return repo.findByCreadoEnAfter(from).stream()
      .filter(e -> e.getResultado().toLowerCase().contains("anomaly"))
      .collect(Collectors.groupingBy(Examen::getTipo, Collectors.counting()));
  }

}
