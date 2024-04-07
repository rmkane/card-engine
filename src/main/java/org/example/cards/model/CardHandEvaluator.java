package org.example.cards.model;

public interface CardHandEvaluator<C extends Card, H extends CardHand<C>, T> {
    T evaluateHand(H hand);
}
