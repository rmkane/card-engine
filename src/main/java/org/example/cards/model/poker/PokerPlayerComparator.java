package org.example.cards.model.poker;

import java.util.Comparator;

public class PokerPlayerComparator implements Comparator<PokerPlayer> {
    private static final PokerHandComparator handComparator = new PokerHandComparator();

    @Override
    public int compare(PokerPlayer player1, PokerPlayer player2) {
        return handComparator.compare(player1.getHand(), player2.getHand());
    }
}
