package org.example.cards.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum Rank {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("T"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    private final String symbol;

    public static Optional<Rank> fromSymbol(String symbol) {
        for (Rank rank : Rank.values()) {
            if (rank.getSymbol().equals(symbol)) {
                return Optional.of(rank);
            }
        }
        return Optional.empty();
    }
}

