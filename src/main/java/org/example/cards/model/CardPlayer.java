package org.example.cards.model;

import java.util.List;

public interface CardPlayer<T extends Card> {
    String getName();

    List<T> getHand();

    void drawCard(T card);

    void discardCard(T card);

    void sortHand();

    int getScore();

    String showHand();
}
