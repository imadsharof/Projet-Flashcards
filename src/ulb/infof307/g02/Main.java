package ulb.infof307.g02;

import javafx.application.Application;
import ulb.infof307.g02.controllers.MainController;

/**
 * La classe principale de l'application.
 */
public class Main {

    /**
     * Constructeur vide de la classe Main.
     */
    public Main() {
    }
    /**
     * Méthode principale qui lance l'application.
     * @param args les arguments de la ligne de commande.
     */
    public static void main(final String[] args) {
        Application.launch(MainController.class, args);
    }
}