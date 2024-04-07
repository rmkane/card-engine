package org.example.cards.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.cards.model.Card;
import org.example.cards.model.CardHand;
import org.example.cards.model.Rank;
import org.example.cards.model.Suit;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CardUtils {
    private static final int RANK_SYMBOL_INDEX = 0;
    private static final int SUIT_SYMBOL_INDEX = 1;

    public static <C extends Card> String formatCard(C card) {
        return String.format("%s%s", card.getRank().getSymbol(), card.getSuit().getSymbol());
    }

    public static <C extends Card, H extends CardHand<C>> String formatHand(H hand) {
        return formatHand(hand.getCards());
    }

    public static <C extends Card> String formatHand(List<C> hand) {
        return hand.stream().map(C::toString).collect(Collectors.joining(" "));
    }

    public static <C extends Card, H extends CardHand<C>> void sortHandBySuitThenRank(H hand) {
        sortHandBySuitThenRank(hand.getCards());
    }

    public static <C extends Card> void sortHandBySuitThenRank(List<C> cards) {
        cards.sort(Comparator.comparing(C::getSuit).thenComparing(C::getRank));
    }

    public static <C extends Card> List<C> sortedByHandBySuitThenRank(List<C> cards) {
        return cards.stream()
                .sorted(Comparator.comparing(C::getSuit).thenComparing(C::getRank))
                .toList();
    }

    public static <C extends Card, H extends CardHand<C>> void sortHandByRankThenSuit(H hand) {
        sortHandByRankThenSuit(hand.getCards());
    }

    public static <C extends Card> void sortHandByRankThenSuit(List<C> cards) {
        cards.sort(Comparator.comparing(C::getRank).thenComparing(C::getSuit));
    }

    public static <C extends Card> List<C> sortedBHandByRankThenSuit(List<C> cards) {
        return cards.stream()
                .sorted(Comparator.comparing(C::getRank).thenComparing(C::getSuit))
                .toList();
    }

    public static <C extends Card, H extends CardHand<C>> void sortByRankDescending(H hand) {
        sortByRankDescending(hand.getCards());
    }

    public static <C extends Card> void sortByRankDescending(List<C> cards) {
        cards.sort(Comparator.comparing(C::getRank).reversed());
    }

    public static <C extends Card, H extends CardHand<C>> List<C> sortedByRankDescending(H hand) {
        return hand.getCards().stream()
                .sorted(Comparator.comparing(C::getRank).reversed())
                .toList();
    }

    public static <C extends Card> void shuffle(List<C> cards) {
        Random rand = new Random();
        for (int currIndex = cards.size() - 1; currIndex > 0; currIndex--) {
            int randIndex = rand.nextInt(currIndex + 1);
            C randCard = cards.get(randIndex);
            cards.set(randIndex, cards.get(currIndex));
            cards.set(currIndex, randCard);
        }
    }

    public static <C extends Card> List<C> populateDeck(Class<C> cardType) {
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

    public static <C extends Card> List<C> fromCardSymbols(List<String> cardSymbols, Class<C> cardType) {
        return cardSymbols.stream()
                .map(symbol -> fromCardSymbol(symbol, cardType))
                .toList();
    }

    public static <C extends Card> List<C> fromCardSymbols(String cardSymbols, Class<C> cardType) {
        return fromCardSymbols(List.of(cardSymbols.trim().split("\\s+")), cardType);
    }

    public static <C extends Card> C fromCardSymbol(String cardSymbol, Class<C> cardType) {
        try {
            String suitSymbol = String.valueOf(cardSymbol.charAt(SUIT_SYMBOL_INDEX));
            String rankSymbol = String.valueOf(cardSymbol.charAt(RANK_SYMBOL_INDEX));

            Suit suit = fromSuitSymbol(suitSymbol);
            Rank rank = fromRankSymbol(rankSymbol);

            return cardType.getDeclaredConstructor(Suit.class, Rank.class)
                    .newInstance(suit, rank);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Suit fromSuitSymbol(String suitSymbol) {
        return Suit.fromSymbol(suitSymbol).orElseThrow();
    }

    private static Rank fromRankSymbol(String rankSymbol) {
        return Rank.fromSymbol(rankSymbol).orElseThrow();
    }
}
