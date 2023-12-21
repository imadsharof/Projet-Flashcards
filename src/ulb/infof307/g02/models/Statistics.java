package ulb.infof307.g02.models;

import java.io.*;

/**
 * Cette classe représente les statistiques liées à un deck.
 * Elle contient un score, le nombre de cartes jouées, le nombre de révisions du deck et le niveau de réponse total du deck.
 */
public class Statistics {


    /**
     * Le score du deck
     */
    protected float score;

    /**
     * Le nombre de cartes jouées
     */
    protected int numberOfCardsPlayed;

    /**
     * Le nombre de révisions
     */
    protected int numberOfRevision;

    /**
     * Le niveau de réponse du joueur.
     * Ce niveau représente la performance du joueur dans la précision de ses réponses.
     */
    protected float answerLevel;

    /**
     * Le temps d'étude.
     */
    protected long studyingTime;
    private long startTime;


    /**
     *
     * Constructeur. Iniatialise les valeurs des attributs par defaut.
     */
    public Statistics(){
        score=0;
        numberOfCardsPlayed=0;
        numberOfRevision=0;
        answerLevel=0;
        studyingTime=0;
    }


    /**
     *
     * Incremente le nombre de revision.
     */
    public void increaseNumberOfRevision(){numberOfRevision++;}

    /**
     * Renvoie le nombre de revision
     *
     * @return  le nombre de revision
     */
    public int getNumberOfRevision() {return numberOfRevision;}

    /**
     *
     * Incremente le nombre de cartes jouées.
     */

    public void increaseNumberOfCardsPlayed(){numberOfCardsPlayed++;}

    /**
     * Renvoie le nombre de cartes jouées.
     *
     * @return  le nombre de cartes jouées
     */
    public int getNumberOfCardsPlayed(){return numberOfCardsPlayed;}

    /**
     * Calcul du score.
     *
     * @param answersLvl : le niveau de connaissance total (du joueur) des cartes jouées
     * @param numberOfCardsPlayed : le nombre de cartes jouées
     */
    public void score(float answersLvl, int numberOfCardsPlayed){
        score = (float) (Math.round( answersLvl/numberOfCardsPlayed * 100.0) / 100.0);
    }

    /**
     * Renvoie le score en fonction des cartes jouées.
     *
     * @return  le score en fonction des cartes jouées
     */
    public float getScore() {return score;}

    /**
     *
     * Incremente le niveau de connaissance total (du joueur) des cartes jouées.
     *
     * @param newLvl: niveau de connaissance (du joueur) de la carte actuelle
     */
    public void increaseAnswerLevel(float newLvl){answerLevel+=newLvl;}

    /**
     * Renvoie le niveau de connaissance total des cartes jouées.
     *
     * @return  le niveau de connaissance total des cartes jouées
     */
    public float getAnswerLevel(){return answerLevel;}

    /**
     * Remet toutes les statistiques à zéro.
     */
    public void reset() {
        score = 0;
        numberOfCardsPlayed = 0;
        numberOfRevision = 0;
        answerLevel=0;
        studyingTime = 0;
    }

    /**
     * Récupère le temps total d'étude.
     *
     * @return le temps total d'étude.
     */
    public long getStudyingTime() {
        return studyingTime;
    }

    /**
     * Démarre la session d'étude en enregistrant l'heure de début.
     */
    public void startStudying(){
        startTime = System.nanoTime();
    }


    /**
     * Termine la session d'étude en enregistrant l'heure de fin et en mettant à jour le temps total d'étude.
     */
    public void endStudying(){
        long endTime = System.nanoTime();
        studyingTime += (endTime - startTime)/1000000000;
    }


    /**
     * Enregistre les statistiques dans un fichier dont le chemin est donnée en paramètre.
     *
     * @param filename  le chemin du fichier
     * @throws IOException  en cas d'erreur lors de l'écriture du fichier
     */
    public void write(String filename) throws IOException {
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write("Score : " + this.score + "\n");
            fw.write("AnswerLevel : " + this.answerLevel + "\n");
            fw.write("NumberOfCardsPlayed :" + this.numberOfCardsPlayed + "\n");
            fw.write("NumberOfRevision :" + this.numberOfRevision + "\n");
            fw.write("StudyingTime :" + this.studyingTime + "\n");
        } // Le FileWriter sera automatiquement fermé à la fin du bloc try
    }

    /**
     * Lis les statistiques du fichier dont le chemin est donné en paramètre.
     *
     * @param fileName  le chemin du fichier
     * @throws IOException  en cas d'erreur lors de la lecture
     */
    public void read(String fileName) throws IOException {
        File f = new File(fileName);
        if(f.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        switch (key) {
                            case "Score" -> this.score = Float.parseFloat(value);
                            case "AnswerLevel" -> this.answerLevel = Float.parseFloat(value);
                            case "NumberOfCardsPlayed" -> this.numberOfCardsPlayed = Integer.parseInt(value);
                            case "NumberOfRevision" -> this.numberOfRevision = Integer.parseInt(value);
                            case "StudyingTime" -> this.studyingTime = Long.parseLong(value);
                            default -> {
                            }
                            // Gérer le cas où la valeur de 'key' ne correspond à aucun des cas précédents
                            // Vous pouvez ignorer, signaler une erreur ou effectuer une autre action appropriée.
                        }
                    }
                }
            }
        }
    }
}
