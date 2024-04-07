package org.example.cards.model.poker;

import lombok.Value;
import org.example.cards.model.Card;
import org.example.cards.model.Rank;
import org.example.cards.model.Suit;
import org.example.cards.util.CardUtils;

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

    }
}
