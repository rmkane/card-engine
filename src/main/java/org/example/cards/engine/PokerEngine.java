package org.example.cards.engine;

import lombok.extern.slf4j.Slf4j;
import org.example.cards.model.Card;
import org.example.cards.model.Deck;
import org.example.cards.model.poker.PokerCard;
import org.example.cards.model.poker.PokerDeck;
import org.example.cards.model.poker.PokerPlayer;
import org.example.cards.util.CardUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
public class PokerEngine {
    private static final int HAND_SIZE = 5;

    private final List<PokerPlayer> players;
    private final Deck<PokerCard> deck;
    private final List<Card> discardPile;

    public PokerEngine() {
        this.players = new ArrayList<>();
        this.deck = new PokerDeck();
        this.discardPile = new ArrayList<>();
    }

    public void addPlayer(PokerPlayer player) {
        players.add(player);
    }

    public void shuffleDeck() {
        deck.shuffle();
    }

    public void dealInitialHand() {
        for (int i = 0; i < HAND_SIZE; i++) {
            for (PokerPlayer player : players) {
                player.drawCard(deck.deal());
            }
        }
    }

    public void discardCard(Card card) {
        discardPile.add(card);
    }

    public void showHands() {
        for (PokerPlayer player : players) {
            player.sortHand();
            log.debug("{}'s hand: {}", player.getName(), player.showHand());
        }
    }

    public void showDiscardPile() {
        log.debug("Discard pile: {}", CardUtils.formatHand(discardPile));
    }

    public Optional<PokerPlayer> determineWinner() {
        return players.stream().max(Comparator.comparing(PokerPlayer::getHandRank));
    }

    public void evaluateHands() {
        for (PokerPlayer player : players) {
            log.info("{}'s hand: {} ({})", player.getName(), player.showHand(), player.evaluateHand().getDescription());
            List<PokerCard> cardsToDiscard = player.decideCardsToDiscard();
            log.info("Discarding: {}", CardUtils.formatHand(cardsToDiscard));
            int cardsToDraw = cardsToDiscard.size();
            for (PokerCard card : cardsToDiscard) {
                player.discardCard(card);
                discardCard(card);
            }
            for (int i = 0; i < cardsToDraw; i++) {
                player.drawCard(deck.deal());
            }
            log.info("New hand: {} ({})", player.showHand(), player.evaluateHand().getDescription());
        }
    }
}
