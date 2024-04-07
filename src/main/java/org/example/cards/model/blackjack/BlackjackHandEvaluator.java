package org.example.cards.model.blackjack;

import org.example.cards.model.CardHandEvaluator;
import org.example.cards.util.BlackjackUtils;

public class BlackjackHandEvaluator implements CardHandEvaluator<BlackjackCard, BlackjackHand, Integer> {
    @Override
    public Integer evaluateHand(BlackjackHand hand) {
        return hand.getCards().stream().reduce(0, BlackjackUtils::getBestValue, Integer::sum);
    }

    public boolean isBust(BlackjackHand hand) {
        return BlackjackUtils.isBust(evaluateHand(hand));
    }

    public BlackjackHandType getHandType(BlackjackHand hand) {
        return BlackjackUtils.getHandType(evaluateHand(hand));
    }
}

