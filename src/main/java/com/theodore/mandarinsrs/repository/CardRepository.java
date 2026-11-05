package com.theodore.mandarinsrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theodore.mandarinsrs.entity.Card;


@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

}