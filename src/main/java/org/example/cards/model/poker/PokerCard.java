package org.example.cards.model.poker;

import lombok.Value;
import org.example.cards.model.Card;
import org.example.cards.model.Rank;
import org.example.cards.model.Suit;
import org.example.cards.util.CardUtils;

@Value
public class PokerCard implements Card {
    Suit suit;
    Rank rank;

    @Override
    public String toString() {
        return CardUtils.formatCard(this);
    }
}
