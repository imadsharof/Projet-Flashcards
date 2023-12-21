package Deck;

import org.junit.jupiter.api.Test;
import ulb.infof307.g02.models.Card;
import ulb.infof307.g02.models.Deck;
import ulb.infof307.g02.models.Statistics;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeckTest {

    public DeckTest() throws IOException {
    }


    @Test
    void testUpdateWeightsMultiplier() {
        Deck deck = new Deck();
        double multiplier = 0.7;
        Card card = new Card();
        deck.newCard(card) ;
        deck.updateWeights(deck.size()-1, multiplier);
        assertEquals(1.0 , card.getWeight());
    }

    @Test
    void testUpdateWeightsR() {
        Deck deck = new Deck();
        double multiplier = 0.7;
        Card card1 = new Card();
        Card card2 = new Card();
        deck.newCard(card1) ;
        deck.newCard(card2) ;
        deck.setInitialWeight();
        deck.updateWeights(deck.size()-2, multiplier);
        assertEquals(0.65 , card2.getWeight());
    }

    @Test
    void setInitialWeight() {
        Deck deck = new Deck();
        Card card1 = new Card();
        Card card2 = new Card();
        deck.newCard(card1) ;
        deck.newCard(card2) ;
        assertTrue(card2.getWeight() == card1.getWeight());
    }

    @Test
    void readWriteStatFile() throws IOException {
        Deck deck1 = new Deck();
        deck1.updateStat(5);
        deck1.incrementNumberOfRevision();

        Deck deck2 = new Deck();

        String filename = "DecksData/testStat.txt";
        deck1.writeStat(filename);

        deck2.readStatFile("DecksData/test.txt");
        assertEquals(5.0,deck2.getScore());
        assertEquals(5.0, deck2.getAnswerLevel());
        assertEquals(1, deck2.getNumberOfCardsPlayed());
        assertEquals(1, deck2.getNumberOfRevision());
    }

    @Test
    void size(){
        Deck deck1 = new Deck();
        Card card1 = new Card();
        deck1.newCard(card1);
        assertEquals(1, deck1.size());
    }

    @Test
    void bornes(){
        Deck deck2 = new Deck();
        Card card2 = new Card();
        Card card3 = new Card();
        deck2.newCard(card2);
        deck2.newCard(card3);
        deck2.setInitialWeight();
        deck2.updateWeights(0, 0.1);
        assertEquals(0.5, card2.getWeight());
        deck2.updateWeights(0, 0.2);
        assertEquals(0.1, card2.getWeight());

    }

}
