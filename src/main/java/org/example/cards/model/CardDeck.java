package org.example.cards.model;

public interface CardDeck<C extends Card> {
    void shuffle();

    C deal();
}