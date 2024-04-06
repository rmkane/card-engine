package org.example.cards.model;

public interface Deck<T extends Card> {
    void shuffle();

    T deal();
}