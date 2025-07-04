package maurotuzzolino.dao;

import jakarta.persistence.EntityManager;
import maurotuzzolino.entities.Utente;

public class UtenteDAO {

    private final EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    // Aggiunta di un utente
    public void aggiungiUtente(Utente utente) {
        if (em.find(Utente.class, utente.getNumeroTessera()) == null) {
            em.persist(utente);
        } else {
            em.merge(utente);
        }
    }

    // Ricerca utente tramite numero di tessera
    public Utente ricercaPerNumeroTessera(String numeroTessera) {
        return em.find(Utente.class, numeroTessera);
    }
}
