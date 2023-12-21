package ulb.infof307.g02.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.infof307.g02.models.Statistics;
import ulb.infof307.g02.views.StatisticsViewController;

import java.io.IOException;

/**
 * Controller de la fenêtre Statistics. Permet d'ouvrir et de fermer la fenêtre et sert de relais entre la vue Statistics
 * et le modèle Statistics.
 */
public class StatisticsController implements StatisticsViewController.ViewListener {

    private final  Stage stage;
    private final  StatisticsController.Listener listener;
    private final Statistics statistics;


    /**
     * Contructeur du StatisticsController.
     * Initialise le stage et le listener.
     * @param stage     le stage qui prend en charge la fenêtre
     * @param listener  le listener de la classe
     * @param stats les statistiques du jeu
     */
    public StatisticsController(Stage stage, Listener listener, Statistics stats) {
        stage.setTitle("Statistiques du jeu");
        this.stage = stage;
        this.listener =  listener;
        this.statistics = stats;
    }

    /**
     * Charge la vue Statistics et son controller, puis montre la fenêtre.
     * @throws IOException  en cas d'erreur de chargement de la vue.
     */
    public void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(StatisticsViewController.class.getResource("StatisticsView.fxml"));
        loader.load();

        StatisticsViewController statisticsViewController = loader.getController();
        statisticsViewController.setListener(this);
        statisticsViewController.updateLabels();

        Parent root = loader.getRoot();
        this.stage.setScene(new Scene(root));
        this.stage.show();

        stage.setOnCloseRequest((e) -> listener.closeStats(this));
    }

    /**
     * Ferme la fenêtre.
     */
    public void hide(){
        stage.hide();
    }

    /**
     * Récupère le score actuel des statistiques.
     *
     * @return  le score contenu dans le statistics
     */
    public float getScore() {
      return statistics.getScore();
    }

    /**
     * Récupère le nombre de révisions effectuées selon les statistiques.
     *
     * @return  le nombre de révision contenu dans le statistics
     */
    public int getNumberOfRevision(){
      return statistics.getNumberOfRevision();
    }

    /**
     * Récupère le nombre de cartes jouées selon les statistiques.
     *
     * @return  le nombre de cartes jouées contenu dans le statistics
     */
    public int getNumberOfCardsPlayed(){
        return statistics.getNumberOfCardsPlayed();
    }


    /**
     * Réinitialise les statistiques.
     */
    @Override
    public void resetStatistics() {
        statistics.reset();
    }

    /**
     * Termine la session des statistiques.
     */
    @Override
    public void quit() {
        listener.closeStats(this);
    }

    /**
     * Récupère le temps total d'étude.
     *
     * @return le temps total d'étude
     */
    @Override
    public long getStudyingTime() {
        return statistics.getStudyingTime();
    }


    /**
     * Listener du StatisticsController.
     */
    public interface Listener {

        /**
         * Appelé lorsque le contrôleur des statistiques est fermé.
         *
         * @param statisticsController le contrôleur des statistiques
         */
        void closeStats(StatisticsController statisticsController);
    }
}
