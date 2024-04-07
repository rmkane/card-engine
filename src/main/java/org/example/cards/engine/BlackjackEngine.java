package org.example.cards.engine;

import lombok.extern.slf4j.Slf4j;
import org.example.cards.model.CardDeck;
import org.example.cards.model.blackjack.BlackjackCard;
import org.example.cards.model.blackjack.BlackjackDeck;
import org.example.cards.model.blackjack.BlackjackHand;
import org.example.cards.model.blackjack.BlackjackPlayer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Slf4j
public class BlackjackEngine implements CardEngine<BlackjackCard, BlackjackHand, BlackjackPlayer> {
    private static final int NUM_CARDS = 2;

    private final List<BlackjackPlayer> players;
    private final CardDeck<BlackjackCard> deck;

    public BlackjackEngine() {
        this.deck = new BlackjackDeck();
        this.players = new ArrayList<>();
    }

    @Override
    public void addPlayer(BlackjackPlayer player) {
        players.add(player);
    }

    @Override
    public void shuffleDeck() {
        deck.shuffle();
    }

    @Override
    public void deal() {
        for (int i = 0; i < NUM_CARDS; i++) {
            for (BlackjackPlayer player : players) {
                player.acceptCard(deck.deal());
            }
        }
    }

    @Override
    public void showHands() {
        players.forEach(player -> {
            player.sortHand();
            log.debug(String.valueOf(player));
        });
    }

    @Override
    public Optional<BlackjackPlayer> determineWinner() {
        return players.stream()
                .filter(Predicate.not(BlackjackPlayer::didBust))
                .max(Comparator.comparingInt(BlackjackPlayer::getScore));
    }

    public void evaluateHands() {
        for (BlackjackPlayer player : players) {
            log.info("{}'s hand: {} ({})", player.getName(), player.showHand(), player.evaluateHand());

            int score = player.getScore();

            while (shouldHit(score)) {
                BlackjackCard card = deck.deal();
                log.info("{} hits and receives a {}", player.getName(), card);
                player.acceptCard(card);
                score = player.getScore();
            }

            if (player.didBust()) {
                log.info("{} busts!", player.getName());
            }

            if (player.getHand().getCardCount() > 2) {
                log.info("New hand: {} ({})", player.showHand(), player.evaluateHand());
            }
        }
    }

    private boolean shouldHit(int score) {
        return score < 17;
    }
}
