package maurotuzzolino.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import maurotuzzolino.entities.Prestito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PrestitoDAO {

    private final EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    // Aggiunta di un nuovo prestito
    public void aggiungiPrestito(Prestito prestito) {
        if (prestito.getId() == null) {
            em.persist(prestito);
        } else {
            em.merge(prestito);
        }
    }

    // Ricerca per Id
    public Optional<Prestito> ricercaPerId(Long id) {
        return Optional.ofNullable(em.find(Prestito.class, id));
    }

    // Ricerca degli elementi attualmente in prestito dato un numero di tessera utente
    public List<Prestito> ricercaPrestitiAttiviPerTessera(String numeroTessera) {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :tessera AND p.dataRestituzioneEffettiva IS NULL",
                Prestito.class);
        query.setParameter("tessera", numeroTessera);
        return query.getResultList();
    }

    // Ricerca di tutti i prestiti scaduti e non ancora restituiti
    public List<Prestito> ricercaPrestitiScaduti(LocalDate oggi) {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < :oggi AND p.dataRestituzioneEffettiva IS NULL",
                Prestito.class);
        query.setParameter("oggi", oggi);
        return query.getResultList();
    }
}
