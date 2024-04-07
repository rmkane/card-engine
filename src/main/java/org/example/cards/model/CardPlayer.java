package org.example.cards.model;

public interface CardPlayer<C extends Card, H extends CardHand<C>> {
    String getName();

    CardHand getHand();

    void setDealer(boolean dealer);

    boolean isDealer();

    void acceptCard(C card);

    void discardCard(C card);

    void sortHand();

    int getScore();

    String showHand();
}
