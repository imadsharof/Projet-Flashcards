package Controller;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulb.infof307.g02.controllers.StatisticsController;
import ulb.infof307.g02.models.Statistics;


public class StatisticsControllerTest {
    private Stage mockStage;
    private StatisticsController statisticsController;

    @BeforeEach
    public void setUp() {
        StatisticsController.Listener mockVue = mock(StatisticsController.Listener.class);
        mockStage = mock(Stage.class);
        Statistics mockStats = mock(Statistics.class);
        statisticsController = new StatisticsController(mockStage, mockVue, mockStats);
    }

    @Test
    public void titletest() {
        //StatisticsController statisticstotest= new StatisticsController(stage,listener,statistics);
        mockStage = mock(Stage.class);
        StatisticsController.Listener mockVue = mock(StatisticsController.Listener.class);
        Statistics mockStats = mock(Statistics.class);
        statisticsController = new StatisticsController(mockStage, mockVue, mockStats);
        String title= mockStage.getTitle();
        assertEquals("Statistiques du jeu",title);
        //verify(mockVue).closeStats(statisticsController);
    }
    @Test
    public void testHide() {
        statisticsController.hide();
        verify(mockStage).hide();
    }
}