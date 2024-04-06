package org.example.cards.model.hearts;

import lombok.Getter;
import org.example.cards.model.CardPlayer;
import org.example.cards.util.CardUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public class HeartsPlayer implements CardPlayer<HeartsCard> {
    String name;
    List<HeartsCard> hand;

    public HeartsPlayer(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    @Override
    public void drawCard(HeartsCard card) {
        hand.add(card);
    }

    @Override
    public void discardCard(HeartsCard card) {
        hand.remove(card);
    }

    @Override
    public void sortHand() {
        CardUtils.sortHandBySuitThenRank(hand);
    }

    @Override
    public int getScore() {
        return hand.stream()
                .mapToInt(HeartsCard::getValue)
                .sum();
    }

    @Override
    public String showHand() {
        return CardUtils.formatHand(hand);
    }

    @Override
    public String toString() {
        return String.format("%s: %s (%d)", name, showHand(), getScore());
    }
}
