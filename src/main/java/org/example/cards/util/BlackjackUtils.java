package org.example.cards.util;

import org.example.cards.model.Rank;
import org.example.cards.model.blackjack.BlackjackCard;
import org.example.cards.model.blackjack.BlackjackHandType;

public class BlackjackUtils {
    public static final int BLACKJACK = 21;

    public static boolean isOver(int currentTotal) {
        // Check if the total exceeds 21 when considering an Ace as 11 points.
        return isBust(currentTotal + BlackjackUtils.getCardValue(Rank.ACE));
    }

    public static boolean isBust(int total) {
        return total > BLACKJACK;
    }

    public static boolean isAce(BlackjackCard card) {
        return card.getRank() == Rank.ACE;
    }

    public static int getBestValue(int currentTotal, BlackjackCard card) {
        return currentTotal + (BlackjackUtils.isAce(card) && BlackjackUtils.isOver(currentTotal) ? 1 : card.getValue());
    }

    public static BlackjackHandType getHandType(int score) {
        if (score < BLACKJACK) return BlackjackHandType.UNDER;
        else if (score == BLACKJACK) return BlackjackHandType.BLACKJACK;
        else return BlackjackHandType.BUST;
    }

    public static int getCardValue(BlackjackCard card) {
        return getCardValue(card.getRank());
    }

    public static int getCardValue(Rank rank) {
        return switch (rank) {
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
            case SIX -> 6;
            case SEVEN -> 7;
            case EIGHT -> 8;
            case NINE -> 9;
            case TEN, JACK, QUEEN, KING -> 10;
            case ACE -> 11;
        };
    }
}
