package Controller;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulb.infof307.g02.controllers.DeckEditorController;
import ulb.infof307.g02.models.Card;
import ulb.infof307.g02.models.Deck;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeckEditorControllerTest {
    private Deck deck=new Deck();
    DeckEditorController.Listener mockListener = mock (DeckEditorController.Listener.class);
    Stage mockStage = mock(Stage.class);
    DeckEditorController deckEditorController=new DeckEditorController(mockStage,mockListener);

    @BeforeEach
    public void setUp() {
        this.deck = new Deck();
    }
    @Test
    public void onAddButtonClickTest(){
        deckEditorController.setDeck(deck);
        deckEditorController.onAddButtonClick();
        deckEditorController.onAddButtonClick();
        assertEquals(2,deck.getNumberOfCards());
    }
    @Test
    public void testHide() {
        deckEditorController.hide();
        verify(mockStage).hide();
    }
    @Test
    public void testquit() {
        deckEditorController.quit();
        verify(mockListener).editorClose();
    }

    @Test
    public void testmodifyCard(){
        Card card =new Card();
        deckEditorController.setDeck(deck);
        deckEditorController.modifyCard(card);
        verify(mockListener).openCardEditor(card, "modify", this.deck);

    }
}
