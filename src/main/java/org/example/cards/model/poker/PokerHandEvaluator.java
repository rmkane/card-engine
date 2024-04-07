package org.example.cards.model.poker;

import org.example.cards.model.Card;
import org.example.cards.model.CardHandEvaluator;
import org.example.cards.model.Rank;
import org.example.cards.model.Suit;
import org.example.cards.util.CardUtils;

import java.util.Map;
import java.util.stream.Collectors;

public class PokerHandEvaluator implements CardHandEvaluator<PokerCard, PokerHand, PokerHandType> {
    @Override
    public PokerHandType evaluateHand(PokerHand hand) {
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

    private static boolean isRoyalFlush(PokerHand hand) {
        return isStraightFlush(hand) && hand.getCards().getLast().getRank() == Rank.ACE;
    }

    private static boolean isStraightFlush(PokerHand hand) {
        return isStraight(hand) && isFlush(hand);
    }

    private static boolean isFourOfAKind(PokerHand hand) {
        return getRankCounts(hand).containsValue(4);
    }

    private static boolean isFullHouse(PokerHand hand) {
        Map<Rank, Integer> rankCounts = getRankCounts(hand);
        return rankCounts.containsValue(3) && rankCounts.containsValue(2);
    }

    private static boolean isFlush(PokerHand hand) {
        final Suit firstSuit = hand.getCards().getFirst().getSuit();
        return hand.getCards().stream().allMatch(card -> card.getSuit() == firstSuit);
    }

    private static boolean isStraight(PokerHand hand) {
        for (int i = 0; i < hand.getCardCount() - 1; i++) {
            if (hand.getCards().get(i).getRank().ordinal() + 1 != hand.getCards().get(i + 1).getRank().ordinal()) {
                return false;
            }
        }
        return true;
    }

    private static boolean isThreeOfAKind(PokerHand hand) {
        Map<Rank, Integer> rankCounts = getRankCounts(hand);
        return rankCounts.containsValue(3) && !rankCounts.containsValue(2);
    }

    private static boolean isTwoPair(PokerHand hand) {
        return getRankCounts(hand).values().stream().filter(count -> count == 2).count() == 2;
    }

    private static boolean isOnePair(PokerHand hand) {
        Map<Rank, Integer> rankCounts = getRankCounts(hand);
        return rankCounts.containsValue(2) && !rankCounts.containsValue(3);
    }

    private static Map<Rank, Integer> getRankCounts(PokerHand hand) {
        return hand.getCards().stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.summingInt(e -> 1)));
    }
}