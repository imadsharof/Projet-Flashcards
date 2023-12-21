package Statistics;

import org.junit.jupiter.api.Test;
import ulb.infof307.g02.models.Statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticsTest {
    private final Statistics stat= new Statistics();


    @Test
    void score(){
        float cardLevel=12;
        int numberOfCardsPlayed= 1;
        stat.score(cardLevel, numberOfCardsPlayed);
        assertEquals(12.0, stat.getScore());
    }

    @Test
    void increaseNumberOfRevision(){
        int expected=1;
        stat.increaseNumberOfRevision();
        assertEquals(expected,stat.getNumberOfRevision());
    }

    @Test
    void increaseNumberOfCardsPlayed(){
        int expected=1;
        stat.increaseNumberOfCardsPlayed();
        assertEquals(expected,stat.getNumberOfCardsPlayed());
    }

    @Test
    void increaseAnswerLevel(){
        float lvlCard1=4;
        float lvlCard2=3;
        float expected= lvlCard2+lvlCard1;
        stat.increaseAnswerLevel(4);
        stat.increaseAnswerLevel(3);
        assertEquals(expected, stat.getAnswerLevel());
    }

    @Test
    void studyTime(){
        stat.startStudying();
        long start1 = stat.getStudyingTime();
        stat.startStudying();
        long start2 = stat.getStudyingTime();
        stat.endStudying();
        long end= stat.getStudyingTime();
        long expected= end-start1;
        assertEquals(expected,stat.getStudyingTime());

    }


}
