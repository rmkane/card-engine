package org.example.cards.model.blackjack;

import org.example.cards.model.Deck;
import org.example.cards.util.CardUtils;

import java.util.ArrayList;
import java.util.List;

public class BlackjackDeck implements Deck<BlackjackCard> {
    private final List<BlackjackCard> cards;

    public BlackjackDeck() {
        cards = new ArrayList<>(CardUtils.populateDeck(BlackjackCard.class));
    }

    @Override
    public void shuffle() {
        CardUtils.shuffle(cards);
    }

    @Override
    public BlackjackCard deal() {
        return cards.removeFirst();
    }
}