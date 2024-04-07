package org.example.cards.engine;

import org.example.cards.model.Card;
import org.example.cards.model.CardHand;
import org.example.cards.model.CardPlayer;

import java.util.Optional;

public interface CardEngine<C extends Card, H extends CardHand<C>, P extends CardPlayer<C, H>> {
    void addPlayer(P player);

    void shuffleDeck();

    void deal();

    void showHands();

    Optional<P> determineWinner();
}
