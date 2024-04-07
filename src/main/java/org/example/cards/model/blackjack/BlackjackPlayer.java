package org.example.cards.model.blackjack;

import lombok.Getter;
import lombok.Setter;
import org.example.cards.model.CardPlayer;
import org.example.cards.util.CardUtils;

@Getter
public class BlackjackPlayer implements CardPlayer<BlackjackCard, BlackjackHand> {
    private final static BlackjackHandEvaluator handEvaluator = new BlackjackHandEvaluator();

    private final String name;
    private final BlackjackHand hand;

    @Setter
    private boolean dealer;

    public BlackjackPlayer(String name) {
        this.name = name;
        this.hand = new BlackjackHand();
    }

    @Override
    public void acceptCard(BlackjackCard card) {
        hand.addCard(card);
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
