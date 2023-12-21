package ulb.infof307.g02.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ulb.infof307.g02.models.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Le controller de vue de DeckTable
 * Cette classe gère l'affichage et la manipulation des decks dans une table.
 */
public class DeckTableViewController implements Initializable {

    /**
     * Constructeur vide de la classe DeckTableViewController.
     */
    public DeckTableViewController() {
    }

    /**
     * Vue de la table des decks qui affiche les decks dans un tableau.
     */
    public TableView<Deck> deckTableView;
    @FXML
    private TextField categoryTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TableColumn<Deck, String> nameColumnTableView;
    @FXML
    private TableColumn<Deck, String> categoryColumnTableView;

    /**
     * Listener de vue pour gérer les actions liées à la table des decks.
     */
    protected ViewListener listener;

    /**
     * Remplace le listener courant par celui passé en paramètre.
     * @param listener  le nouveau listener
     */
    public void setListener(ViewListener listener) {
        this.listener = listener;
    }

    /**
     * Méthode appelée lors du clic sur le bouton "Ouvrir".
     * Récupère la ligne sélectionnée dans le TableView et appelle le listener pour ouvrir le deck correspondant.
     * Affiche une alerte si aucune ligne n'est sélectionnée.
     */
    @FXML
    public void onOpenButtonClick() {
        ObservableList<Deck> selectedRows;

        //this gives us the rows that were selected
        selectedRows = deckTableView.getSelectionModel().getSelectedItems();
        if (!selectedRows.isEmpty() && selectedRows.get(0)!= null){
            Deck deck = selectedRows.get(0);
            listener.onOpenButtonClick(deck);
        }
        else{listener.showNoItemSelectedErrorAlert();}
    }

    /**
     * Méthode appelée lors du clic sur le bouton "Supprimer".
     * Récupère les lignes sélectionnées dans le TableView et appelle le listener pour supprimer le deck correspondant.
     * Supprime également la ligne du TableView.
     * Affiche une alerte si aucune ligne n'est sélectionnée ou si la suppression échoue.
     */
    @FXML
    public void onDeleteButtonClick() {
        ObservableList<Deck> selectedRows, allPeople;
        allPeople = deckTableView.getItems();

        //this gives us the rows that were selected
        selectedRows = deckTableView.getSelectionModel().getSelectedItems();
        if (!selectedRows.isEmpty() && selectedRows.get(0)!= null && listener.deleteDeck(selectedRows.get(0))) {
            allPeople.remove(selectedRows.get(0));
        }
        else{listener.showNoItemSelectedErrorAlert();}
    }

    /**
     * Méthode appelée lors du clic sur le bouton "Ajouter".
     * Crée un nouveau deck avec les informations fournies dans les champs de texte.
     * Appelle le listener pour ajouter le deck.
     * Ajoute également une nouvelle ligne dans le TableView.
     * Efface les champs de texte après l'ajout.
     * Affiche une alerte si l'ajout échoue.
     *
     */
    @FXML
    public void onAddButtonClick() {
        Deck deck = new Deck(nameTextField.getText(), categoryTextField.getText());
        if (listener.addDeck(deck)){
            deckTableView.getItems().add(deck);
            this.nameTextField.clear();
            this.categoryTextField.clear();
        }
    }

    /**
     * Méthode appelée lors du clic sur le bouton "Jouer".
     * Récupère la ligne sélectionnée dans le TableView et appelle le listener pour lancer le jeu avec le deck correspondant.
     * Affiche une alerte si aucune ligne n'est sélectionnée ou si le deck est vide.
     */
    @FXML
    public void onPlayButtonClick() {
        ObservableList<Deck> selectedRows;
        //this gives us the rows that were selected
        selectedRows = deckTableView.getSelectionModel().getSelectedItems();

        if (selectedRows != null && !selectedRows.isEmpty()) {
            Deck deck = selectedRows.get(0);
            if (deck.getCardList().isEmpty()) {
                listener.showDeckEmptyErrorAlert();
            }
            else {
                listener.onPlayButtonClick(deck);
            }
        }
        else{listener.showNoItemSelectedErrorAlert();}
    }

    /**
     * Méthode appelée lors du clic sur le bouton "Statistiques".
     * Récupère la ligne sélectionnée dans le TableView et appelle le listener pour afficher les statistiques correspondantes.
     * Affiche une alerte si aucune ligne n'est sélectionnée.
     */
    @FXML
    public void onStatsButtonClick(){
        ObservableList<Deck> selectedRows;
        //this gives us the rows that were selected
        selectedRows = deckTableView.getSelectionModel().getSelectedItems();
        if (selectedRows != null && !selectedRows.isEmpty()) {
            Deck deck = selectedRows.get(0);
            Statistics statistics = deck.getStatistics();
            listener.onStatsButtonClick(statistics);
        }
        else{listener.showNoItemSelectedErrorAlert();}
    }

    /**
     * Méthode d'initialisation appelée lors du chargement de la vue.
     * Cette méthode configure les colonnes du TableView avec les valeurs appropriées.
     * Elle associe les propriétés des noms et des catégories des decks aux colonnes correspondantes.
     * Initialise également le TableView avec les données de decks appropriées.
     *
     * @param location  L'URL de la vue
     * @param resources Les ressources utilisées pour localiser les éléments
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // initialiser les colonnes du TableView avec les valeurs appropriées
        nameColumnTableView.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        categoryColumnTableView.setCellValueFactory(cellData -> cellData.getValue().getCategoryProperty());

        // initialisation de la table view
        deckTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        deckTableView.setItems(FXCollections.observableArrayList(/* données de decks */)); //mettre à jour la table si des decks préinstallés
    }

    /**
     * Place les éléments du DeckTable passé en paramètre dans le deckTableView.
     * @param table la table à afficher
     */
    public void setDeckTable(DeckTable table) {
        deckTableView.setItems(FXCollections.observableArrayList(table.getTable()));
    }

    /**
     * Le ViewListener du DeckTableViewController.
     */
    public interface ViewListener {

        /**
         * Méthode de rappel appelée lorsqu'une demande d'ajout de deck est effectuée.
         * @param deck Le deck à ajouter.
         * @return Vrai si le deck a été ajouté avec succès, sinon faux.
         */
        boolean addDeck(Deck deck);

        /**
         * Méthode de rappel appelée lorsqu'une demande de suppression de deck est effectuée.
         * @param deck Le deck à supprimer.
         * @return Vrai si le deck a été supprimé avec succès, sinon faux.
         */
        boolean deleteDeck(Deck deck);

        /**
         * Méthode de rappel appelée lorsqu'un bouton "Ouvrir" est cliqué pour un deck spécifique.
         * @param deck Le deck à ouvrir.
         */
        void onOpenButtonClick(Deck deck);

        /**
         * Méthode de rappel appelée lorsqu'un bouton "Jouer" est cliqué pour un deck spécifique.
         * @param deck Le deck à jouer.
         */
        void onPlayButtonClick(Deck deck);

        /**
         * Méthode de rappel appelée lorsqu'un bouton "Statistiques" est cliqué pour des statistiques spécifiques.
         * @param statistics Les statistiques à afficher.
         */
        void onStatsButtonClick(Statistics statistics);

        /**
         * Méthode de rappel appelée lorsqu'une erreur de deck vide est détectée.
         */
        void showDeckEmptyErrorAlert();

        /**
         * Méthode de rappel appelée si aucun élément n'est sélectionné.
         */
        void showNoItemSelectedErrorAlert();
    }
}
