package org.example.cards.model.poker;

import lombok.Getter;
import lombok.Setter;
import org.example.cards.model.CardPlayer;
import org.example.cards.util.CardUtils;
import org.example.cards.util.PokerUtils;

import java.util.List;

@Getter
public class PokerPlayer implements CardPlayer<PokerCard, PokerHand> {
    private final static PokerHandEvaluator handEvaluator = new PokerHandEvaluator();

    private final String name;
    private final PokerHand hand;

    @Setter
    private boolean dealer;

    public PokerPlayer(String name) {
        this.name = name;
        this.hand = new PokerHand();
    }

    @Override
    public void acceptCard(PokerCard card) {
        hand.addCard(card);
    }

    @Override
    public void discardCard(PokerCard card) {
        hand.removeCard(card);
    }

    @Override
    public void sortHand() {
        hand.sort();
    }

    @Override
    public int getScore() {
        return evaluateHand().ordinal();
    }

    @Override
    public String showHand() {
        return CardUtils.formatHand(hand);
    }

    public PokerHandType evaluateHand() {
        return handEvaluator.evaluateHand(hand);
    }

    public List<PokerCard> decideCardsToDiscard() {
        return PokerUtils.decideCardsToDiscard(hand).stream()
                .limit(3)
                .toList();
    }

    public int getHandRank() {
        return this.evaluateHand().ordinal();
    }

    @Override
    public String toString() {
        return String.format("%s: %s (%s)", name, showHand(), evaluateHand().getDescription());
    }
}
