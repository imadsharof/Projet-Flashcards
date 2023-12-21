package ulb.infof307.g02.controllers;


import ulb.infof307.g02.models.*;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe principale et super controller de l'application. Interagit avec les autres controllers pour ouvrir ou fermer
 * les différentes fenêtres de l'application.
 */
public class MainController extends Application implements MenuController.Listener,
        DeckEditorController.Listener, PlayerController.Listener, StatisticsController.Listener, CardEditorController.Listener{

    /**
     * Constructeur vide de la classe MainController.
     */
    public MainController() {
        // Constructeur vide utilisé par certaines fonctionnalités de l'application.
    }


    private DeckTable table;
    private  MenuController menuController;
    private DeckEditorController deckEditorController;

    /**
     * Méthode de démarrage de l'application.
     * Elle charge les decks depuis le fichier, affiche une alerte en cas d'erreur de poids des cartes,
     * crée et affiche le contrôleur du menu principal.
     * En cas de fermeture de l'application, les données sont sauvegardées dans le fichier.
     *
     * @param primaryStage le stage principal de l'application
     */
    @Override
    public void start(Stage primaryStage)  {

        try {
            table = new DeckTable();
            if (table.loadDecks()){
                showWeightErrorAlert();
            }
        }
        catch (IOException e){
            showReadFileErrorAlert();
        }
        try{
            menuController = new MenuController(primaryStage, this);
            menuController.setDeckTable(table);
            menuController.show();
        }
        catch (IOException e){
            showErrorAlert();
        }
        primaryStage.setOnCloseRequest(e -> {
            try {
                table.saveTable();
            } catch (IOException ex) {
                showWriteFileErrorAlert();
            }
        });
    }

    /**
     * Affiche une fenêtre d'erreur.
     * Cette methode doit être appelée en cas d'erreur lors du chargement d'une vue.
     */
    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("La fenêtre n'a pu être chargée");
        alert.setHeaderText("Un fichier nécessaire à la vue n'a pu être trouvé.");
        alert.setContentText("C'est une erreur interne. Veuillez contacter l'équipe de développement.");
        alert.showAndWait();
    }

    /**
     * Affiche une fenêtre d'erreur.
     * Cette methode doit être appelée en cas d'erreur lors du chargement d'une vue.
     */
    private void showWeightErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de lecture");
        alert.setHeaderText("Une erreur est survenue lors de la lecture des poids des cartes.");
        alert.setContentText("La somme des poids des cartes n'est pas égale à 1. Les poids vont être réinitialisés !");
        alert.showAndWait();
    }

    /**
     * Affiche une fenêtre d'erreur.
     * Cette methode doit être appelée en cas d'une erreur
     * lors d'une tentative de lecture d'un fichier du dossier DecksData.
     */
    private void showReadFileErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de lecture d'un fichier");
        alert.setHeaderText("Erreur lors de la lecture d'un fichier du dossier DecksData.");
        alert.setContentText("Le fichier est ignoré.");
        alert.showAndWait();
    }

    /**
     * Affiche une fenêtre d'erreur.
     * Cette methode doit être appelée en cas d'une erreur
     * lors d'une tentative d'écriture d'un fichier dans le dossier DecksData.
     */
    private void showWriteFileErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur d'écriture d'un fichier");
        alert.setHeaderText("Erreur lors de l'écriture d'un fichier dans le dossier DecksData.");
        alert.setContentText("Un deck n'a pas pus être enregistrer correctement.");
        alert.showAndWait();
    }

    /**
     * Ouvre la fenêtre de statistiques
     * @param stat  les statistiques à visualiser
     */
    @Override
    public void openStat(Statistics stat) {
        Stage stage = new Stage();
        StatisticsController statisticsController = new StatisticsController(stage, this, stat);
        try {
            statisticsController.show();
        }
        catch (Exception e){
            showErrorAlert();
        }
    }


    /**
     * Ouvre l'éditeur de carte
     *
     * @param card  la carte à éditer
     * @param action l'action à effectuer
     * @param deck le deck auquel la carte appartient
     */
    @Override
    public void openCardEditor(Card card, String action, Deck deck) {
        Stage stage = new Stage();
        CardEditorController editorCardController = new CardEditorController(stage);
        editorCardController.setDeck(deck);
        editorCardController.setCard(card);
        editorCardController.setAction(action);
        editorCardController.setListener(this);
        try {
            editorCardController.show();
        }
        catch (Exception e){
            showErrorAlert();
        }
    }

    /**
     * Ferme l'éditeur
     */
    @Override
    public void editorClose() {
        try {
            deckEditorController.hide();
            menuController.setDeckTable(table);
            menuController.show();
        }
        catch (Exception e){
            showErrorAlert();
        }
    }

    /**
     * Ouvre le DeckEditor
     * @param deck  le deck à éditer
     */
    @Override
    public void openEditor(Deck deck) {
        menuController.hide();
        Stage stage = new Stage();
        this.deckEditorController = new DeckEditorController(stage, this);
        this.deckEditorController.setDeck(deck);
        try {
            this.deckEditorController.show();
        }
        catch (Exception e){
            showErrorAlert();
        }
    }

    /**
     * Ouvre le player.
     * @param deck  le deck à jouer.
     */
    @Override
    public void openPlayer(Deck deck) {
        menuController.hide();
        Stage stage = new Stage();
        PlayerController playerController = new PlayerController(stage, this);
        playerController.setDeck(deck);
        try {
            playerController.show();
        }
        catch (Exception e){
            showErrorAlert();
        }
    }

    /**
     * Ferme la fenêtre du Player.
     * @param playerController  le controller de la fenêtre à fermer.
     */
    @Override
    public void playerClose(PlayerController playerController) {
        try {
            playerController.hide();
            menuController.setDeckTable(table);
            menuController.show();
        }
        catch (Exception e){
            showErrorAlert();
        }
    }

    /**
     * Ferme la fenêtre stats.
     * @param statisticsController  le controller de la fenêtre à fermer.
     */
    @Override
    public void closeStats(StatisticsController statisticsController) {
        try {
            statisticsController.hide();
            menuController.setDeckTable(table);
            menuController.show();
        }
        catch (Exception e){
            showErrorAlert();
        }
    }

    /**
     * Ferme l'application actuelle et ouvre l'éditeur de deck.
     * @param deck le deck à passer à l'éditeur.
     */
    @Override
    public void quit(Deck deck) {
        this.deckEditorController.setDeck(deck);
        try {
            this.deckEditorController.show();
        } catch (Exception e) {
            showErrorAlert();
        }
    }
}
