package ulb.infof307.g02.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.infof307.g02.models.Deck;
import ulb.infof307.g02.views.PlayDeckViewController;
import java.io.IOException;
import java.util.List;

/**
 * Controller de la fenêtre Player. Permet d'ouvrir et de fermer la fenêtre et sert de relais entre la vue PlayDeck
 * et le modèle Deck.
 */
public class PlayerController implements PlayDeckViewController.ViewListener {
    private final Stage stage;
    private final PlayerController.Listener listener;
    private Deck deck;
    private int index;
    private int initWeight;

    /**
     * Contructeur du PlayerController.
     * Initialise le stage et le listener.
     * @param stage     le stage qui prend en charge la fenêtre.
     * @param listener  le listener de la classe.
     */
    public PlayerController(Stage stage, PlayerController.Listener listener) {
        this.stage = stage;
        this.listener = listener;
        initWeight = 1;
    }

    /**
     * Charge la vue PlayDeck et son controller, puis montre la fenêtre.
     * @throws IOException  en cas d'erreur de chargement de la vue.
     */
    public void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(PlayDeckViewController.class.getResource("PlayDeckView.fxml"));
        loader.load();

        PlayDeckViewController playDeckViewController = loader.getController();
        playDeckViewController.setListener(this);
        playDeckViewController.setDeck(deck);

        Parent root = loader.getRoot();
        this.stage.setScene(new Scene(root));
        this.stage.show();

        stage.setOnCloseRequest((e) -> this.quit());
    }

    /**
     * Ferme la fenêtre.
     */
    public void hide(){
        stage.hide();
    }

    /**
     * Change de deck courant.
     * @param deck  le nouveau deck courant.
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    /**
     * Renvoie la question de la carte en position index.
     * @return  la question.
     */
    @Override
    public String getQuestion() {
        return deck.getQuestion(index);
    }

    /**
     * Renvoie la réponse de la carte en position index.
     * @return  la réponse.
     */
    @Override
    public String getAnswer() {
        return deck.getAnswer(index);
    }

    /**
     * Termine l'étude en cours et ferme le lecteur de cartes.
     */
    @Override
    public void quit(){
        deck.endStudying();
        listener.playerClose(this);
    }

    /**
     * Met à jour les poids des cartes du deck.
     * @param levelAnswer   le niveau de réponse. Dépend du bouton de réponse appuyé.
     */
    public void updateProbabilitiesAndNextIndex(int levelAnswer) {
        double multiplier = 1.0;
        this.updateRandomIndex();
        /*
         * En fonction de la réponse répondue, le coefficient appelé "multiplier" change de valeur dans
         * le but de changer la probabilité liée à la question
         */
        switch (levelAnswer) {
            case 1 -> multiplier = 0.7;
            case 2 -> multiplier = 0.85;
            case 3 -> multiplier = 1;
            case 4 -> multiplier = 1.2;
            case 5 -> multiplier = 1.35;
        }
        deck.updateWeights(index, multiplier);
    }

    /**
     * Contrôle du bouton switch <br/>.
     * Initialise les poids des cartes si c'est la première fois que le bouton est cliqué.
     */
    @Override
    public void onSwitchButtonClick() {
        if (initWeight == 1){
            updateRandomIndex();
            deck.setInitialWeight();
            initWeight++;
            deck.startStudying();
        }
    }

    /**
     * Contrôle des 5 boutons d'évaluation (très bonne, bonne, moyenne, mauvaise et très mauvaise).
     * @param levelAnswer   le niveau de réponse. Dépend du bouton appuyé.
     */
    @Override
    public void evaluationButtonClick(int levelAnswer) {
        deck.updateStat(levelAnswer);
        updateProbabilitiesAndNextIndex(levelAnswer);
    }

    /**
     * Fonction qui renvoie l'index de la carte choisie aléatoirement en fonction des probabilités d'apparition de chaque carte.
     */
    public void updateRandomIndex() {
        List<Double> probabilities = deck.getAllWeights();
        int newIndex = index;
        while (deck.size() > 1 && newIndex == index) {
            double random = Math.random();
            double sum = 0.0;
            for (int i = 0; i < probabilities.size(); i++) {
                sum += probabilities.get(i);
                if (random <= sum) {
                    newIndex = i;
                    break;
                }
            }
        }
        index = newIndex;
    }

    /**
     * Listener du PlayerController.
     */
    public interface Listener{

        /**
         * Appelé lorsque le joueur de cartes est fermé.
         *
         * @param playerController le contrôleur du joueur de cartes
         */
        void playerClose(PlayerController playerController);
    }
}
