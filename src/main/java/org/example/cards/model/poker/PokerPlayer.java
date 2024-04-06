package org.example.cards.model.poker;

import lombok.Getter;
import org.example.cards.model.CardPlayer;
import org.example.cards.util.CardUtils;
import org.example.cards.util.PokerUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PokerPlayer implements CardPlayer<PokerCard> {
    private final String name;
    private final List<PokerCard> hand;

    public PokerPlayer(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    @Override
    public void drawCard(PokerCard card) {
        hand.add(card);
    }

    @Override
    public void discardCard(PokerCard card) {
        hand.remove(card);
    }

    @Override
    public void sortHand() {
        CardUtils.sortHandBySuitThenRank(hand);
    }

    @Override
    public int getScore() {
        return PokerHandEvaluator.evaluateHand(hand).ordinal();
    }

    @Override
    public String showHand() {
        return CardUtils.formatHand(hand);
    }

    public PokerHandType evaluateHand() {
        return PokerHandEvaluator.evaluateHand(hand);
    }

    public List<PokerCard> decideCardsToDiscard() {
        return PokerUtils.decideCardsToDiscard(hand).stream()
                .limit(3)
                .toList();
    }

    public int getHandRank() {
        return this.evaluateHand().ordinal();
    }

    @Override
    public String toString() {
        return String.format("%s: %s (%s)", name, showHand(), evaluateHand().getDescription());
    }
}
