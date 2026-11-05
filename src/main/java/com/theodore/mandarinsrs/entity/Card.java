package com.theodore.mandarinsrs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "card_progress")
public class Card {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, length=30)
    private String username;

    @Column(name = "word", nullable = false, length=10)
    private String word;

    @Column(name = "known", nullable = false)
    private Boolean known;

    @Column(name = "level", nullable = false, length=1)
    private int level;

    @Column(name = "reviewed_at", nullable = false)
    private String reviewedAt;

    public Card(String username, String word, Boolean known, int level, String reviewedAt) {
        this.username = username;
        this.word = word;
        this.known = known;
        this.level = level;
        this.reviewedAt = reviewedAt;
    }

    public Card(String word, Boolean known, int level, String reviewedAt) {
        this.word = word;
        this.known = known;
        this.level = level;
        this.reviewedAt = reviewedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Boolean getKnown() {
        return known;
    }

    public void setKnown(Boolean known) {
        this.known = known;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getReviewedAt() {
        return reviewedAt;
    }

    public void setReviewedAt(String reviewedAt) {
        this.reviewedAt = reviewedAt;
    }
}
