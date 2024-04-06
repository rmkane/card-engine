package org.example.cards.util;

import org.example.cards.model.Card;
import org.example.cards.model.Rank;
import org.example.cards.model.poker.PokerCard;
import org.example.cards.model.poker.PokerHandEvaluator;
import org.example.cards.model.poker.PokerHandType;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokerUtils {
    private final static PokerHandEvaluator handEvaluator = new PokerHandEvaluator();

    public static List<PokerCard> decideCardsToDiscard(List<PokerCard> hand) {
        PokerHandType handType = handEvaluator.evaluateHand(hand);
        Map<Rank, Long> rankCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

        return switch (handType) {
            case HIGH_CARD -> discardHighCard(hand);
            case ONE_PAIR -> discardPair(hand, rankCounts);
            case TWO_PAIR -> discardTwoPair(hand, rankCounts);
            case THREE_OF_A_KIND -> discardThreeOfAKind(hand, rankCounts);
            case STRAIGHT,
                 FLUSH,
                 FULL_HOUSE,
                 FOUR_OF_A_KIND,
                 STRAIGHT_FLUSH,
                 ROYAL_FLUSH -> Collections.emptyList();
        };
    }

    private static List<PokerCard> discardHighCard(List<PokerCard> hand) {
        final Card highestCard = hand.stream()
                .max(Comparator.comparing(Card::getRank))
                .orElse(null);
        // Discard all but the highest card
        return hand.stream()
                .filter(card -> !card.equals(highestCard))
                .toList();
    }

    private static List<PokerCard> discardPair(List<PokerCard> hand, Map<Rank, Long> rankCounts) {
        final Rank pairRank = rankCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey).findFirst()
                .orElse(null);
        // Discard the three cards that are not part of the pair
        return hand.stream()
                .filter(card -> !card.getRank().equals(pairRank))
                .toList();
    }

    private static List<PokerCard> discardTwoPair(List<PokerCard> hand, Map<Rank, Long> rankCounts) {
        final List<Rank> pairRanks = rankCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey)
                .toList();
        // Discard the fifth card that is not part of the pairs
        return hand.stream()
                .filter(card -> !pairRanks.contains(card.getRank()))
                .toList();
    }

    private static List<PokerCard> discardThreeOfAKind(List<PokerCard> hand, Map<Rank, Long> rankCounts) {
        final Rank threeOfAKindRank = rankCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == 3)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        // Discard the two cards that are not part of the three of a kind
        return hand.stream()
                .filter(card -> !card.getRank().equals(threeOfAKindRank))
                .toList();
    }
}
