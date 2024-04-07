package org.example.cards.model.hearts;

import org.example.cards.model.CardHandEvaluator;

public class HeartsHandEvaluator implements CardHandEvaluator<HeartsCard, HeartsHand, Integer> {
    @Override
    public Integer evaluateHand(HeartsHand hand) {
        return hand.getCards().stream()
                .mapToInt(HeartsCard::getValue)
                .sum();
    }
}
