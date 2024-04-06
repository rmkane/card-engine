package org.example.cards.model.hearts;

import org.example.cards.model.CardHandEvaluator;

import java.util.List;

public class HeartsHandEvaluator implements CardHandEvaluator<HeartsCard, Integer> {
    @Override
    public Integer evaluateHand(List<HeartsCard> hand) {
        return hand.stream()
                .mapToInt(HeartsCard::getValue)
                .sum();
    }
}
