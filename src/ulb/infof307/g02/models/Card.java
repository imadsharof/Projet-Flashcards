package ulb.infof307.g02.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Cette classe représente une carte. Elle contient une question, une réponse et un degre de connaissance
 */
public class Card {
    private final StringProperty question;
    private final StringProperty answer;
    private double weight ;

    /**
     * Constructeur. Iniatialise les valeurs des attributs par defaut.
     */
    public Card(){
        question = new SimpleStringProperty();
        answer = new SimpleStringProperty();
        question.set("");
        answer.set("");
        weight = 1.0 ;
    }



    /**
     * Retourne le poids de la carte.
     *
     * @return le poids (weight)
     */
    public double getWeight() {
        return weight;
    }

    /**
     * modifie le poids (weight).
     * @param weight    la nouvelle valeur du poids
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Modifie la question de la carte.
     *
     * @param new_question  la nouvelle question de la carte
     * @param new_answer la nouvelle réponse de la carte
     */
    public void modifyTexte(String new_question, String new_answer){
        char[] caractereQ = new_question.toCharArray();
        char[] caractereA = new_answer.toCharArray();

        List<String> listAnswer = new ArrayList<>();
        String realQuestion = "";
        String realAnswer = "";
        int cle=0;
        int i=0;

        String buffer="";
        for (char c : caractereA){
            if(c==','){
                listAnswer.add(buffer);
                buffer="";
            }
            else{
                buffer+=c;
            }
        }
        listAnswer.add(buffer);

        for(char c : caractereQ){
            if(c=='('){
                cle+=1;
                realQuestion+='_';
                realAnswer+= listAnswer.get(i);
                i+=1;
            }
            else if(c==')'){
                cle-=1;
                realQuestion+='_';
            }
            else if(cle==0){
                realQuestion+=c;
                realAnswer+=c;
            }
        }
        question.set(realQuestion);
        answer.set(realAnswer);
    }

    /**
     * Modifie la question de la carte.
     * @param new_question la nouvelle question
     */
    public void modifyQuestion(String new_question){
        question.set(new_question);
    }

    /**
     * Modifie la reponse de la carte.
     * @param new_answer   la nouvelle reponse
     */
    public void modifyAnswer(String new_answer){
        answer.set(new_answer);
    }


    /**
     * Enregistre la question et la reponse dans le fichier donné.
     * @param fw  le "file descriptor" du fichier.
     * @throws IOException si une erreur se produit lors de l'écriture dans le fichier
     */
    public void storage_txt(FileWriter fw) throws IOException{
        fw.write("{" + question.get() + "|" + answer.get() + "["+ weight + "}\n");
    }

    /**
     * Récupère la question de la carte.
     * @return  la question
     */
    public String getQuestion(){
        return question.get();
    }

    /**
     * Récupère la réponse de la carte.
     * @return  la reponse
     */
    public String getAnswer(){
        return answer.get();
    }


    /**
     * Renvoie la question sous forme de StringProperty
     *
     * @return  la question sous forme de StringProperty
     */
    public StringProperty getQuestionProperty(){
        return question;
    }

    /**
     * Renvoie réponse sous forme de StringProperty
     *
     * @return  la réponse sous forme de StringProperty
     */
    public StringProperty getAnswerProperty(){ return answer; }

}