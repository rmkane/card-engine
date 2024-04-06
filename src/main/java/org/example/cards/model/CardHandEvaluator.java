package org.example.cards.model;

import java.util.List;

public interface CardHandEvaluator<C extends Card, T> {
    T evaluateHand(List<C> hand);
}
