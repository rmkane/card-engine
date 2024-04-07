package org.example.cards.model.poker;

import org.example.cards.model.CardDeck;
import org.example.cards.util.CardUtils;

import java.util.ArrayList;
import java.util.List;

public class PokerDeck implements CardDeck<PokerCard> {
    private final List<PokerCard> cards;

    public PokerDeck() {
        cards = new ArrayList<>(CardUtils.populateDeck(PokerCard.class));
    }

    @Override
    public void shuffle() {
        CardUtils.shuffle(cards);
    }

    @Override
    public PokerCard deal() {
        return cards.removeFirst();
    }
}