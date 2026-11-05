package com.theodore.mandarinsrs.controllers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.theodore.mandarinsrs.service.CardService;

import tools.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api/cards")
public class RestAPIController {

  private final CardService cardService;

  public RestAPIController(CardService cardService) {
    this.cardService = cardService;
  }

  @GetMapping("/{simplified}")
  public ResponseEntity<JsonNode> getWord(@PathVariable String simplified) {
    return cardService.findBySimplified(simplified)
        .map(body -> ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(Duration.ofDays(30)).cachePublic())
            .body(body))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/random")
  public ResponseEntity<List<JsonNode>> random(@RequestParam(defaultValue = "20") int count) {

    List<JsonNode> all = cardService.findAll();
    int n = Math.max(1, Math.min(count, 100));
    if (n >= all.size()) {
      return ResponseEntity.ok(all);
    }
    List<JsonNode> copy = new ArrayList<>(all);
    Collections.shuffle(copy, ThreadLocalRandom.current());
    return ResponseEntity.ok()
        .cacheControl(CacheControl.noStore())
        .body(copy.subList(0, n));
  }

  @GetMapping
  public List<JsonNode> getAll() {
    return cardService.findAll();
  }
}