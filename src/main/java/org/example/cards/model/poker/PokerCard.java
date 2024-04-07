package org.example.cards.model.poker;

import lombok.Value;
import org.example.cards.model.Card;
import org.example.cards.model.Rank;
import org.example.cards.model.Suit;
import org.example.cards.util.CardUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Value
public class PokerCard implements Card {
    Suit suit;
    Rank rank;

    @Override
    public String toString() {
        return CardUtils.formatCard(this);
    }

    public static List<PokerCard> fromCardSymbols(String cardSymbols) {
        return CardUtils.fromCardSymbols(cardSymbols, PokerCard.class);
    }

    public static PokerCard fromCardSymbol(String cardSymbol) {
        return CardUtils.fromCardSymbol(cardSymbol, PokerCard.class);
    }

    public static void main(String[] args) {
        PokerHandEvaluator handEvaluator = new PokerHandEvaluator();
        Comparator<PokerHand> handComparator = new PokerHandComparator();

        // TODO: Ensure that sort is correct
        List<PokerHand> hands = List.of(
                        "T♠ J♠ Q♠ K♠ A♠",
                        "9♠ T♠ J♠ Q♠ K♠",
                        "8♠ 9♠ T♠ J♠ Q♠",
                        "7♠ 8♠ 9♠ T♠ J♠",
                        "6♠ 7♠ 8♠ 9♠ T♠"
                ).stream()
                .map(hand -> new ArrayList<>(PokerCard.fromCardSymbols(hand)))
                .map(PokerHand::new)
                .sorted(handComparator)
                .toList();

        for (PokerHand hand : hands) {
            PokerHandType handType = handEvaluator.evaluateHand(hand);
            System.out.printf("%s (%s)%n", hand, handType);
        }
    }
}
