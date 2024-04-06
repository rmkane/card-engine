package org.example.cards.model.blackjack;

import lombok.Value;
import org.example.cards.model.Card;
import org.example.cards.model.Rank;
import org.example.cards.model.Suit;
import org.example.cards.util.BlackjackUtils;
import org.example.cards.util.CardUtils;

@Value
public class BlackjackCard implements Card {
    Suit suit;
    Rank rank;

    public int getValue() {
        return BlackjackUtils.getCardValue(this);
    }

    @Override
    public String toString() {
        return CardUtils.formatCard(this);
    }
}
