package ulb.infof307.g02.models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Cette classe permet de garder en mémoire tous les decks actifs.
 */
public class DeckTable {
    private final ArrayList<Deck> table;
    private final String SaveDirectory = "DecksData/";

    /**
     * Contructeur de la classe DeckTable.
     * Initialise la table avec l'attribut table.
     *
     * @param table la table servant à l'initialisation de l'attribut éponyme
     */
    public DeckTable(ArrayList<Deck> table) {
        this.table = table;
    }

    /**
     * Second constructeur de la classe, sans paramètre.
     * Initialise l'attribut table avec une liste vide.
     */
    public DeckTable() {
        this.table = new ArrayList<>();
    }

    /**
     * Récupère la liste des decks présents dans la table.
     *
     * @return la table
     */
    public List<Deck> getTable(){
        return Collections.unmodifiableList(this.table);
    }

    /**
     * Supprime le deck donné en attribut de la table.
     *
     * @param deck  le deck à supprimer
     * @throws IOException  En cas d'erreur lors de la suppression
     */
    public void deleteDeck(Deck deck) throws IOException {
        deck.delete(this.SaveDirectory);
        this.table.remove(deck);
    }

    /**
     * Ajoute le deck passé en attribut à la fin de la table.
     *
     * @param deck  le deck à ajouter
     */
    public void addDeck(Deck deck){
        this.table.add(deck);
    }

    /**
     * Charge en mémoire tous les decks stockés dans des fichiers dans le dossier DecksData.
     *
     * @return true si des exceptions liées aux poids des cartes ont été rencontrées, sinon false
     * @throws IOException  en cas d'erreur lors du chargement des decks en mémoire
     */
    public boolean loadDecks() throws IOException {
        List<String> filenames = findAllFilesInFolder(this.SaveDirectory);
        boolean checkExceptions = false;
        for(String filename : filenames){
            Deck deck = new Deck();
            try {
                deck.readDeck(this.SaveDirectory + filename);
                if(deck.getName() != null){
                    this.table.add(deck);
                    deck.checkWeights();
                }
            }catch(WeightException e){
                checkExceptions = true;
            }
        }
        return checkExceptions;
    }

    /**
     * Trouve tous les noms des fichiers présents dans le dossier dont le chemin est donné en attribut.
     *
     * @param repositoryPathName    le chemin du dossier où il faut chercher les noms des fichiers à retourner
     * @return  la liste des noms trouvés dans le dossier dont le chemin est passé en attribut
     * @throws IOException en cas d'erreur lors de la recherche des fichiers ou de création d'un nouveau dossier vide
     */
    public List<String> findAllFilesInFolder(String repositoryPathName) throws IOException {
        File folder = new File(repositoryPathName);
        ArrayList<String> filenames = new ArrayList<>();
        try {
            for (File file : Objects.requireNonNull(folder.listFiles())) {
                if (!file.isDirectory()) {
                    filenames.add(file.getName());
                }
            }
        }
        catch(NullPointerException e){
            // Crée un nouveau dossier vide
            File newDirectory = new File(this.SaveDirectory);
            if(!newDirectory.mkdir()){
                throw new IOException();
            }
        }
        return Collections.unmodifiableList(filenames);
    }

    /**
     * Enregistre le contenu de la table.
     *
     * @throws IOException en cas d'erreur lors de l'enregistrement des decks
     */
    public void saveTable() throws IOException{
        for(Deck deck : table){
            if(deck.getName() != null) {
                saveDeck(deck);
            }
        }
    }

    /**
     * Enregistre le contenu du deck passé en attribut dans le fichier de nom "src/ulb/infof307/g02/DecksData/[deckName]"
     *
     * @param deck  le deck à enregistrer
     * @throws IOException en cas d'erreur lors de l'enregistrement de la deck
     */
    public void saveDeck(Deck deck) throws IOException{
        if( deck.getName() != null) {
            String filename = this.SaveDirectory + deck.getName();
            deck.writeDeck(filename + ".txt");
            deck.writeStat(filename + "Stat.txt");
        }
    }

}
