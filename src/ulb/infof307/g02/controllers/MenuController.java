package ulb.infof307.g02.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import ulb.infof307.g02.models.Deck;
import ulb.infof307.g02.models.DeckTable;
import ulb.infof307.g02.models.Statistics;
import ulb.infof307.g02.views.DeckTableViewController;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * Controller de la fenêtre Menu. Permet d'ouvrir et de fermer la fenêtre et sert de relais entre la vue DeckTable
 * et le modèle DeckTable.
 */
public class MenuController implements DeckTableViewController.ViewListener {

    private final Listener listener;
    private final Stage stage;
    private DeckTable table;

    /**
     * Contructeur du MenuController.
     * Initialise le stage et le listener.
     * @param stage     le stage qui prend en charge la fenêtre.
     * @param listener  le listener de la classe.
     */
    public MenuController(Stage stage, Listener listener){
        this.stage = stage;
        this.listener = listener;
    }

    /**
     * Charge la vue deckTable et son controller, puis la montre.
     * @throws IOException  en cas d'erreur de chargement de la vue.
     */
    public void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(DeckTableViewController.class.getResource("DeckTableView.fxml"));
        loader.load();

        DeckTableViewController deckTableViewController = loader.getController();
        deckTableViewController.setListener(this);
        deckTableViewController.setDeckTable(table);

        Parent root = loader.getRoot();
        this.stage.setScene(new Scene(root));
        this.stage.show();
    }

    /**
     * Affiche une fenêtre d'erreur lorsqu'un deck est vide.
     * Informe l'utilisateur qu'il ne peut pas jouer avec un deck vide.
     */
    public void showDeckEmptyErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur d'ouverture d'un deck");
        alert.setHeaderText("Erreur lors de l'ouverture du deck.");
        alert.setContentText("Le deck est vide. Vous ne pouvez pas jouer!");
        alert.showAndWait();
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
     * Set la deckTable.
     * @param deckTable     la nouvelle deckTable.
     */
    public void setDeckTable(DeckTable deckTable){
        table = deckTable;
    }

    /**
     * Ajoute un deck.
     * @param deck  le deck à ajouter.
     * @return      true si le deck a été correctement ajouter, false dans le cas contraire.
     */
    public boolean addDeck(Deck deck) {
        if (deck == null || (Objects.equals(deck.getName(), "") || Objects.equals(deck.getCategory(),""))){
            // Display a confirmation dialog box
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Champs incomplets");
            alert.setHeaderText("Veuillez completer les champs ci-dessus avant d'ajouter !");

            // Wait for user response
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                return false;
            }
        }
        table.addDeck(deck);
        return true;
    }

    /**
     * Supprime un deck.
     * @param deck  le deck à supprimer.
     * @return      true si le deck est effectivement supprimé, false dans le cas contraire.
     */
    public boolean deleteDeck(Deck deck) {
        // Display a confirmation dialog box
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Les jeux dans cette catégorie seront également supprimer, ainsi que le fichier de sauvegarde du deck !");
        alert.setContentText("Cliquez sur OK pour confirmer.");

        // Wait for user response
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                table.deleteDeck(deck);
            }
            catch (IOException e){
                showErrorDeleteFileAlert();
            }
            return true;
        }
        return false;
    }

    /**
     * Affiche une fenêtre d'erreur en cas d'erreur lors d'une tentative de suppression d'un fichier.
     */
    private void showErrorDeleteFileAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Le fichier contenant le deck n'a pas pu être supprimé.");
        alert.setContentText("Si le fichier que vous vouliez supprimé est toujours là, vous devrez le supprimer manuellement.");
        alert.showAndWait();
    }

    /**
     * Contrôle du bouton ouvrir.
     * @param deck  le deck sélectionné.
     */
    @Override
    public void onOpenButtonClick(Deck deck) {
        listener.openEditor(deck);
    }

    /**
     * Contrôle du bouton jouer. Incrémente le nombre de révisions du deck.
     * @param deck  le deck sélectionné.
     */
    @Override
    public void onPlayButtonClick(Deck deck) {
        deck.incrementNumberOfRevision();
        listener.openPlayer(deck);
    }

    /**
     * Contrôle du bouton Statistiques.
     * @param statistics    Le contenant des statistiques à afficher.
     */
    @Override
    public void onStatsButtonClick(Statistics statistics) {
        listener.openStat(statistics);
    }

    /**
     * Ferme la fenêtre.
     */
    public void hide() {
        stage.hide();
    }


    /**
     * Listener du MenuController.
     */
    public interface Listener {

        /**
         * Ouvre l'éditeur de deck pour le deck spécifié.
         *
         * @param deck le deck à éditer
         */
        void openEditor(Deck deck);

        /**
         * Ouvre le joueur de cartes pour le deck spécifié.
         *
         * @param deck le deck à jouer
         */
        void openPlayer(Deck deck);

        /**
         * Ouvre les statistiques pour les statistiques spécifiées.
         *
         * @param statistics les statistiques à afficher
         */
        void openStat(Statistics statistics);
    }
}