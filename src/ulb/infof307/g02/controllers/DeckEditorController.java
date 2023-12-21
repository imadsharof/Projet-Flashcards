package ulb.infof307.g02.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import ulb.infof307.g02.models.Deck;
import ulb.infof307.g02.models.Card;
import ulb.infof307.g02.views.DeckEditorViewController;

import java.io.IOException;

/**
 * Controller de la fenêtre DeckEditor. Permet d'ouvrir et de fermer la fenêtre et sert de relais entre la vue DeckEditor
 * et le modèle Deck.
 */
public class DeckEditorController implements DeckEditorViewController.ViewListener {

    private final Stage stage;
    private final Listener listener;
    private Deck deck;


    /**
     * Constructeur de la classe DeckEditorController.
     * Initialise le stage et le listener.
     * @param stage     le stage qui prend en charge la fenêtre.
     * @param listener  le listener de la classe.
     */
    public DeckEditorController(Stage stage, DeckEditorController.Listener listener){
        this.stage = stage;
        this.listener = listener;
    }

    /**
     * Charge la vue DeckEditor et le controller de vue et montre la fenêtre.
     * @throws IOException  en cas d'erreur lors du chargement de la vue.
     */
    public void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(DeckEditorViewController.class.getResource("DeckEditorView.fxml"));
        loader.load();

        DeckEditorViewController deckEditorView = loader.getController();
        deckEditorView.setListener(this);
        deckEditorView.setDeck(deck);

        Parent root = loader.getRoot();
        this.stage.setScene(new Scene(root));
        this.stage.show();

        stage.setOnCloseRequest((e) -> listener.editorClose());
    }

    /**
     * Affiche une fenêtre d'erreur en cas de non selection d'un élément avant d'appuyer sur un bouton.
     */
    public void showNoItemSelectedErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur: Impossible d'effectuer l'opération");
        alert.setHeaderText("Aucun élément n'est sélctionné");
        alert.setContentText("Veuillez sélectionner un élément");
        alert.showAndWait();
    }

    /**
     * Ajoute la carte passée en paramètre au deck et l'ouvre dans le CardEditor.
     */
    public void onAddButtonClick(){
        Card card = new Card();
        listener.openCardEditor(card, "add", deck);
        deck.newCard(card);
        stage.hide();
    }

    /**
     * Quitte le DeckEditor.
     */
    @Override
    public void quit() {
        listener.editorClose();
    }

    /**
     * Ouvre la carte passée en paramètre dans le CardEditor.
     * @param card  la carte à éditer.
     */
    @Override
    public void modifyCard(Card card) {
        listener.openCardEditor(card, "modify", this.deck);
    }

    /**
     * Supprime la carte passée en paramètre du deck courant.
     * @param card  la carte à supprimer.
     */
    @Override
    public void delete(Card card) {
        deck.removeCard(card);
    }

    /**
     * Change de deck courant.
     * @param deck le deck à ouvrir.
     */
    public void setDeck(Deck deck){
        this.deck=deck;
    }

    /**
     * Ferme la fenêtre.
     */
    public void hide(){
        stage.hide();
    }


    /**
     * Listener du controller DeckEditor.
     */
    public interface Listener {
        /**
         * Ouvre l'éditeur de carte avec les informations spécifiées.
         *
         * @param card   la carte à éditer
         * @param action l'action à effectuer
         * @param deck   le deck auquel la carte appartient
         */
        void openCardEditor(Card card, String action, Deck deck);

        /**
         * Indique que l'éditeur de deck a été fermé.
         *
         */
        void editorClose();
    }
}
