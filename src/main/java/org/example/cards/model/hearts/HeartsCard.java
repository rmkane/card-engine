package org.example.cards.model.hearts;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.example.cards.model.Card;
import org.example.cards.model.Rank;
import org.example.cards.model.Suit;
import org.example.cards.util.CardUtils;

@RequiredArgsConstructor
@Value
public class HeartsCard implements Card {
    Suit suit;
    Rank rank;

    int getValue() {
        if (suit == Suit.HEARTS) return 1;
        if (suit == Suit.SPADES && rank == Rank.QUEEN) return 13;
        return 0;
    }

    @Override
    public String toString() {
        return CardUtils.formatCard(this);
    }
}
