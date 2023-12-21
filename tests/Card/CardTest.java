package Card;

import org.junit.jupiter.api.Test;
import ulb.infof307.g02.models.Card;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    private final Card card= new Card();

    @Test
    void testModifyQuestion(){
        card.modifyQuestion("question_test");
        assertEquals("question_test", card.getQuestion());
    }

    @Test
    void testModifyAnswer(){
        card.modifyAnswer("reponse_test");
        assertEquals("reponse_test", card.getAnswer());
    }

}
