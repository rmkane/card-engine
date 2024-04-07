package org.example.cards.model.poker;

import lombok.Getter;
import org.example.cards.model.CardHand;
import org.example.cards.util.CardUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PokerHand implements CardHand<PokerCard> {
    private final List<PokerCard> cards;

    public PokerHand() {
        this.cards = new ArrayList<>();
    }

    public PokerHand(List<PokerCard> cards) {
        this.cards = cards;
    }

    @Override
    public int getCardCount() {
        return this.cards.size();
    }

    @Override
    public void addCard(PokerCard card) {
        this.cards.add(card);
    }

    @Override
    public void removeCard(PokerCard card) {
        this.cards.remove(card);
    }

    @Override
    public void sort() {
        CardUtils.sortHandBySuitThenRank(this.cards);
    }

    public PokerHandType evaluateHand() {
        return new PokerHandEvaluator().evaluateHand(this);
    }

    @Override
    public String toString() {
        return CardUtils.formatHand(this);
    }
}
