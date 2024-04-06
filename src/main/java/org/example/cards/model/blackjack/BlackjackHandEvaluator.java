package org.example.cards.model.blackjack;

import org.example.cards.model.CardHandEvaluator;
import org.example.cards.util.BlackjackUtils;

import java.util.List;

public class BlackjackHandEvaluator implements CardHandEvaluator<BlackjackCard, Integer> {
    @Override
    public Integer evaluateHand(List<BlackjackCard> hand) {
        return hand.stream().reduce(0, BlackjackUtils::getBestValue, Integer::sum);
    }

    public boolean isBust(List<BlackjackCard> hand) {
        return BlackjackUtils.isBust(evaluateHand(hand));
    }

    public BlackjackHandType getHandType(List<BlackjackCard> hand) {
        return BlackjackUtils.getHandType(evaluateHand(hand));
    }
}

