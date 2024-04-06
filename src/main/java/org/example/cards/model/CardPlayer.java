package org.example.cards.model;

import java.util.List;

public interface CardPlayer<C extends Card> {
    String getName();

    List<C> getHand();

    void drawCard(C card);

    void discardCard(C card);

    void sortHand();

    int getScore();

    String showHand();
}
