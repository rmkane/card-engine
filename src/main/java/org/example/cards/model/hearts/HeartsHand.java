package org.example.cards.model.hearts;

import lombok.Getter;
import org.example.cards.model.CardHand;
import org.example.cards.util.CardUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public class HeartsHand implements CardHand<HeartsCard> {
    private static final HeartsHandEvaluator handEvaluator = new HeartsHandEvaluator();

    private final List<HeartsCard> cards;

    public HeartsHand() {
        this.cards = new ArrayList<>();
    }

    public HeartsHand(List<HeartsCard> cards) {
        this.cards = cards;
    }

    @Override
    public int getCardCount() {
        return this.cards.size();
    }

    @Override
    public void addCard(HeartsCard card) {
        this.cards.add(card);
    }

    @Override
    public void removeCard(HeartsCard card) {
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
