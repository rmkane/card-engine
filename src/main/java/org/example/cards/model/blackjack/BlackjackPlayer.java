package org.example.cards.model.blackjack;

import lombok.Getter;
import org.example.cards.model.CardPlayer;
import org.example.cards.util.CardUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BlackjackPlayer implements CardPlayer<BlackjackCard> {
    private final static BlackjackHandEvaluator handEvaluator = new BlackjackHandEvaluator();

    private final String name;
    private final List<BlackjackCard> hand;

    public BlackjackPlayer(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    @Override
    public void acceptCard(BlackjackCard card) {
        hand.add(card);
    }

    @Override
    public void discardCard(BlackjackCard card) {
        throw new UnsupportedOperationException("Cannot discard cards in Blackjack");
    }

    @Override
    public void sortHand() {
        // no-op
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
        return String.format("%s: %s (%s)", name, showHand(), handEvaluator.getHandType(hand));
    }

    public boolean didBust() {
        return handEvaluator.isBust(hand);
    }
}
