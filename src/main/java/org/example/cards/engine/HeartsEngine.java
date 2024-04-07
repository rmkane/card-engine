package org.example.cards.engine;

import lombok.extern.slf4j.Slf4j;
import org.example.cards.model.hearts.HeartsCard;
import org.example.cards.model.hearts.HeartsDeck;
import org.example.cards.model.hearts.HeartsHand;
import org.example.cards.model.hearts.HeartsPlayer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
public class HeartsEngine implements CardEngine<HeartsCard, HeartsHand, HeartsPlayer> {
    private static final int NUM_CARDS = 13;

    private final List<HeartsPlayer> players;
    private final HeartsDeck deck;

    public HeartsEngine() {
        this.deck = new HeartsDeck();
        this.players = new ArrayList<>();
    }

    @Override
    public void addPlayer(HeartsPlayer player) {
        players.add(player);
    }

    @Override
    public void shuffleDeck() {
        deck.shuffle();
    }

    @Override
    public void deal() {
        for (int i = 0; i < NUM_CARDS; i++) {
            for (HeartsPlayer player : players) {
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
    public Optional<HeartsPlayer> determineWinner() {
        return players.stream()
                .max(Comparator.comparingInt(HeartsPlayer::getScore));
    }
}
