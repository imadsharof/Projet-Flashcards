# Histoires actualisées

## Histoires traitées

### Histoire 2 : Gestion des paquets de cartes

**Description** : L’utilisateur peut créer un jeu de cartes, 
lui donner un nom et une catégorie (par exemple, le nom d’un cours pour lequel le paquet aide à la révision
ou le type de questions qu’on trouve dans le paquet). Une fois le paquet de cartes enregistré,
il apparaît dans un menu permettant de parcourir les jeux de cartes. 
L’utilisateur peut également supprimer un jeu de cartes ou le sélectionner pour l’éditer. <br />

**Priorité Client** : 1 <br />
**Estimation en points** : 10 <br />
**Risque Développeurs** : 3 <br />
**Introduit dans l’iteration** : 1 <br />
**État** : terminé <br />


### Histoire 3 : Création de cartes dans un paquet

**Description** : Lorsque l’utilisateur édite un paquet de cartes, 
il peut y ajouter autant de cartes qu’il le souhaite. 
Chaque carte est composée de deux faces : un recto (contenant une question) et un verso (avec la réponse).
Il peut également supprimer une carte ou en modifier une existante. <br />

**Priorité Client** : 1 <br />
**Estimation en points** : 10 <br />
**Risque Développeurs** : 3 <br />
**Introduit dans l’iteration** : 1 <br />
**État** : terminé <br />


### Histoire 4 : Étudier un jeu de cartes

**Description** : L’utilisateur peut étudier un jeu de cartes en le sélectionnant dans le menu.
Le programme lui présente chaque carte du paquet dans un ordre aléatoire, côté question.
Quand l’utilisateur le demande, la réponse est affichée.
Il indique alors au programme s’il connaissait ou non la réponse grâce à des boutons.
Plusieurs boutons sont disponibles pour indiquer son degré de connaissance : Très bonne, bonne, moyenne,
mauvaise ou très mauvaise connaissance.

**Priorité Client** : 1 <br />
**Estimation en points** : 8 <br />
**Risque Développeurs** : 2 <br />
**Introduit dans l’iteration** : 1 <br />
**État** : terminé <br />


### Histoire supplémentaire 1 : Stockage du contenu des decks

**Description** : Les paquets de cartes créés par l'utilisateur sont enregistrés en local dans des fichiers distincts.
L'utilisateur doit pouvoir modifier directement ces fichiers, sans passer par l'application.

**Priorité Client** : 1 <br />
**Estimation en points** : 3 <br />
**Risque Développeurs** : 3 <br />
**Introduit dans l’iteration** : 2 <br />
**État** : terminé <br />


### Histoire 5 : Méthode de tirage

**Description** : Quand l’utilisateur étudie un paquet de cartes, 
l’application lui présente les cartes de sorte que celles qui sont le moins bien connues apparaissent plus souvent.

**Priorité Client** : 2 <br />
**Estimation en points** : 5 <br />
**Risque Développeurs** : 3 <br />
**Introduit dans l’iteration** : 2 <br />
**État** : terminé <br />


### Histoire 9 : Statistiques d'entrainement

**Description** : Le programme offre deux niveaux de statistiques à l’utilisateur.
D’abord, des statistiques globales comprennent le nombre de jeux de cartes utilisés,
une liste des jeux de cartes les plus utilisés (et - moins important - le temps d’entraînement total et par jour).
Il donne également accès à des statistiques par paquet de cartes.
Celles-ci reprennent le nombre de sessions d’études réalisées sur ce jeu et un indice de maîtrise du paquet.
Il permet également de voir l’évolution de ces statistiques au fil des jours sous la forme d’un diagramme d’évolution.

**Priorité Client** : 2 <br />
**Estimation en points** : 20 <br />
**Risque Développeurs** : 1 <br />
**Introduit dans l’iteration** : 2 <br />
**État** : modifié (terminé sauf diagramme d'évolution annulé) <br />


### Histoire supplémentaire 2 : Sauvegarder les scores et les probabilités

**Description** : Les statistiques globales et par deck doivent être sauvegardées d'une utilisation
à l'autre de l'application.

**Priorité Client** : 1 <br />
**Estimation en points** : 9 <br />
**Risque Développeurs** : 2 <br />
**Introduit dans l’iteration** : 3 <br />
**État** : terminé <br />


### Histoire 17 : Latex dans les cartes
**Description** : L’utilisateur peut écrire des cartes contenant du code LATEX qui est correctement interprété lors de la visualisation.
Des formules mathématiques simples ainsi que des matrices doivent être encodables.

**Priorité Client** : 1 <br />
**Estimation en points** : 19 <br />
**Risque Développeurs** : 2 <br />
**Introduit dans l’iteration** : 3 <br />
**État** : terminé <br />


## Histoire 8 : Réponses de différents types

**Description** : Les réponses à une question peuvent être de différentes natures : <br />
— Texte à trous à compléter <br />
— Choix multiple <br />
— Réponse ouverte <br />

**Priorité Client** : 2 <br />
**Estimation en points** : 6 <br />
**Risque Développeurs** : 2 <br />
**Introduit dans l’iteration** : 3 <br />
**État** : terminé (choix multiple et réponse ouverte annulés) <br />
