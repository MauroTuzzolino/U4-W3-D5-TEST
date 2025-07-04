Lo schema è composto da un totale di 5 tabelle.
elemento_catalogo sarà la superclasse.
Ad estenderla ci saranno 2 ulteriori classi (libro, rivista).
Queste tabelle avranno relazioni 1:1 tra il loro id, e l'id della classe padre.
A parte saranno presenti una tabella utente ed una prestito.
La classe prestito sarà il "fulcro" di tutto il progetto, di fatto avrà una relazione N:1
con l'id della tabella elementi_catalogo (un prestito riguarda un solo elemento del catalogo, al contrario
un elemento può essere prestato più volte), e una N:1 con la tabella utente (anche qui un prestito riguarda
un unico utente, allo stesso tempo un utente può usufruire di più prestiti).

Come strategia di ereditarietà ho deciso di usare JOIN, così da non avere un unica tabella ridondante
come nel caso di SINGLE_TABLE, e allo stesso tempo usufruire delle relazioni tra classi figlie e superclasse
cosa non possibile nel caso di TABLE_PER_CLASS.

N.B.
utente e prestito sono tabelle assestanti: non dispongono di una classse padre!