package org.example.cards.model.hearts;

import org.example.cards.model.Deck;
import org.example.cards.util.CardUtils;

import java.util.ArrayList;
import java.util.List;

public class HeartsDeck implements Deck<HeartsCard> {
    private final List<HeartsCard> cards;

    public HeartsDeck() {
        cards = new ArrayList<>(CardUtils.populateDeck(HeartsCard.class));
    }


    @Override
    public void shuffle() {
        CardUtils.shuffle(cards);
    }

    @Override
    public HeartsCard deal() {
        return cards.removeFirst();
    }
}
