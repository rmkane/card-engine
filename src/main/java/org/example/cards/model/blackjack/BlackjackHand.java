package org.example.cards.model.blackjack;

import lombok.Getter;
import org.example.cards.model.CardHand;
import org.example.cards.util.CardUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BlackjackHand implements CardHand<BlackjackCard> {
    private static final BlackjackHandEvaluator handEvaluator = new BlackjackHandEvaluator();

    private final List<BlackjackCard> cards;

    public BlackjackHand() {
        this.cards = new ArrayList<>();
    }

    public BlackjackHand(List<BlackjackCard> cards) {
        this.cards = cards;
    }

    @Override
    public int getCardCount() {
        return this.cards.size();
    }

    @Override
    public void addCard(BlackjackCard card) {
        this.cards.add(card);
    }

    @Override
    public void removeCard(BlackjackCard card) {
        this.cards.remove(card);
    }

    @Override
    public void sort() {
        CardUtils.sortHandBySuitThenRank(this.cards);
    }

    public Integer evaluateHand() {
        return handEvaluator.evaluateHand(this);
    }

    @Override
    public String toString() {
        return CardUtils.formatHand(this);
    }
}
