package org.example.cards.model.hearts;

import lombok.Getter;
import lombok.Setter;
import org.example.cards.model.CardPlayer;
import org.example.cards.util.CardUtils;

@Getter
public class HeartsPlayer implements CardPlayer<HeartsCard, HeartsHand> {
    private final static HeartsHandEvaluator handEvaluator = new HeartsHandEvaluator();

    private final String name;
    private final HeartsHand hand;

    @Setter
    private boolean dealer;

    public HeartsPlayer(String name) {
        this.name = name;
        this.hand = new HeartsHand();
    }

    @Override
    public void acceptCard(HeartsCard card) {
        hand.addCard(card);
    }

    @Override
    public void discardCard(HeartsCard card) {
        hand.removeCard(card);
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
