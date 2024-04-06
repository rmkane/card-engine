package org.example.cards.model;

public interface Deck<C extends Card> {
    void shuffle();

    C deal();
}