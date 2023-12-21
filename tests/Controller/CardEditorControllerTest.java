package Controller;

import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulb.infof307.g02.controllers.CardEditorController;
import ulb.infof307.g02.models.Card;
import ulb.infof307.g02.models.Deck;
import ulb.infof307.g02.models.DeckTable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CardEditorControllerTest {
    private Card card =new Card();
    Stage mockStage = mock(Stage.class);
    CardEditorController cardEditorController=new CardEditorController(mockStage);

    @BeforeEach
    public void setUp() {
        CardEditorController.Listener l = deck -> {
            return;
        };
        cardEditorController.setListener(l);
    }

    @Test
    public void testSaveCard(){
        cardEditorController.setCard(card);
        cardEditorController.saveCard("Quel est le nombre de crédits du cours INFOF307 pour les ingénieurs?","5 crédits");
        assertEquals("Quel est le nombre de crédits du cours INFOF307 pour les ingénieurs?",card.getQuestion());

    }
    @Test
    public void testSaveTexte(){
        cardEditorController.setCard(card);
        cardEditorController.saveTexte("Il y a (1) personnes qui travaillent (2) par jour pour le projet en (3)","10,30 heures,Gestion de groupe");
        assertEquals("Il y a __ personnes qui travaillent __ par jour pour le projet en __",card.getQuestion());
        assertEquals("Il y a 10 personnes qui travaillent 30 heures par jour pour le projet en Gestion de groupe",card.getAnswer());

    }
    @Test
    public void testquit() {
        cardEditorController.quit();
        verify(mockStage).hide();
    }

}

