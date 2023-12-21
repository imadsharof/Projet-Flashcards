package ulb.infof307.g02.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Cette classe représente un deck de carte. Elle possède un nom et une catégorie.
 * Les cartes sont rangées dans une arrayList.
 */
public class Deck {
    private final StringProperty  name;
    private final StringProperty category;
    private final ArrayList<Card> cardList;
    private final Statistics statistics;
    private int  totalNumberofCards;

    /**
     * Constructeur de la classe Deck. Initialise le nom, la catégorie et la liste des cartes.
     */
    public Deck() {
        this.name = new SimpleStringProperty();
        this.category = new SimpleStringProperty();
        this.cardList = new ArrayList<>();
        this.statistics = new Statistics();
        this.totalNumberofCards=0;
    }

    /**
     * Deuxième version du constructeur de la classe Deck. Initialise le nom, la catégorie et la liste des cartes.
     * Le nom et la catégorie reçoivent directement une valeur.
     * @param name      le nom du deck
     * @param category  la catégorie du deck
     */
    public Deck(String name, String category) {
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
        this.cardList = new ArrayList<>();
        this.statistics = new Statistics();
        this.totalNumberofCards=0;
    }

    /**
     * Renvoie le score
     *
     * @return  le score
     */
    public float getScore(){
        return this.statistics.getScore();
    }

    /**
     * Renvoie le nombre de cartes jouées
     *
     * @return  le nombre de cartes jouées
     */
    public int getNumberOfCardsPlayed(){
        return this.statistics.getNumberOfCardsPlayed();
    }

    /**
     * Renvoie le nombre de révisions du deck
     *
     * @return  le nombre de révisions du deck
     */
    public int getNumberOfRevision(){
        return this.statistics.getNumberOfRevision();
    }

    /**
     * Renvoie le niveau de réponse total
     *
     * @return  le niveau de réponse total
     */
    public float getAnswerLevel(){
        return this.statistics.getAnswerLevel();
    }

    /**
     * Met à jour les statistiques.
     * @param levelAnswer valeur d'incrémentation du score, dépendant du niveau
     *                    de connaissance de la question.
     */
    public void updateStat(float levelAnswer) {
        this.statistics.increaseAnswerLevel(levelAnswer);
        float answersLevelTot=this.statistics.getAnswerLevel();
        this.statistics.increaseNumberOfCardsPlayed();
        int nbCardsPlayed= this.statistics.getNumberOfCardsPlayed();
        this.statistics.score(answersLevelTot,nbCardsPlayed);
    }

    /**
     * Renvoie le nom du deck
     *
     * @return le nom du deck
     */
    public StringProperty getNameProperty() {
        return name;
    }

    /**
     * Renvoie la catégorie du deck
     *
     * @return la catégorie du deck
     */
    public StringProperty getCategoryProperty() {
        return category;
    }


    /**
     * Cette méthode retourne le nom du paquet de cartes.
     *
     * @return le nom du paquet de cartes
     */
    public String getName() {
        return name.get();
    }

    /**
     * Cette méthode retourne la liste des catégories du paquet de cartes.
     *
     * @return la liste des catégories du paquet de cartes
     */
    public String getCategory() {
        return this.category.get();
    }

    /**
     * Reset les poids de chaque carte.
     */
    public void  resetWeight() {
        for(Card card1 : this.cardList){
            card1.setWeight(1.0/(this.size())); //remise à niveau des poids des cartes à chaque modif
        }
    }

    /**
     * Ajoute la carte passée en attribut à la fin du deck.
     *
     * @param card  la card à ajouter au deck
     */
    public void newCard(Card card) {
        this.cardList.add(card);
        this.totalNumberofCards++;
        resetWeight();
    }



    /**
     * Supprime du deck la carte passée en attribut.
     *
     * @param card  la carte à supprimer.
     */
    public void removeCard(Card card) {
        this.cardList.remove(card);
        resetWeight();
    }

    /**
     * Renvoie la liste des cartes.
     *
     * @return  la liste des cartes
     */
    public List<Card> getCardList(){
        return Collections.unmodifiableList(this.cardList);
    }

    /**
     * Retourne la carte en position index.
     *
     * @param index    la position de la carte à retourner
     * @return  la carte à la position index
     */
    public Card getCard(int index){
        return cardList.get(index);
    }

    /**
     * Retourne la question de la carte en position index dans la liste de carte du deck.
     *
     * @param index    la position de la carte dont on veut la question
     * @return  la question ou "Ce deck ne contient pas de question" si le deck est vide
     */
    public String getQuestion(int index){
        if(cardList.size() > 0) {
            Card card = getCard(index);
            return card.getQuestion();
        }
        else return "Ce deck ne contient pas de question";
    }

