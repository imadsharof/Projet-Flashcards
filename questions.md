# Question 1
Controller.StatisticsControllerTest#titletest : ce test ne passe pas.  Pourquoi d'après vous ? Quelle méthodologie avez-vous utilisé pour écrire vos tests ? 

# Question 2
Comment fonctionnent les mocks dans les tests (comme dans Controller.DeckEditorControllerTest par exemple) ?

# Question 3
Discutez de la séparation Vue/Contrôleur dans la méthode ulb.infof307.g02.views.CardEditorViewController#onSaveButtonClick. Généralisez cette discussion à toute votre architecture. 

# Question 4
Discutez de l'emplacement de la méthode ulb.infof307.g02.models.Deck#readDeck dans la classe Deck.

# Question 5
Discutez de la gestion des exceptions dans la méthode ulb.infof307.g02.models.DeckTable#loadDecks. Vous définissez également WeightException qui n'est utilisée qu'une seule fois (dans Deck.java). Quel(s) sont les avantage(s) par rapport l'utilisation d'exceptions prééxistantes ?

# Question 6
Les constantes qui permettent de modifier le poids des cartes se trouvent dans le controlleur et pas dans le modèle. Qu'est-ce qui a motivé ce choix ?

# Question 7
Vous testez la méthode updateWeights. Comment vous assurer que vous testez tous les cas. Par exemple, est-ce que la condition liée aux bornes contraignant le poids des cartes est toujours vérifiée ? Si non, est-ce un problème ?

# Question 8
Quels design patterns avez-vous utilisé et pouquoi ?

# Question 9
ulb.infof307.g02.controllers.MainController est une grande classe. Comment réduire son code ?

# Question 10
Comment pourrait-on faire mieux que l'utilisation des strings utilisés dans ulb.infof307.g02.views.CardEditorViewController#onSaveButtonClick ?