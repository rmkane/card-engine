package org.example.cards.model.hearts;

import lombok.Value;
import org.example.cards.model.Card;
import org.example.cards.model.Rank;
import org.example.cards.model.Suit;
import org.example.cards.util.CardUtils;
import org.example.cards.util.HeartsUtils;

@Value
public class HeartsCard implements Card {
    Suit suit;
    Rank rank;

    int getValue() {
        return HeartsUtils.getCardValue(this);
    }

    @Override
    public String toString() {
        return CardUtils.formatCard(this);
    }

    public static HeartsCard fromCardSymbol(String cardSymbol) {
        return CardUtils.fromCardSymbol(cardSymbol, HeartsCard.class);
    }
}
