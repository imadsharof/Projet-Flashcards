# Planification et Choix

## Suivi de la planification du projet

### Itération 1

Les histoires 2, 3 et 4 ont été implémentées. Cependant, elles l'ont été séparément, sans rassemblement avant la fin de l'itération.
Ainsi, seules certaines fonctionnalités fonctionnaient correctement : l'ouverture du menu des decks et le player pour étudier un deck.
Le menu de création des cartes s'ouvraient, mais aucune carte ne pouvait être créée ou ajoutée. <br />

Le manque de concertations et de communication dans l'équipe ont conduit à des choix différents : 
- Le menu principal a été implémenté avec javaFX avec une structure MVC
- Le player de decks a été implémenté avec javaFX mais sans structure MVC
- L'éditeur de cartes a été implémenté avec Swing

Des problèmes de librairies ont aussi été rencontrés. Afin de les contourner, les librairies requises ont été téléchargées séparément.
Maven n'a pas été utilisé lors de cette itération. <br />

L'enregistrement en mémoire physique des decks a été commencé, mais non finalisé.


### Itération 2

La priorité lors de cette itération a été de faire fonctionner correctement ensembles les trois parties (les 3 histoires) de l'itération 1.
Ainsi, un important refactoring a été effectué, avec l'utilisation de Maven pour les librairies
et l'uniformisation du code sous le paradigme du Model-View-Controller (MVC) et l'utilisation de javaFX. <br />

L'enregistrement en mémoire physique des decks a, lui aussi, été finalisé.

En outre, les histoires 5 et 9 ont été implémentées. Cependant, les statistiques demandées dans l'histoire 9 ne sont pas conservées
en mémoire physique. Ainsi, elles sont réinitialisées à chaque nouveau lancement de l'application. 
Le diagramme d'évolution des statistiques de l'histoire 9 n'a pas été réalisé, de même que le temps d'entrainement.


### Itération 3

Les statistiques fournies actuelles convenant au client, le diagramme d'évolution ainsi que le temps d'entrainement n'ont pas été implémentés.
En revanche, les statistiques liées aux decks sont enregistrées, afin de perdurer d'un lancement de l'application à l'autre. <br />

L'histoire 17, concernant l'utilisation de Latex dans les cartes, a été implémentée. <br />
Cependant, par facilité et manque de temps, cela a été fait avec la librairie Swing. De plus, dû à des soucis de compatibilité entre Swing
et javaFX, des fenêtres supplémentaires ont été nécessaires pour la visualisation. Ce n'est donc pas une solution idéale.
Cette partie devra probablement encore être adaptée, selon les souhaits du client. <br />

L'histoire 8, concernant les différents types de cartes, n'est en revanche pas implémentée. Il avait été convenu avec le client 
qu'elle ne serait réalisée que dans le cas où il nous restait du temps après la fin des tâches précédentes, celle-ci étant moins prioritaire.

### Itération 4

Pour introduire l'histoire 8, plusieurs boutons ont été ajoutés lors de la création des cartes pour définir le type de carte qui allait être enregistré.
Nous disposons maintenant d'un type de carte supplémentaire, textes à trous. Un test a été ajouté concernant ce type de carte.
Si le nombre de trous ne correspond pas au nombre de réponses, un nouveau message d'erreur s'affiche.<br />

L'histoire 17, considéré comme peu pratique par le client, la fenêtre supplémentaire pour l'affichage du latex a été enlenvé. Maintenant le latex est directement incorporé à la fenêtre principale.

Concernant les probabilités d'apparition des cartes, un minimum et un maximum ont été imposé pour ne pas créer des probabilités infimes ou beaucoup trop grandes.
Aussi, les probabilités sont dorénavant recalculées à même lorsque le client ajoute ou enlève une carte d'un deck.<br />

Lors de cette itération beaucoup de refactoring a été mis à l'oeuvre pour que le code soit le plus optimal possible. Quelques petits ajouts ont été aussi mis comme l'appartition d'un nouveau bouton, le bouton "retour".
Il sert simplement à revenir en arrière. Quelques exceptions ont été aussi corrigées.

## Choix d'implémentations

### Enregistrements en mémoire physique

Le principal choix d'implémentation que nous avons rencontré a été la manière dont toutes les données devaient être enregistrées
dans la mémoire physique. Ce choix est resté en suspend lors de l'itération 1. Lors de la réunion avec le client clôturant l'itération 1
et démarrant l'itération 2, la question a été posée au client. Celui-ci a émis le désir de pouvoir modifier directement les fichiers de
sauvegarde et a refusé l'utilisation d'une base de donnée. Ainsi, dans le souci de simplicité et de satisfaire la demande du client,
le choix s'est finalement porté sur de simples fichiers au format txt, conservés dans un dossier spécifique (le dossier DecksData). 
Chaque deck est enregistré dans un fichier séparé, avec toutes les données qui lui sont nécessaires.
Un format simple est utilisé pour séparer les questions, les réponses et les statistiques. Ainsi, en un coup d'œil,
l'utilisateur peut comprendre l'organisation du fichier, modifier ce dernier, ou en créer un nouveau, sans besoin de passer par l'application.
