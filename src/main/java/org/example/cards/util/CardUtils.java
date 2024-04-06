package org.example.cards.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.cards.model.Card;
import org.example.cards.model.Rank;
import org.example.cards.model.Suit;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CardUtils {
    public static <E extends Card> String formatCard(E card) {
        return String.format("%s%s", card.getRank().getSymbol(), card.getSuit().getSymbol());
    }

    public static <E extends Card> String formatHand(List<E> hand) {
        return hand.stream().map(E::toString).collect(Collectors.joining(" "));
    }

    public static <E extends Card> void sortHandBySuitThenRank(List<E> hand) {
        hand.sort(Comparator.comparing(E::getSuit).thenComparing(E::getRank));
    }

    public static <E extends Card> void sortHandByRankThenSuit(List<E> hand) {
        hand.sort(Comparator.comparing(E::getRank).thenComparing(E::getSuit));
    }

    public static <E extends Card> void shuffle(List<E> cards) {
        Random rand = new Random();
        for (int currIndex = cards.size() - 1; currIndex > 0; currIndex--) {
            int randIndex = rand.nextInt(currIndex + 1);
            E randCard = cards.get(randIndex);
            cards.set(randIndex, cards.get(currIndex));
            cards.set(currIndex, randCard);
        }
    }

    public static <E extends Card> List<E> populateDeck(Class<E> cardType) {
        return Stream.of(Suit.values())
                .flatMap(suit -> Stream.of(Rank.values())
                        .map(rank -> {
                            try {
                                return cardType.getDeclaredConstructor(Suit.class, Rank.class)
                                        .newInstance(suit, rank);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }))
                .toList();
    }
}
