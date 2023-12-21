package ulb.infof307.g02.views;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import org.scilab.forge.jlatexmath.TeXFormula;
import ulb.infof307.g02.models.Card;

import java.awt.image.BufferedImage;
import java.util.Objects;


/**
 * Controller de vue de CardEditor
 */
public class CardEditorViewController {

    /**
     * Constructeur vide de la classe CardEditorViewController.
     */
    public CardEditorViewController() {
        //
    }

    /**
     * L'ImageView pour afficher une représentation LaTeX de la question.
     */
    @FXML
    public ImageView qLatex;

    /**
     * L'ImageView pour afficher une représentation LaTeX de la réponse.
     */
    @FXML
    public ImageView aLatex;
    @FXML
    private Button save;
    @FXML
    private Button txt;
    @FXML
    private Button txtTrous;
    @FXML
    private TextArea textAreaQuestion;
    @FXML
    private TextArea textAreaAnswer;

    /**
     * Écouteur de la vue pour gérer les interactions et les événements utilisateur.
     */
    protected ViewListener listener;
    private String type;

    /**
     * Le viewListener devient celui passé en paramètre
     * @param listener  le nouveau listener
     */
    public void setListener(ViewListener listener){
        this.listener = listener;
    }

    /**
     * La carte courante devient celle passée en paramètre
     * @param card  la nouvelle carte courante
     */
    public void setCard(Card card){
        save.setDisable(true);
        if(!card.getQuestion().equals("")){
            textAreaQuestion.setText(card.getQuestion());
        }
        if(!card.getAnswer().equals("")) {
            textAreaAnswer.setText(card.getAnswer());
        }
    }

    /**
     * Gestionnaire d'événements pour le bouton "Enregistrer".
     * Cette méthode est appelée lorsque le bouton "Enregistrer" est cliqué.
     * Elle récupère le contenu des zones de texte pour la question et la réponse,
     * puis vérifie si elles sont vides. Si l'une des zones de texte est vide,
     * une alerte d'erreur est affichée en appelant le listener approprié.
     * Sinon, en fonction du type de carte sélectionné, les données sont envoyées
     * au listener pour être enregistrées.
     */
    @FXML
    private void onSaveButtonClick() {
        String question = textAreaQuestion.getText();
        String answer = textAreaAnswer.getText();
        if (Objects.equals(question, "") || Objects.equals(answer, "")) {
            listener.showQREmptyErrorAlert();
        }
        else {
            if(Objects.equals(type,"texte")){listener.saveCard(question, answer);}
            else if (Objects.equals(type,"texte à trous")){listener.saveTexte(question, answer);}

        }
    }

    /**
     * Gestionnaire d'événements pour le bouton "Texte".
     * Cette méthode est appelée lorsque le bouton "Texte" est cliqué.
     * Elle met à jour le type de carte sélectionné en "texte",
     * désactive le bouton "Texte" et le bouton "Texte à trous",
     * et active le bouton "Enregistrer".
     */
    @FXML
    private void onTxtButtonClick(){
        type="texte";
        save.setDisable(false);
        txt.setDisable(true);
        txtTrous.setDisable(true);
    }

    /**
     * Gestionnaire d'événements pour le bouton "Texte à trous".
     * Cette méthode est appelée lorsque le bouton "Texte à trous" est cliqué.
     * Elle met à jour le type de carte sélectionné en "texte à trous",
     * désactive le bouton "Texte" et le bouton "Texte à trous",
     * et active le bouton "Enregistrer".
     */
    @FXML
    private void onTxtTrousButtonClick(){
        type="texte à trous";
        save.setDisable(false);
        txt.setDisable(true);
        txtTrous.setDisable(true);
    }

    /**
     * Crée une image à partir d'un code LaTeX donné.
     *
     * @param latexCode le code LaTeX
     * @return une image BufferedImage
     */
    public static BufferedImage createLatexImage(String latexCode) {
        // Vérifie si le code LaTeX est déjà une formule mathématique ou non
        latexCode = latexCode.replace("\n", "\\\\");
        latexCode = "\\text{" + latexCode + "}";
        TeXFormula formula = new TeXFormula(latexCode);
        return (BufferedImage) formula.createBufferedImage(TeXFormula.SERIF, 30, java.awt.Color.black, java.awt.Color.white);
    }


    /**
     * Gestionnaire d'événements pour le bouton "Afficher LaTeX".
     * Cette méthode est appelée lorsque le bouton "Afficher LaTeX" est cliqué.
     * Elle récupère le code LaTeX de la question et de la réponse depuis les champs de texte correspondants,
     * puis génère les images LaTeX correspondantes.
     * Les images LaTeX sont ensuite affichées dans les composants d'image correspondants dans l'interface utilisateur.
     */
    @FXML
    private void onLatexViewButtonClick() {
        BufferedImage qImage = createLatexImage(textAreaQuestion.getText());
        // Conversion de l'image en format JavaFX pour l'affichage
        Image fxQImage = SwingFXUtils.toFXImage(qImage, null);
        qLatex.setImage(fxQImage);

        BufferedImage aImage = createLatexImage(textAreaAnswer.getText());
        Image fxAImage = SwingFXUtils.toFXImage(aImage, null);
        aLatex.setImage(fxAImage);

    }


    @FXML
    private void onCancelButtonClick(){
            listener.cancel();
    }

    /**
     * ViewListener du CardEditorViewController
     */
    public interface ViewListener {

        /**
         * Méthode appelée pour sauvegarder un texte (question et réponse) depuis une vue.
         *
         * @param question la question à sauvegarder
         * @param answer   la réponse à sauvegarder
         */
        void saveTexte(String question, String answer);

        /**
         * Méthode appelée pour sauvegarder une carte (question et réponse) depuis une vue.
         *
         * @param question la question de la carte à sauvegarder
         * @param answer   la réponse de la carte à sauvegarder
         */
        void saveCard(String question, String answer);

        /**
         * Méthode appelée pour afficher une alerte d'erreur lorsque le champ QR est vide.
         */
        void showQREmptyErrorAlert();

        /**
         * Méthode appelée pour annuler une action depuis une vue.
         */
        void cancel();
    }
}


