package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.cards.engine.HeartsEngine;
import org.example.cards.engine.PokerEngine;
import org.example.cards.model.hearts.HeartsPlayer;
import org.example.cards.model.poker.PokerPlayer;

import java.util.List;

@Slf4j
public class App {
    public static void main(String[] args) {
        playPoker();
        playHearts();
    }

    public static void playPoker() {
        log.info("Playing poker...");
        PokerEngine poker = new PokerEngine();

        List.of("Alice", "Bob", "Charlie", "David")
                .forEach(name -> poker.addPlayer(new PokerPlayer(name)));

        poker.shuffleDeck();
        poker.dealInitialHand();
        poker.showHands();
        poker.evaluateHands();
        poker.showDiscardPile();

        poker.determineWinner().ifPresent(winner -> {
            log.info("Winner: {}", winner.getName());
            log.info("Hand: {} ({})", winner.showHand(), winner.evaluateHand().getDescription());
        });
    }

    public static void playHearts() {
        log.info("Playing Hearts...");
        HeartsEngine hearts = new HeartsEngine();

        List.of("Bob", "Alice", "Charlie", "David")
                .forEach(name -> hearts.addPlayer(new HeartsPlayer(name)));

        hearts.deal();
        hearts.showHands();

        hearts.determineWinner()
                .ifPresent(winner -> {
                    log.info("Winner: {}", winner.getName());
                    log.info("Hand: {} ({})", winner.showHand(), winner.getScore());
                });
    }
}
