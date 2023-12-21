package ulb.infof307.g02.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Le controller de vue des Statistics
 */
public class StatisticsViewController  implements Initializable {

    /**
     * Constructeur vide de la classe StatisticsViewController.
     */
    public StatisticsViewController() {
        //
    }

    /**
     * Label pour afficher le temps d'étude dans la vue des statistiques.
     */
    public Label labelStudyingTime;
    @FXML
    private Label labelScore;
    @FXML
    private Label labelNbRev;
    @FXML
    private Label labelNbCardsPlayed;

    /**
     * Listener de la vue pour les interactions liées aux statistiques.
     */
    protected ViewListener listener;


    /**
     * Remplace le listener courant par celui passé en paramètre
     * @param listener  le nouveau listener
     */
    public void setListener(ViewListener listener) {
        this.listener = listener;
    }

    /**
     * Méthode d'initialisation appelée lorsque la vue est chargée.
     * Cette méthode est vide car aucune initialisation supplémentaire n'est nécessaire.
     * @param url             L'URL de la ressource de la vue.
     * @param resourceBundle Les ressources utilisées par la vue.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //
    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton Réinitialiser les statistiques.
     * Notifie le listener pour réinitialiser les statistiques et met à jour les labels.
     */
    @FXML
    private void onResetStatsButtonClick(){
        listener.resetStatistics();
        updateLabels();
    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton Retour.
     * Notifie le listener pour quitter la vue des statistiques.
     */
    @FXML
    private void onReturnClickButton(){
        listener.quit();
    }

    /**
     * Met à jour les trois labels de la vue.
     */
    public void updateLabels() {
        String str1 = "Score globale moyen : " + listener.getScore() + "/5";
        String str2 = "Nombre de révision totale : " + listener.getNumberOfRevision();
        String str3 = "Nombre total de cartes jouées : " + listener.getNumberOfCardsPlayed();
        String str4 = getStringStudyingTime();
        labelScore.setText(str1);
        labelNbRev.setText(str2);
        labelNbCardsPlayed.setText(str3);
        labelStudyingTime.setText(str4);
    }

    /**
     * Retourne une chaîne de caractères représentant le temps d'étude total.
     * Le temps d'étude total est obtenu en appelant la méthode getStudyingTime() du listener.
     *
     * @return Une chaîne de caractères représentant le temps d'étude total.
     */
    private String getStringStudyingTime() {
        long totalTime = listener.getStudyingTime();
        int seconds, minutes, hours;
        minutes = (int) Math.floor(totalTime/60.0);
        seconds = (int) (totalTime%60);
        hours = (int) Math.floor(minutes/60.0);
        minutes = minutes%60;
        String h, m, s;
        if(hours < 10){
            h = "0" + hours;
        }
        else{
            h = String.valueOf(hours);
        }
        if(minutes < 10){
            m = "0" + minutes;
        }
        else{
            m = String.valueOf(minutes);
        }
        if(seconds < 10){
            s = "0" + seconds;
        }
        else{
            s = String.valueOf(seconds);
        }
        return "Temps d'étude : " + h + "h" + m + "m" + s + "s";
    }

    /**
     * Le ViewListener du StatisticsViewController
     */
    public interface ViewListener{

        /**
         * Récupère le score des statistiques.
         * @return Le score.
         */
        float getScore();

        /**
         * Récupère le nombre de révisions effectuées.
         * @return Le nombre de révisions.
         */
        int getNumberOfRevision();

        /**
         * Récupère le nombre de cartes jouées.
         * @return Le nombre de cartes jouées.
         */
        int getNumberOfCardsPlayed();

        /**
         * Réinitialise les statistiques.
         */
        void resetStatistics();

        /**
         * Méthode de rappel appelée lorsqu'une demande de fermeture est effectuée.
         */
        void quit();

        /**
         * Récupère le temps d'étude.
         * @return Le temps d'étude en millisecondes.
         */
        long getStudyingTime();
    }

}
