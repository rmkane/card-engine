package org.example.cards.engine;

import org.example.cards.model.Card;
import org.example.cards.model.CardPlayer;

import java.util.Optional;

public interface CardEngine<C extends Card, P extends CardPlayer<C>> {
    void addPlayer(P player);

    void shuffleDeck();

    void deal();
    
    void showHands();

    Optional<P> determineWinner();
}
