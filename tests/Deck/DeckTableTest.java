package Deck;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import ulb.infof307.g02.models.Deck;
import ulb.infof307.g02.models.DeckTable;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTableTest {
    private DeckTable deckTable;

    @BeforeEach
    public void setUp() {
        this.deckTable = new DeckTable();
    }

    @Test
    public void testAddDeck() {
        Deck deck = new Deck("Deck1", "test");
        deckTable.addDeck(deck);

        List<Deck> table = deckTable.getTable();
        Deck loadedDeck1 = table.get(0);
        assertEquals("Deck1", loadedDeck1.getName());
        assertEquals("test", loadedDeck1.getCategory());
        assertEquals(1, table.size());
        assertEquals(deck, table.get(0));
    }

    @Test
    public void testDeleteDeck() {
        Deck deck = new Deck("Deck1", "test");
        deckTable.addDeck(deck);
        assertEquals(1, deckTable.getTable().size());

        assertDoesNotThrow(() -> deckTable.deleteDeck(deck));
        assertEquals(0, deckTable.getTable().size());
        assertFalse(new File("DecksData/Deck1").exists());
    }

    @Test
    public void testSaveDeck() {
        Deck deck = new Deck("Deck1","test");
        deckTable.addDeck(deck);
        assertDoesNotThrow(() -> deckTable.saveDeck(deck));
        assertTrue(new File("DecksData/Deck1.txt").exists());
    }

    @Test
    void testLoadDecks() {
        // Créez une instance de votre classe qui contient la méthode `loadDecks()`
        DeckTable deckTable = new DeckTable();
        assertDoesNotThrow(deckTable::loadDecks);
        assertFalse(deckTable.getTable().isEmpty(), "Table should not be empty after loading decks");

        // Effectuez d'autres vérifications en fonction de vos besoins et de vos attentes
    }


    @Test
    public void testSaveTable() {
        Deck deck1 = new Deck("Deck1", "test");
        deckTable.addDeck(deck1);
        Deck deck2 = new Deck("Deck2", "test");
        deckTable.addDeck(deck2);

        // Test avec des decks valides
        assertDoesNotThrow(() -> deckTable.saveTable());
        assertTrue(new File("DecksData/Deck1.txt").exists());
        assertTrue(new File("DecksData/Deck2.txt").exists());
    }



}