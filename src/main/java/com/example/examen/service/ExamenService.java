package com.example.examen.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.examen.model.Examen;
import com.example.examen.repository.ExamenRepository;

@Service
public class ExamenService {
  private final ExamenRepository repo;
  public ExamenService(ExamenRepository repo) { this.repo = repo; }

  public List<Examen> findRecent(LocalDateTime from) {
    return repo.findByCreadoEnAfter(from);
  }

  public List<Examen> findAnomalies(LocalDateTime from) {
    return findRecent(from).stream()
      .filter(e -> e.getResultado().toLowerCase().contains("anomaly"))
      .collect(Collectors.toList());
  }
}
