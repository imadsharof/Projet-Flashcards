package ulb.infof307.g02.views;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.scilab.forge.jlatexmath.TeXFormula;
import ulb.infof307.g02.models.Deck;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Contrôleur de vue de PlayDeck
 */
public class PlayDeckViewController implements Initializable {

    /**
     * Constructeur vide de la classe PlayDeckViewController.
     */
    public PlayDeckViewController() {
        //
    }

    @FXML
    private ImageView QAText;
    @FXML
    private Label nomCat;
    @FXML
    private Rectangle card;
    @FXML
    private Label labelQorA;
    @FXML
    private Button buttonTB;
    @FXML
    private Button buttonB;
    @FXML
    private Button buttonMo;
    @FXML
    private Button buttonM;
    @FXML
    private Button buttonTM;
    @FXML
    private Button buttonSwitch;

    private boolean QuestionAnswered = false;

    /**
     * Écouteur de vue pour gérer les actions liées à la lecture du deck.
     */
    protected ViewListener listener;

    /**
     * Définit le deck d'étude à utiliser
     * @param deck  le deck d'étude
     */
    public void setDeck(Deck deck) {
        //Afficher le nom et catégorie du jeu de cartes
        String nomEtCat= "Nom: " +deck.getName()+ "\n"+ "Catégorie: " + deck.getCategory();
        nomCat.setAlignment(Pos.TOP_RIGHT);
        nomCat.setText(nomEtCat);

    }
    /**
     * Crée une image à partir d'un code LaTeX donné.
     *
     * @param latexCode le code LaTeX
     * @return une image BufferedImage
     */
    public static BufferedImage createLatexImage(String latexCode) {
        // Vérifie si le code LaTeX est déjà une formule mathématique ou no
        latexCode = latexCode.replace("\n", "\\\\");
        latexCode = "\\text{" + latexCode + "}";
        TeXFormula formula = new TeXFormula(latexCode);
        return (BufferedImage) formula.createBufferedImage(TeXFormula.SERIF, 30, java.awt.Color.black, java.awt.Color.white);
    }


    /**
     * Méthode d'initialisation appelée lorsque la vue est chargée.
     * Initialise la carte graphique et désactive les boutons d'évaluation.
     *
     * @param url             L'URL de la ressource de la vue.
     * @param resourceBundle Les ressources utilisées par la vue.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Création et paramétrage de la carte graphique
        card = new Rectangle(300, 500, Color.WHITE);
        card.setStroke(Color.BLACK);

        // Désactiver les boutons d'évaluation au début
        buttonTB.setDisable(true);
        buttonB.setDisable(true);
        buttonMo.setDisable(true);
        buttonM.setDisable(true);
        buttonTM.setDisable(true);
    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton Quitter.
     */
    @FXML
    private void onQuitButtonClick(){
        listener.quit();
    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton Switch.
     * Gère le basculement entre la question et la réponse de la carte.
     * Active ou désactive les boutons en conséquence.
     */
    @FXML
    private void onSwitchButtonClick() {
        listener.onSwitchButtonClick();
        /*
         * Une fois les poids des cartes initialisés,
         * si la question n'a pas encore été répondue
         */
        if (!QuestionAnswered) {
            QuestionAnswered = true;
            // Afficher la question de la carte aléatoire
            changeCardContent(listener.getQuestion());
            changeLabelContent("Question :");
            buttonSwitch.setText("Réponse");
            DisablingButton();
        }
        /*
         * Si la question a été répondue
         */
        else {
            // Afficher la réponse de la carte aléatoire
            changeCardContent(listener.getAnswer());
            changeLabelContent("Réponse :");

            // Activer les boutons d'évaluation
            EnablingButton();
            buttonSwitch.setDisable(true);
            buttonSwitch.setText("Carte suivante");
        }
    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton TB.
     * Réactive la carte pour une révision ultérieure en changeant le niveau.
     */
    @FXML
    private void onTbButtonClick() {
        QuestionAnswered = false;
        buttonSwitch.setDisable(false);
        int levelAnswer = 5;
        listener.evaluationButtonClick(levelAnswer);
        DisablingButton();
    }
    /**
     * Méthode appelée lorsqu'on clique sur le bouton B.
     * Réactive la carte pour une révision ultérieure en changeant le niveau.
     */
    @FXML
    public void onBButtonClick() {
        QuestionAnswered = false;
        buttonSwitch.setDisable(false);
        int levelAnswer=4;
        listener.evaluationButtonClick(levelAnswer);
        DisablingButton();
    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton Mo.
     * Réactive la carte pour une révision ultérieure en changeant le niveau.
     */
    @FXML
    private void onMoButtonClick() {
        QuestionAnswered = false;
        buttonSwitch.setDisable(false);
        int levelAnswer=3;
        listener.evaluationButtonClick(levelAnswer);
        DisablingButton();
    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton M.
     * Réactive la carte pour une révision ultérieure en changeant le niveau.
     */
    @FXML
    private void onMButtonClick() {
        QuestionAnswered = false;
        buttonSwitch.setDisable(false);
        int levelAnswer=2;
        listener.evaluationButtonClick(levelAnswer);
        DisablingButton();
    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton Tm.
     * Réactive la carte pour une révision ultérieure en changeant le niveau.
     */
    @FXML
    private void onTmButtonClick() {
        QuestionAnswered = false;
        buttonSwitch.setDisable(false);
        int levelAnswer=1;
        listener.evaluationButtonClick(levelAnswer);
        DisablingButton();
    }

     /**
     * Set le listener de la vue.
     * @param listener  le nouveau listener
     */
    public void setListener(ViewListener listener) {
        this.listener = listener;
    }

    /**
     * Désactive tous les boutons de scores.
     */
    private void DisablingButton() {
        buttonTB.setDisable(true);
        buttonB.setDisable(true);
        buttonMo.setDisable(true);
        buttonM.setDisable(true);
        buttonTM.setDisable(true);
    }

    /**
     * Active tous les boutons de scores.
     */
    private void EnablingButton() {
        buttonTB.setDisable(false);
        buttonB.setDisable(false);
        buttonMo.setDisable(false);
        buttonM.setDisable(false);
        buttonTM.setDisable(false);
    }

    /**
     * Change le contenu de label Qora.
     * @param newText   le nouveau contenu
     */
    public void changeLabelContent(String newText) {
        labelQorA.setText(newText);
    }

    /**
     * Change le contenu da QAText.
     * @param newText   le nouveau contenu
     */
    public void changeCardContent(String newText){
        BufferedImage image = createLatexImage(newText);
        Image fxImage = SwingFXUtils.toFXImage(image, null);
        QAText.setImage(fxImage);
    }

    /**
     * Le ViewListener du PlayDeckViewController.
     */
    public interface ViewListener{

        /**
         * Récupère la question saisie dans la vue.
         * @return La question saisie.
         */
        String getQuestion();

        /**
         * Récupère la réponse saisie dans la vue.
         * @return La réponse saisie.
         */
        String getAnswer();

        /**
         * Méthode de rappel appelée lorsqu'un bouton de commutation est cliqué.
         */
        void onSwitchButtonClick();

        /**
         * Méthode de rappel appelée lorsqu'un bouton d'évaluation est cliqué.
         * @param levelAnswer Le niveau d'évaluation de la réponse.
         */
        void evaluationButtonClick(int levelAnswer);

        /**
         * Méthode de rappel appelée lorsqu'une demande de fermeture est effectuée.
         */
        void quit();
    }
}
