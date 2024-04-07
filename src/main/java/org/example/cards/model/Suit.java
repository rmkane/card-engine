package org.example.cards.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum Suit {
    HEARTS("♥"),
    DIAMONDS("♦"),
    CLUBS("♣"),
    SPADES("♠");

    private final String symbol;

    public static Optional<Suit> fromSymbol(String symbol) {
        for (Suit suit : Suit.values()) {
            if (suit.getSymbol().equals(symbol)) {
                return Optional.of(suit);
            }
        }
        return Optional.empty();
    }
}
