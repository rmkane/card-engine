package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.cards.engine.BlackjackEngine;
import org.example.cards.engine.HeartsEngine;
import org.example.cards.engine.PokerEngine;
import org.example.cards.model.blackjack.BlackjackPlayer;
import org.example.cards.model.hearts.HeartsPlayer;
import org.example.cards.model.poker.PokerPlayer;

import java.util.List;

@Slf4j
public class App {
    public static void main(String[] args) {
        playBlackjack();
        playPoker();
        playHearts();
    }

    public static void playBlackjack() {
        log.info("\nPlaying Blackjack...");
        BlackjackEngine blackjack = new BlackjackEngine();

        List.of("Alice", "Bob", "Charlie", "David")
                .forEach(name -> blackjack.addPlayer(new BlackjackPlayer(name)));

        blackjack.shuffleDeck();
        blackjack.deal();
        blackjack.showHands();
        blackjack.evaluateHands();

        blackjack.determineWinner()
                .ifPresent(winner -> {
                    log.info("Winner: {}", winner.getName());
                    log.info("Hand: {} ({})", winner.showHand(), winner.evaluateHand());
                });
    }

    public static void playPoker() {
        log.info("\nPlaying poker...");
        PokerEngine poker = new PokerEngine();

        List.of("Alice", "Bob", "Charlie", "David")
                .forEach(name -> poker.addPlayer(new PokerPlayer(name)));

        poker.shuffleDeck();
        poker.deal();
        poker.showHands();
        poker.evaluateHands();
        poker.showDiscardPile();
        poker.showHands();

        poker.determineWinner()
                .ifPresent(winner -> {
                    log.info("Winner: {}", winner.getName());
                    log.info("Hand: {} ({})", winner.showHand(), winner.evaluateHand().getDescription());
                });
    }

    public static void playHearts() {
        log.info("\nPlaying Hearts...");
        HeartsEngine hearts = new HeartsEngine();

        List.of("Alice", "Bob", "Charlie", "David")
                .forEach(name -> hearts.addPlayer(new HeartsPlayer(name)));

        hearts.shuffleDeck();
        hearts.deal();
        hearts.showHands();

        hearts.determineWinner()
                .ifPresent(winner -> {
                    log.info("Winner: {}", winner.getName());
                    log.info("Hand: {} ({})", winner.showHand(), winner.getScore());
                });
    }
}