    /**
     * Retourne la réponse de la carte en position index dans la liste de carte du deck.
     *
     * @param index    la position de la carte dont on veut la réponse
     * @return  la réponse ou "Ce deck ne contient pas de réponse" si le deck est vide
     */
    public String getAnswer(int index){
        if(cardList.size() > 0) {
            Card card = getCard(index);
            return card.getAnswer();
        }
        else return "Ce deck ne contient pas de réponse";
    }

    /**
     * Retourne la taille du deck, c'est-à-dire le nombre de cartes dans le deck.
     *
     * @return la taille du deck
     */
    public int size(){
        return this.cardList.size();
    }

    /**
     * Lis une ligne dans le buffer en paramètre et renvoie l'élément après " : " dans la ligne.
     *
     * @param br  Le buffer de lecture du fichier
     * @return  L'élément utile (le nom ou la catégorie)
     * @throws IOException  en cas d'erreur lors de la lecture du buffer
     */
    public String readBufferedElement(BufferedReader br) throws IOException{
        String line = br.readLine();
        if(line != null){
            String[] token = line.split(" : ");
            return token[1];
        }
        else return "";
    }

    /**
     * Lis un deck de carte stocké dans un fichier txt.
     *
     * @param filename le chemin du fichier
     * @throws IOException si une erreur de lecture du fichier se produit
     */
    public void readDeck(String filename) throws IOException {
        if (!filename.contains("Stat")) {
            File file = new File(filename);
            try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
                this.name.set(readBufferedElement(br).strip());
                this.category.set(readBufferedElement(br).strip());
                skipLine(br); // Passe une ligne (la ligne "cartes :" n'est pas utilisée)
                processCards(br);
                readStatFile(filename);
            }
        }
    }

    private void skipLine(BufferedReader br) throws IOException {
        br.readLine();
    }

    private void processCards(BufferedReader br) throws IOException {
        String buffer = "";
        Card card = null;
        int readChar;
        while ((readChar = br.read()) != -1) {
            char character = (char) readChar;
            if (character == '{') {
                card = new Card();
            } else if (character == '|' && card != null) {
                card.modifyQuestion(buffer);
                buffer = "";
            } else if (character == '[' && card != null) {
                card.modifyAnswer(buffer);
                buffer = "";
            } else if (character == '}' && card != null) {
                card.setWeight(Double.parseDouble(buffer));
                buffer = "";
                this.cardList.add(card);
                readChar = br.read();
                character = (char) readChar;
                if (character != '\n' && readChar != -1) {
                    buffer += character;
                }
            } else if (character == '\n') {
                // do nothing
            } else {
                buffer += character;
            }
        }
    }
    /**
     * Vérifie les poids des cartes dans le deck.
     *
     * @throws WeightException si la somme des poids des cartes est inférieure à 0.99 ou supérieure à 1.01.
     *                         Dans ce cas, les poids des cartes sont réinitialisés et une WeightException est lancée.
     */
    public void checkWeights()throws WeightException{
        double sumOfWeights = 0.0;
        for(Card card: this.cardList){
            sumOfWeights += card.getWeight();
        }
        if (cardList.size() > 0 && (sumOfWeights <0.99 || sumOfWeights>1.01)){
            resetWeight();
            throw new WeightException();
        }
    }

    /**
     * Lis le fichier Stat associé au deck. Le chemin du fichier doit être passé en argument.
     *
     * @param fileName  le chemin du fichier Stat
     * @throws IOException  En cas d'une erreur lors de la lecture du fichier
     */
    public void readStatFile(String fileName) throws IOException {
        String statFileName = fileName.substring(0,fileName.length()-4) + "Stat.txt";
        statistics.read(statFileName);
    }


    /**
     * Stocke un jeu de carte dans un fichier txt.
     *
     * @param filename le chemin du fichier
     * @throws IOException si une erreur d'écriture dans le fichier se produit
     */
    public void writeDeck(String filename) throws IOException {
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write("Nom : " + this.name.get() + "\n");
            fw.write("Catégorie : " + this.category.get() + "\n");
            fw.write("Cartes : \n");
            for (Card card : this.cardList) {
                card.storage_txt(fw);
            }
        } // Le FileWriter sera automatiquement fermé à la fin du bloc try
    }

    /**
     * Met à jour les poids de toutes les cartes du deck.
     *
     * @param index    la position de la carte de référence dans le deck
     * @param multiplier    le multiplicateur utilisé pour modifier le poids des cartes
     */
    public void updateWeights(int index, double multiplier) {
        Card card = cardList.get(index);
        // Vérifier si le paquet de carte contient au moins 2 cartes
        if (this.size() < 2) {
            return;
        }
        //r est le coefficient qui va être appliqué à toutes les autres cartes sauf celle jouée
        double r = (1- card.getWeight()*multiplier)/(1- card.getWeight());
        double newWeight = card.getWeight()*multiplier;
        if (newWeight > 1/(10.0*size()) && newWeight < 4.0/size() && acceptedChange(r, card)){
            card.setWeight(newWeight);
            for(Card card2 : this.getCardList()){
                if(card2 != card){
                    card2.setWeight(card2.getWeight() * r);
                }

            }
        }

    }

    /**
     * Vérifie que les bornes des poids ne sont dépassées pour aucune cartes.
     *
     * @param r le modificateur des poids des cartes
     * @param card  la carte jouée (celle dont le poids est directement modifié)
     * @return  true si les bornes ne sont pas dépassées, false dans le cas contraire
     */
    boolean acceptedChange(double r, Card card){
        boolean acceptedChange = true;
        for (Card card2 : cardList) {
            if (card != card2) {
                double newWeight = card2.getWeight() * r;
                if (newWeight < 1.0/(10.0 * size()) || newWeight > 4.0/size()) {
                    acceptedChange = false;
                }
            }
        }
        return acceptedChange;
    }

    /**
     * Initialise les poids de toutes les cartes du deck de manière équipondérée.
     */
    public void setInitialWeight(){
        int deckSize = this.size();
        if(this.allWeightsEqualOne()) {
            for (Card card : this.cardList) {
                double newWeight = card.getWeight() / deckSize;
                card.setWeight(newWeight);
            }
        }
    }

    /**
     * Vérifie si le poids de toutes les cartes vaut 1.
     *
     * @return  true si le poids de toutes les cartes vaut 1, false dans le cas contraire
     */
    public boolean allWeightsEqualOne(){
        boolean allWeightsEqualOne = true;
        for (Card card : this.cardList) {
            if (card.getWeight() != 1.0) {
                allWeightsEqualOne = false;
                break;
            }
        }
        return allWeightsEqualOne;
    }

    /**
     * Récupère le poids de chaque carte du deck et retourne une liste contenant les résultas.
     *
     * @return  la liste des poids de toutes les cartes du deck
     */
    public List<Double> getAllWeights() {
        List<Double> weights = new ArrayList<>();
        for(Card card : cardList){
            weights.add(card.getWeight());
        }
        return Collections.unmodifiableList(weights);
    }

    /**
     * Supprime le fichier de sauvegarde du deck.
     *
     * @param directory le dossier où doit se trouver le fichier
     * @throws IOException  en cas d'erreur lors de la suppression
     */
    public void delete(String directory) throws IOException {
        String filename = directory + this.name.get();
        Path path = Paths.get(filename);
        Files.deleteIfExists(path);
        deleteStat(directory);
    }

    /**
     * Supprime le fichier Stat du deck.
     *
     * @param directory le dossier où doit se trouver le fichier
     * @throws IOException  en cas d'erreur lors de la suppression
     */
    private void deleteStat(String directory) throws IOException {
        String filename = directory + this.name.get() + "Stat";
        Path path = Paths.get(filename);
        Files.deleteIfExists(path);
    }

    /**
     * Incrémente le nombre de révisions du deck.
     */
    public void incrementNumberOfRevision(){
        statistics.increaseNumberOfRevision();
    }

    /**
     * Enregistre les statistiques du deck dans un fichier Stat.
     * Le nom de ce fichier est "nom du deck" + "Stat".
     *
     * @param filename  le chemin du fichier
     * @throws IOException  en cas d'erreur lors de l'écriture du fichier
     */
    public void writeStat(String filename) throws IOException {
        this.statistics.write(filename);
    }

    /**
     * Récupère le nombre total de cartes dans le deck.
     *
     * @return le nombre total de cartes dans le deck
     */
    public int getNumberOfCards() { return totalNumberofCards;
    }

    /**
     * Récupère les statistiques liées au deck
     * @return l'objet Statistics lier au deck
     */
    public Statistics getStatistics() {
        return this.statistics;
    }

    /**
     * Démarre la session d'étude en enregistrant l'heure de début dans les statistiques.
     */
    public void startStudying() {
        this.statistics.startStudying();
    }

    /**
     * Termine la session d'étude en enregistrant l'heure de fin dans les statistiques.
     */
    public void endStudying() {
        this.statistics.endStudying();
    }
}