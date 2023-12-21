package ulb.infof307.g02.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import ulb.infof307.g02.models.Card;
import ulb.infof307.g02.models.Deck;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller de vue du DeckEditor.
 */
public class DeckEditorViewController implements Initializable{

    /**
     * Constructeur vide de la classe DeckEditorViewController.
     */
    public DeckEditorViewController() {
        //
    }

    /**
     * Vue de l'éditeur de deck qui affiche les cartes dans un tableau.
     */
    public TableView<Card> deckEditorView;

    /**
     * Colonne de la table contenant les questions des cartes.
     */
    @FXML
    public TableColumn<Card, String> questionColumnTableView;

    /**
     * Colonne de la table contenant les réponses des cartes.
     */
    @FXML
    public TableColumn<Card, String> answerColumnTableView;
    @FXML
    private Label nomJeu;
    @FXML
    private Label category;
    @FXML
    private Label nbCards;
    private ViewListener listener;

    /**
     * Gestionnaire d'événements pour le bouton "Sélectionner".
     * Cette méthode est appelée lorsque le bouton "Sélectionner" est cliqué.
     * Elle récupère les lignes sélectionnées dans la vue du deck,
     * puis vérifie si une ligne a été sélectionnée.
     * Si une ligne est sélectionnée, la carte correspondante est récupérée et transmise au listener pour modification.
     * Sinon, une fenêtre d'erreur indiquant qu'aucune ligne n'a été sélectionnée.
     */
    @FXML
    public void onSelectButtonClick(){
        ObservableList<Card> selectedRows;

        //this gives us the rows that were selected
        selectedRows = deckEditorView.getSelectionModel().getSelectedItems();
        if (!selectedRows.isEmpty() && selectedRows.get(0)!= null) {
            Card card = selectedRows.get(0);
            listener.modifyCard(card);
        }
        else{listener.showNoItemSelectedErrorAlert();}
    }

    /**
     * Gestionnaire d'événements pour le bouton "Ajouter".
     * Cette méthode est appelée lorsque le bouton "Ajouter" est cliqué.
     * Elle transmet l'événement de clic au listener correspondant pour effectuer les actions nécessaires.
     */
    @FXML
    public void onAddButtonClick(){
        listener.onAddButtonClick();
    }

    /**
     * Gestionnaire d'événements pour le bouton "Supprimer".
     * Cette méthode est appelée lorsque le bouton "Supprimer" est cliqué.
     * Elle récupère les lignes sélectionnées dans la vue du deck,
     * puis vérifie si une ligne a été sélectionnée.
     * Si une ligne est sélectionnée, la carte correspondante est supprimée en utilisant le listener,
     * et la ligne est également supprimée de la vue.
     * Sinon, une alerte d'erreur indiquant qu'aucune ligne n'a été sélectionnée est affichée.
     * Ensuite, le nombre de cartes dans le deck est affiché.
     */
    @FXML
    public void OnDeleteButtonClick(){
        ObservableList<Card> selectedRows, allPeople;
        allPeople = deckEditorView.getItems();

        selectedRows = deckEditorView.getSelectionModel().getSelectedItems();
        if (!selectedRows.isEmpty() && selectedRows.get(0)!= null) {
            listener.delete(selectedRows.get(0));
            allPeople.remove(selectedRows.get(0));
        }
        else{listener.showNoItemSelectedErrorAlert();}

        int number = deckEditorView.getItems().size();
        nbCards.setText("Nombre de cartes : " + number);
    }

    /**
     * Gestionnaire d'événements pour le bouton "Retour".
     * Cette méthode est appelée lorsque le bouton "Retour" est cliqué.
     * Elle transmet l'événement de clic au listener correspondant pour effectuer les actions nécessaires.
     */
    @FXML
    public void OnReturnButtonClick(){
        listener.quit();
    }


    /**
     * Remplace le listener courant par celui passé en paramètre.
     *
     * @param listener  le nouveau listener
     */
    public void setListener(ViewListener listener) {
        this.listener = listener;
    }

    /**
     * Remplace le deck courant par celui passé en paramètre.
     *
     * @param deck  le nouveau deck courant
     */
    public void setDeck(Deck deck) {
        deckEditorView.setItems(FXCollections.observableArrayList(deck.getCardList()));

        //Show the number of cards in the pack
        int number = deckEditorView.getItems().size();
        nbCards.setText("Nombre de cartes : " + number);

        //Affiche le nom du jeu de cartes
        String name= "Éditer le deck " + "'" + deck.getName()+  "'" ;
        nomJeu.setAlignment(Pos.CENTER);
        nomJeu.setText(name);
        String localCategory = deck.getCategory();
        this.category.setAlignment(Pos.TOP_RIGHT);
        this.category.setText(localCategory);
    }

    /**
     * Méthode d'initialisation appelée lors du chargement de la vue.
     * Cette méthode configure les cellules des colonnes de la table des cartes.
     * Elle associe les propriétés des questions et des réponses des cartes aux cellules correspondantes.
     *
     * @param location  L'URL de la vue
     * @param resources Les ressources utilisées pour localiser les éléments
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questionColumnTableView.setCellValueFactory(cellData -> cellData.getValue().getQuestionProperty());
        answerColumnTableView.setCellValueFactory(cellData -> cellData.getValue().getAnswerProperty());
    }



    /**
     * Le viewListener du DeckEditorViewController.
     */
    public interface ViewListener{

        /**
         * Méthode de rappel appelée lorsqu'un bouton "Ajouter" est cliqué.
         */
        void onAddButtonClick();

        /**
         * Méthode de rappel appelée lorsqu'une action "Quitter" est déclenchée.
         */
        void quit();

        /**
         * Méthode de rappel appelée lorsqu'une carte est modifiée.
         * @param card La carte modifiée.
         */
        void modifyCard(Card card);

        /**
         * Méthode de rappel appelée lorsqu'une carte est supprimée.
         * @param card La carte à supprimer.
         */
        void delete(Card card);

        /**
         * Méthode de rappel appelée si aucun élément n'est sélectionné.
         */
        void showNoItemSelectedErrorAlert();
    }
}
