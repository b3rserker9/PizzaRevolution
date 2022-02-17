# PizzaRevolution
 
Contesto di riferimento e obiettivi
Contesto
Si vuole realizzare un sistema informativo su Web per l’ordinazione di pizza da parte di una piccola pizzeria
 • Oltre agli utenti occasionali, interagiscono con il sistema due tipologie di attori: i clienti e l'amministrazione 
• Un utente occasionale può svolgere le seguenti operazioni:
– Registrazione al sito
– Consultazione menù pizzeria
– Contattare attraverso l’apposita sezione “contattaci”
• Un cliente può svolgere le seguenti operazioni:
– Consultazione menù pizzeria
– Consultazione cronologia ordini effettuati presso la pizzeria 
– Fare un nuovo ordine alla pizzeria
• L'amministrazione può svolgere le seguenti operazioni:
 – Inserimento di una nuova pizza
 – Inserimento di nuovo fattorino
– Aggiornamento dati fattorino 
– Visualizzare la cronologia di tutti gli ordini
–Visualizzare dettagli ordine
•Per ogni pizza sono di interesse un nome, un costo, una lista di ingredienti
• Per ogni fattorino sono di interesse un nome , un veicolo e un numero di telefono.
• Per ogni ordine è necessario memorizzare, oltre al cliente, l’ora della prenotazione tra quelle disponibili, le varie pizze scelte e il fattorino che si occuperà della consegna


-Obiettivi
L’obiettivo è creare un sistema informativo su Web che contempli i seguenti casi d’uso che seguono
Casi D’Uso
Caso d'uso UC1: consulta offerta: 
Attore primario: utente non registrato.
Un qualunque accesso di un utente non registrato, può accedere alle pagine del menù per visualizzare tutti i tipi di pizza con costo e ingredienti offerti dalla pizzeria.
Scenario principale di successo:
Il sistema mostra i dettagli della richiesta visualizzando la pagina. L'utente non registrato può ripetere i passi precedenti un numero indefinito di volte. La stessa cosa è disponibile anche per gli utenti registrati con le stesse modalità.
Caso d'uso UC2: crea pizza e ingrediente.
Attore primario: amministrazione. 
Scenario principale di successo: dopo aver eseguito l’accesso al sistema con le proprie credenziali che verranno riconosciute dal sistema come utenza di amministrazione. L’amministratore crea una pizza cliccando sul tasto aggiungi nella pagina di menù, successivamente compila i campi richiesti quali nome, costo, ingredienti ( tra quelli disponibili nell’elenco registrato nel DB)
Inoltre sulla stessa pagine l’amministratore potrà aggiungere un ingrediente cliccando sul tasto “Aggiungi ingrediente” e scegliendo un nome. Dopo aver controllato che l’ingrediente non esista già, il sistema registrerà tutto nel DB 
Caso d'uso UC3: nuovo ordine
Attore primario: cliente. 
Scenario principale di successo:
dopo aver eseguito l’accesso al sistema con le proprie credenziali che verranno riconosciute dal sistema come utenza di cliente. Il cliente dopo aver cliccato su ordina può selezione tra le varie pizze disponibili, scrivere un messaggio e può scegliere l’orario di consegna tra i vari orari disponibili (che verranno visualizzati in base alla disponibilità dei fattorini)
Caso d'uso UC4: inserimento e modifica fattorino
Attore primario: amministrazione. 
Si presuppone che l’utente principale sia quello registrato con appositi permessi di “amministrazione”.
Scenario principale di successo:
L'amministratore inserisce un nuovo fattorino cliccando sul tasto “Aggiungi” nella pagina “fattorini” indicando nell’apposita form nome, numero di telefono e veicolo con cui effettuerà le consegne. Il sistema registra i dati nel DB solo se l’operazione va a buon fine.
Nella stessa pagina l’amministratore può modificare i dati del fattorino cliccando sul tasto “modifica” e modificando i dati direttamente nella schermata dove li stava visualizzando, successivamente cliccando su “salva” il sistema aggiornerà i dati nel DB.
Caso d'uso UC5: cronologia ordini.
Attore primario: amministrazione.
 Si presuppone che l’utente principale sia quello registrato con appositi permessi di “amministrazione”.
Scenario principale di successo: L’amministratore visualizza tutti gli ordini effettuati selezionando l’apposita pagina “Storico ordini”. Il sistema stampa tutti i dati relativi agli ordini (nome cliente, nome fattorino, orario di consegna e il costo complessivo dell’ordine)
Caso d'uso UC6: visualizzazioni ordini cliente. 
Attore primario: cliente. 
Scenario principale di successo: dopo aver eseguito l’accesso al sistema con le proprie credenziali che verranno riconosciute dal sistema come utenza di cliente. Il cliente dopo aver cliccato su “Cronologia “ visualizzerà i vari ordini passati che ha fatto.
Caso d'uso UC7: contatti.
 Attore primario: utente generico
Scenario principale di successo: L'utente seleziona il campo contatti. Il sistema mostra la form L'utente inserisce il proprio nome, email e richieste. Il sistema inoltra tutto ad un indirizzo email della pizzeria.
