package com.theodore.javingthontest.repository;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Repository
public class CardRepository {

  private final ObjectMapper mapper;
  private Map<String, JsonNode> bySimplified = Map.of();

  public CardRepository(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  @PostConstruct
  void load() throws Exception {
    try (InputStream in = new ClassPathResource("com/theodore/javingthontest/dict/hsk.json").getInputStream()) {
      List<JsonNode> list = mapper.readValue(in, new TypeReference<List<JsonNode>>() {});
      bySimplified = list.stream().collect(Collectors.toUnmodifiableMap(
          n -> n.get("simplified").asString(),
          n -> n,
          (a, b) -> a));
    }
  }

  public Optional<JsonNode> findBySimplified(String word) {
    return Optional.ofNullable(bySimplified.get(word));
  }

  public List<JsonNode> findAll() {
    return List.copyOf(bySimplified.values());
  }
}