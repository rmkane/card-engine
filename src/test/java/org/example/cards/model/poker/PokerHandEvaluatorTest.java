package org.example.cards.model.poker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PokerHandEvaluatorTest {
    private final PokerHandEvaluator handEvaluator = new PokerHandEvaluator();
    private final Comparator<PokerHand> handComparator = new PokerHandComparator();

    @Test
    void testEvaluateHand() {
        List<PokerHand> hands = List.of(
                        "T♠ J♠ Q♠ K♠ A♠", // Royal Flush
                        "9♠ T♠ J♠ Q♠ K♠", // Straight Flush
                        "8♠ 9♠ T♠ J♠ Q♠", // Straight Flush
                        "7♠ 8♠ 9♠ T♠ J♠", // Straight Flush
                        "6♠ 7♠ 8♠ 9♠ T♠", // Straight Flush
                        "A♠ A♣ A♦ A♥ 2♠", // Four of a Kind
                        "2♠ 2♣ 2♦ 3♠ 3♣", // Full House
                        "2♠ 4♠ 6♠ 8♠ T♠", // Flush
                        "2♥ 3♠ 4♥ 5♠ 6♥", // Straight
                        "A♠ A♣ A♦ 2♠ 3♠", // Three of a Kind
                        "A♠ A♣ 2♠ 2♣ 3♠", // Two Pair
                        "A♠ A♣ 2♠ 3♠ 4♠", // One Pair
                        "A♥ 2♠ 3♠ 4♠ 5♠"  // High Card

                ).stream()
                .map(hand -> new ArrayList<>(PokerCard.fromCardSymbols(hand)))
                .map(PokerHand::new)
                .sorted(handComparator)
                .toList();

        for (PokerHand hand : hands) {
            PokerHandType handType = handEvaluator.evaluateHand(hand);
            System.out.printf("%s (%s)%n", hand, handType);
        }
    }
}
