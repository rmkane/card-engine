package org.example.cards.model.hearts;

import lombok.Getter;
import org.example.cards.model.CardPlayer;
import org.example.cards.util.CardUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public class HeartsPlayer implements CardPlayer<HeartsCard> {
    private final static HeartsHandEvaluator handEvaluator = new HeartsHandEvaluator();

    private final String name;
    private final List<HeartsCard> hand;

    public HeartsPlayer(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    @Override
    public void drawCard(HeartsCard card) {
        hand.add(card);
    }

    @Override
    public void discardCard(HeartsCard card) {
        hand.remove(card);
    }

    @Override
    public void sortHand() {
        CardUtils.sortHandBySuitThenRank(hand);
    }

    @Override
    public int getScore() {
        return handEvaluator.evaluateHand(hand);
    }

    @Override
    public String showHand() {
        return CardUtils.formatHand(hand);
    }

    public Integer evaluateHand() {
        return handEvaluator.evaluateHand(hand);
    }

    @Override
    public String toString() {
        return String.format("%s: %s (%d)", name, showHand(), getScore());
    }
}
