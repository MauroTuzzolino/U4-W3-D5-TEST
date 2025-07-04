package maurotuzzolino;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import maurotuzzolino.dao.ElementoCatalogoDAO;
import maurotuzzolino.dao.PrestitoDAO;
import maurotuzzolino.dao.UtenteDAO;
import maurotuzzolino.entities.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogobibliografico");
        EntityManager em = emf.createEntityManager();

        ElementoCatalogoDAO elementoDAO = new ElementoCatalogoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);

        Utente utente1 = new Utente("T123", "Mario", "Rossi", LocalDate.of(1980, 5, 15));
        Utente utente2 = new Utente("T456", "Luisa", "Bianchi", LocalDate.of(1992, 8, 24));

        Libro libro1 = new Libro("ISBN001", "Il nome della rosa", 1980, 500, "Umberto Eco", "Storico");
        Libro libro2 = new Libro("ISBN002", "1984", 1949, 328, "George Orwell", "Distopico");

        Rivista rivista1 = new Rivista("ISSN001", "Focus", 2024, 100, Periodicita.MENSILE);
        Rivista rivista2 = new Rivista("ISSN002", "Time", 2024, 90, Periodicita.SETTIMANALE);

        Prestito prestito1 = new Prestito();
        prestito1.setUtente(utente1);
        prestito1.setElemento(libro1);
        prestito1.setDataInizio(LocalDate.now().minusDays(40));
        prestito1.setDataRestituzionePrevista(prestito1.getDataInizio().plusDays(30));
        prestito1.setDataRestituzioneEffettiva(null);  // Non ancora restituito

        Prestito prestito2 = new Prestito();
        prestito2.setUtente(utente2);
        prestito2.setElemento(rivista1);
        prestito2.setDataInizio(LocalDate.now().minusDays(10));
        prestito2.setDataRestituzionePrevista(prestito2.getDataInizio().plusDays(30));
        prestito2.setDataRestituzioneEffettiva(LocalDate.now().minusDays(2));  // Già restituito

        em.getTransaction().begin();

//         Decommentare per testare un metodo per volta:

//        Aggiungi utenti
//        utenteDAO.aggiungiUtente(utente1);
//        utenteDAO.aggiungiUtente(utente2);
//        System.out.println("Utenti aggiunti.");


//        Aggiungi elementi catalogo
//        elementoDAO.aggiungiElemento(libro1);
//        elementoDAO.aggiungiElemento(libro2);
//        elementoDAO.aggiungiElemento(rivista1);
//        elementoDAO.aggiungiElemento(rivista2);
//        System.out.println("Elementi aggiunti.");


//        Aggiungi prestiti
//        prestitoDAO.aggiungiPrestito(prestito1);
//        prestitoDAO.aggiungiPrestito(prestito2);
//        System.out.println("Prestiti aggiunti");

        em.getTransaction().commit();

//        Ricerca per numero tessera utente
//        Utente trovato = utenteDAO.ricercaPerNumeroTessera("T123");
//        System.out.println("Utente trovato: " + (trovato != null ? trovato.getNome() + " " + trovato.getCognome() : "Nessuno"));

//        Ricerca per ISBN
//        ElementoCatalogo el = elementoDAO.ricercaPerISBN("ISBN001");
//        System.out.println("Elemento trovato: " + (el != null ? el.getTitolo() : "Nessuno"));


//        Ricerca per anno pubblicazione
//        List<ElementoCatalogo> listaAnno = elementoDAO.ricercaPerAnno(1980);
//        System.out.println("Elementi pubblicati nel 1980:");
//        listaAnno.forEach(e -> System.out.println(e.getTitolo()));

//        Ricerca per autore
//        List<Libro> libriAutore = elementoDAO.ricercaPerAutore("Umberto Eco");
//        System.out.println("Libri di Umberto Eco:");
//        libriAutore.forEach(l -> System.out.println(l.getTitolo()));

//        Ricerca per titolo parziale
//        List<ElementoCatalogo> perTitolo = elementoDAO.ricercaPerTitoloParziale("time");
//        System.out.println("Elementi con 'time' nel titolo:");
//        perTitolo.forEach(e -> System.out.println(e.getTitolo()));

//        Ricerca prestiti attivi per numero tessera
//        List<Prestito> attivi = prestitoDAO.ricercaPrestitiAttiviPerTessera("T123");
//        System.out.println("Prestiti attivi per tessera T123:");
//        attivi.forEach(p -> System.out.println(p.getElemento().getTitolo()));

//        Ricerca prestiti scaduti e non restituiti
//        List<Prestito> scaduti = prestitoDAO.ricercaPrestitiScaduti(LocalDate.now());
//        System.out.println("Prestiti scaduti non restituiti:");
//        scaduti.forEach(p -> System.out.println(p.getElemento().getTitolo() + " - Tessera: " + p.getUtente().getNumeroTessera()));

        em.close();
        emf.close();
    }
}
