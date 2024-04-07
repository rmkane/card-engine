package org.example.cards.model.poker;

import org.example.cards.util.CardUtils;

import java.util.Comparator;
import java.util.List;

public class PokerHandComparator implements Comparator<PokerHand> {
    @Override
    public int compare(PokerHand hand1, PokerHand hand2) {
        // Compare hand types first
        int handTypeComparison = compareHandTypes(hand1, hand2);
        if (handTypeComparison != 0) return -handTypeComparison;
        return compareHandsByRank(hand1, hand2);
    }

    private int compareHandTypes(PokerHand player1, PokerHand player2) {
        return player1.evaluateHand().compareTo(player2.evaluateHand());
    }

    private int compareHandsByRank(PokerHand hand1, PokerHand hand2) {
        // Sort the hands by rank in descending order
        List<PokerCard> sortedHand1 = CardUtils.sortedByRankDescending(hand1);
        List<PokerCard> sortedHand2 = CardUtils.sortedByRankDescending(hand2);

        // Compare the sorted hands card by card
        for (int i = 0; i < sortedHand1.size(); i++) {
            int cardComparison = compareHandsAtIndex(sortedHand1, sortedHand2, i);
            if (cardComparison != 0) return -cardComparison;
        }

        return 0; // Hands are equal
    }

    public int compareHandsAtIndex(List<PokerCard> hand1, List<PokerCard> hand2, int index) {
        return hand1.get(index).getRank().compareTo(hand2.get(index).getRank());
    }
}


