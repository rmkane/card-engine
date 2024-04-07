package org.example.cards.model;

import java.util.List;

public interface CardHand<C extends Card> {
    List<C> getCards();

    int getCardCount();

    void addCard(C card);

    void removeCard(C card);

    void sort();
}
