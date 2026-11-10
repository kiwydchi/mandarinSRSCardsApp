package com.theodore.mandarinsrs.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theodore.mandarinsrs.entity.Card;
import com.theodore.mandarinsrs.service.CardService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewsController {

    public record ReviewRequest(
        String simplified,
        int level,
        Boolean grade,
        String reviewedAt
    ) {}

    private final CardService cardService;

    public ReviewsController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping ("/save")
    public void save(@RequestBody ReviewRequest req, Authentication auth) {
        String username = auth.getName();
        Card card = new Card(username, req.simplified, req.grade, req.level, req.reviewedAt);
        cardService.save(card);
    }
}