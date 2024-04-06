package org.example.cards.model.poker;

import org.example.cards.model.Card;
import org.example.cards.model.Rank;
import org.example.cards.model.Suit;
import org.example.cards.util.CardUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokerHandEvaluator {
    public static PokerHandType evaluateHand(List<PokerCard> hand) {
        // Sort the hand by rank
        CardUtils.sortHandByRankThenSuit(hand);

        // Check for various hand types, starting from the strongest
        if (isRoyalFlush(hand)) return PokerHandType.ROYAL_FLUSH;
        else if (isStraightFlush(hand)) return PokerHandType.STRAIGHT_FLUSH;
        else if (isFourOfAKind(hand)) return PokerHandType.FOUR_OF_A_KIND;
        else if (isFullHouse(hand)) return PokerHandType.FULL_HOUSE;
        else if (isFlush(hand)) return PokerHandType.FLUSH;
        else if (isStraight(hand)) return PokerHandType.STRAIGHT;
        else if (isThreeOfAKind(hand)) return PokerHandType.THREE_OF_A_KIND;
        else if (isTwoPair(hand)) return PokerHandType.TWO_PAIR;
        else if (isOnePair(hand)) return PokerHandType.ONE_PAIR;
        return PokerHandType.HIGH_CARD;
    }

    private static boolean isRoyalFlush(List<PokerCard> hand) {
        return isStraightFlush(hand) && hand.getLast().getRank() == Rank.ACE;
    }

    private static boolean isStraightFlush(List<PokerCard> hand) {
        return isStraight(hand) && isFlush(hand);
    }

    private static boolean isFourOfAKind(List<PokerCard> hand) {
        return getRankCounts(hand).containsValue(4);
    }

    private static boolean isFullHouse(List<PokerCard> hand) {
        Map<Rank, Integer> rankCounts = getRankCounts(hand);
        return rankCounts.containsValue(3) && rankCounts.containsValue(2);
    }

    private static boolean isFlush(List<PokerCard> hand) {
        final Suit firstSuit = hand.getFirst().getSuit();
        return hand.stream().allMatch(card -> card.getSuit() == firstSuit);
    }

    private static boolean isStraight(List<PokerCard> hand) {
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getRank().ordinal() + 1 != hand.get(i + 1).getRank().ordinal()) {
                return false;
            }
        }
        return true;
    }

    private static boolean isThreeOfAKind(List<PokerCard> hand) {
        Map<Rank, Integer> rankCounts = getRankCounts(hand);
        return rankCounts.containsValue(3) && !rankCounts.containsValue(2);
    }

    private static boolean isTwoPair(List<PokerCard> hand) {
        return getRankCounts(hand).values().stream().filter(count -> count == 2).count() == 2;
    }

    private static boolean isOnePair(List<PokerCard> hand) {
        Map<Rank, Integer> rankCounts = getRankCounts(hand);
        return rankCounts.containsValue(2) && !rankCounts.containsValue(3);
    }

    private static Map<Rank, Integer> getRankCounts(List<PokerCard> hand) {
        return hand.stream().collect(Collectors.groupingBy(Card::getRank, Collectors.summingInt(e -> 1)));
    }
}