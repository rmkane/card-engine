package org.example.cards.util;

import org.example.cards.model.Rank;
import org.example.cards.model.Suit;
import org.example.cards.model.hearts.HeartsCard;

public class HeartsUtils {
    public static int getCardValue(HeartsCard card) {
        if (card.getSuit() == Suit.HEARTS) return 1;
        if (card.getSuit() == Suit.SPADES && card.getRank() == Rank.QUEEN) return 13;
        return 0;
    }
}
