# Répartition des Tâches Développeurs

## 1. Première itération

### Liste des tâches
__Histoire 2__ <br/>
- **Classe Main**

- **View** <br/>
Créer un menu qui affiche tous les jeux de cartes. <br/>
Créer un bouton "pour créer un nouveau jeu de cartes". <br/>
Créer un bouton "ouvrir jeu de cartes" <br/>
Créer un bouton "éditer jeu de cartes" <br/>
Créer un bouton " jouer" <br/>
Selon le bouton sur lequel on a appuié, la view change pour afficher le nouveau menu adéquat (menu édition, menu jouer, ...) <br/>
Bouton "retour <br/>

- **Classe jeu de cartes** <br/>
Attributs : nom, catégorie(s) <br/>
Méthode " supprimer une carte" <br/>
Méthode "éditer une carte" <br/>

__Histoire 3__
- **Classe cartes** <br/>
Attributs : question, réponse <br/>
Méthode "Ecrire une question et sa réponse" <br/>
Méthode "Suppression" <br/>
Méthode "Modification" <br/>
Méthode "Stockage" <br/>

- **View** <br/>
Bouton "Ajouter carte" <br/>
Bouton "Supprimer" <br/>
Bouton "Modifier" <br/>
Affichage de la carte <br/>

__Histoire 4__
- **View** <br/>
Bouton "Afficher la réponse" <br/>
Bouton "Passer à la question suivante" <br/>
Créer 5 boutons pour indiquer le degré de connaissance <br/>


### Répartition

**Histoire 2** : 2 binômes
- Classe Main et classe jeu de cartes : Imad et Mohamed
- View : Rhodney et Salah

**Histoire 3** : 2 binômes
- Classe carte : Lina et Luka
- View : Dylane et Eliott

**Histoire 4** : 1 binôme
- Emirhan et Louis


## Deuxième itération

### Liste des tâches
__Finir la première itération__ <br/>
- **Restructurer en MVC et Refactoring** <br/>
Séparer les views et controllers <br/>
Recréer les views <br/>
Recréer les controllers <br/>


- **Restructurer le dépot git** <br/>
Merge les branches finies <br/>
Supprimer les branches inutiles <br/>

- **Réinitialiser le projet avec Maven** 

- **Uniformisation et homogénéisation du code**

__Stockage des paquets en format text__ <br/>

__Histoire 5__ <br/>
- **Faire l'algorithme pour les probabilités d'apparition des cartes**

- **Implémenter l'algorithme**

- **Ajouter la possibilité de voir les cartes triées en fonction de leur niveau de connaissance par l'utilisateur pour chaque paquet de cartes**


__Histoire 9__ <br/>
- **Ajouter la fonctionnalité de statistiques globales** <br/>
Le nombre de jeux de cartes utilisés <br/>
Le nombre de cartes jouées <br/>
Le score moyen global <br/>

- **Ajouter la fonctionnalité de statistiques par paquet de cartes** <br/>
Le nombre de cartes par paquet de cartes <br/>




### Répartition

**Finir la première itération** : 2 binômes
- Louis et Mohamed : <br/>
Restructurer en MVC et refactoring <br/>
Réinitialiser le projet avec Maven <br/>
Restructurer le dépot git <br/>

- Luka et Eliott : <br/>
Uniformisation et homogénéisation du code <br/>

**Stockage des paquets en format text** : 1 binôme
- Luka et Eliott <br/>


**Histoire 5** : 2 binômes
- Emirhan et Salah
- Imad et Rhodney

**Histoire 9** : 1 binôme
- Lina et Dylane


## Troisième itération

### Liste des tâches
__Sauvegarder les scores et les probabilités__
- **Régler les problèmes des sauvegardes** <br/>


__Histoire 17__

- **Implémenter les formules de base LaTeX** <br/>
Recherche et documentation <br/>
Implémenter les formules mathématiques avec $$ <br/>
\frac <br/>
Affichage des matrices <br/>


__Histoire 8__ <br/>

- **Créer des textes à trous et des choix multiples** <br/>

__Documentations__ <br/>
- **La javadoc** <br/>

- **Le .jar** <br/>

- **Planification et motivation des choix de conception** <br/>

- **Actualiser le template du burndown**

- **Répartition des tâches**

### Répartition

**Sauvegarder les scores et les probabilités** : 2 binômes
- Louis et Eliott : <br/>
Probabilité
- Imad et Rhodney : <br/>
Statistiques


**Histoire 17** : 2 binômes
- Emirhan et Lina
- Dylane et Salah

**Documentations** : 1 binôme
- Luka et Mohamed

**Histoire 8** : Au binôme qui se libère


## Quatrième itération
### Liste des tâches

__Histoire 17__

- **Implémenter les formules de base LaTeX** <br/>
- Correction de l'affichage Latex <br/>
- Parsing des balises Latex <br/>


__Histoire 8__ <br/>

- **Créer des textes à trous** <br/>
- Creation de la carte texte à trous et affichage correspondant <br/>
- Test des méthodes utilisées pour le texte à trous <br/>

__Refactoring__<br/>

- **Ajouter la fonctionnalité de statistiques par paquet de cartes** <br/>
- Temps de révision <br/>
- Réinitialisation des statistiques <br/>

- **Affichage** <br/>
- Ajout des boutons retour <br/>
- Mettre tous les champs en français <br/>
- Afficher le nom et la catégorie du jeu de cartes lors d'une partie et lors de l'ouverture du paquet pour modifications
- Affichage d'une fenêtre d'erreur si le champ question et/ou réponse est vide lors de la sauvegarde de la carte <br/>
- Affichage d'une fenêtre d'erreur si un bouton (par exemple "ouvrir") est appuyé sans séléction de paquet de cartes au préalable <br/>
- Suppression de la carte du tableau si bouton "annuler" appuyé après bouton "ajouter" sans sauvegarde de la carte

- **Amelioration du code (mode mvc)** <br/>
- Amelioration des Listener <br/>
- Ajout d'un seuil minimum pour la probabilité d'apparition d'une carte <br/>

- **Ajout des tests** <br/>
- Tester les controllers <br/>
- Ajout des tests manquants <br/>

__Documentations__ <br/>
- **La javadoc** <br/>
- **Le .jar** <br/>
- **Planification et motivation des choix de conception** <br/>
- **Actualiser le template du burndown**
- **Répartition des tâches**


### Répartition

**Refactoring** : 2 binômes (principalement), tout le monde participe dès que sa tâche est finie
- Luka et Rhodney <br/>
- Lina et Salah <br/>


**Histoire 17** : 2 binômes
- Mohamed et Imad
- Louis et Emirhan

**Documentations** : 1 binôme
- Louis et Emirhan

**Histoire 8** : 1 binôme
- Dylane et Eliott


