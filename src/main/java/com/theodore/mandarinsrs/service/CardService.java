package com.theodore.mandarinsrs.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import com.theodore.mandarinsrs.entity.Card;
import com.theodore.mandarinsrs.repository.CardRepository;

@Service 
public class CardService {
    private final CardRepository cardRepository;
    private final ObjectMapper mapper;
    private Map<String, JsonNode> bySimplified = Map.of();

    public CardService(CardRepository cardRepository, ObjectMapper mapper) {
        this.cardRepository = cardRepository;
        this.mapper = mapper;
    }

    @PostConstruct
    void load() throws Exception {
        try (InputStream in = new ClassPathResource("com/theodore/mandarinsrs/dict/hsk.json").getInputStream()) {
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

    public void save(Card card) {
        cardRepository.save(card);
    }
}
