package ulb.infof307.g02.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import ulb.infof307.g02.models.Card;
import ulb.infof307.g02.models.Deck;
import ulb.infof307.g02.views.CardEditorViewController;

import java.io.IOException;


/**
 * Controller de la fenêtre CardEditor. Permet d'ouvrir et de fermer la fenêtre et sert de relais entre la vue CardEditor
 * et le modèle Card
 */
public class CardEditorController implements CardEditorViewController.ViewListener {

    private final Stage stage;
    private Card card;
    private Listener listener;
    private String action;
    private Deck deck;

    /**
     * Constructeur de la classe CardEditorController.
     * Initialise le stage et le listener
     *
     * @param stage le stage qui prend en charge la fenêtre
     */
    public CardEditorController(Stage stage) {
        this.stage = stage;
    }


    /**
     * Charge la vue CardEditor et le controller de vue et montre la fenêtre.
     *
     * @throws IOException en cas d'erreur lors du chargement de la vue
     */
    public void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(CardEditorViewController.class.getResource("CardEditorView.fxml"));
        loader.load();

        CardEditorViewController cardEditorView = loader.getController();
        cardEditorView.setListener(this);
        cardEditorView.setCard(card);

        Parent root = loader.getRoot();
        this.stage.setScene(new Scene(root));
        this.stage.show();

    }

    /**
     * Définit le listener utilisé.
     *
     * @param listener le listener du Controller CardEditor.
     */
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /**
     * Set l'attribut this.card à card
     *
     * @param card la nouvelle card
     */
    public void setCard(Card card) {
        this.card = card;
    }

    /**
     * Enregistre la question et la réponse de la carte.
     *
     * @param question la nouvelle question
     * @param answer   la nouvelle réponse
     */
    @Override
    public void saveCard(String question, String answer) {
        card.modifyQuestion(question);
        card.modifyAnswer(answer);
        quit();
    }
    /**
     * Enregistre la question et la réponse de la carte en format texte à trou.
     *
     * @param question la nouvelle question de la carte
     * @param answer   la nouvelle réponse de la carte
     */
    @Override
    public void saveTexte(String question, String answer){
        if(verifNbTrous(question)!=verifNbAnswer(answer)){
            showTexteError();
        }
        else{
            card.modifyTexte(question, answer);
            quit();
        }
    }
    /**
     * Vérifie qu'il y a bien le bon nombre de trous dans le texte
     * @param question la question à vérifier
     * @return le nombre de trous dans la question
     */
    public int verifNbTrous(String question){
        char[] caractereQ = question.toCharArray();
        int nbtrous=0;
        for (char c : caractereQ){
            if(c=='('){
                nbtrous++;
            }
        }
        return nbtrous;
    }
    /**
     * Vérifie qu'il y a le bon nombre de réponses pour remplir les trous.
     * @param answer la réponse à vérifier
     * @return le nombre de réponses dans la réponse
     */
    public int verifNbAnswer(String answer){
        char[] caractereA = answer.toCharArray();
        int nbansw=1;
        for (char c : caractereA){
            if(c==','){
                nbansw++;
            }
        }
        return nbansw;
    }

    /**
     * Quitte l'éditeur de carte
     */
    public void quit() {
        stage.hide();
        this.listener.quit(this.deck);
    }

    /**
     * Affiche une fenêtre d'erreur en cas de non selection d'un élément avant d'appuyer sur un bouton.
     */
    @Override
    public void showQREmptyErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur: Impossible d'enregistrer la carte");
        alert.setHeaderText("Les champs sont incomplets");
        alert.setContentText("Veuillez compléter les champs vides");
        alert.showAndWait();
    }

    /**
     * Affiche une fenêtre d'erreur lorsque que le nombre de réponses correspond au nombre de trous.
     */
    private void showTexteError() {
        // Display a confirmation dialog box
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Erreur texte à trous");
        alert.setHeaderText("Le nombre de trous et de réponses ne concordent pas !");
        alert.showAndWait();

    }


    /**
     * Annule l'action en cours.
     * Si l'action est "add", supprime la carte du deck.
     * Ensuite, quitte l'application.
     */
    @Override
    public void cancel() {
        if(action.equals("add")){
            this.deck.removeCard(this.card);
        }
        this.quit();
    }


    /**
     * Définit l'action en cours.
     *
     * @param action l'action à définir.
     */
    public void setAction(String action) {
        this.action = action;
    }


    /**
     * Définit le deck utilisé.
     *
     * @param deck le deck ouvert.
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }


    /**
     * listener du Controller CardEditor.
     */
    public interface Listener {
        /**
         * Ferme l'application actuelle et ouvre l'éditeur de deck.
         * @param deck le deck à passer à l'éditeur.
         */
        void quit(Deck deck);
    }



}


