package maurotuzzolino.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import maurotuzzolino.entities.ElementoCatalogo;
import maurotuzzolino.entities.Libro;

import java.util.List;

public class ElementoCatalogoDAO {

    private final EntityManager em;

    public ElementoCatalogoDAO(EntityManager em) {
        this.em = em;
    }

    // Aggiunta di un elemento del catalogo
    public void aggiungiElemento(ElementoCatalogo elemento) {
        if (em.find(ElementoCatalogo.class, elemento.getIsbn()) == null) {
            em.persist(elemento);
        } else {
            em.merge(elemento);
        }
    }

    // Rimozione di un elemento dal catalogo dato un codice ISBN
    public void rimuoviElementoPerISBN(String isbn) {
        ElementoCatalogo elemento = em.find(ElementoCatalogo.class, isbn);
        if (elemento != null) {
            em.remove(elemento);
        }
    }

    // Ricerca per ISBN
    public ElementoCatalogo ricercaPerISBN(String isbn) {
        return em.find(ElementoCatalogo.class, isbn);
    }

    // Ricerca per anno di pubblicazione
    public List<ElementoCatalogo> ricercaPerAnno(int anno) {
        TypedQuery<ElementoCatalogo> query = em.createQuery(
                "SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :anno", ElementoCatalogo.class);
        query.setParameter("anno", anno);
        return query.getResultList();
    }

    // Ricerca per autore
    public List<Libro> ricercaPerAutore(String autore) {
        TypedQuery<Libro> query = em.createQuery(
                "SELECT l FROM Libro l WHERE l.autore = :autore", Libro.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    // Ricerca per titolo o parte di esso
    public List<ElementoCatalogo> ricercaPerTitoloParziale(String titoloParziale) {
        TypedQuery<ElementoCatalogo> query = em.createQuery(
                "SELECT e FROM ElementoCatalogo e WHERE LOWER(e.titolo) LIKE :titolo", ElementoCatalogo.class);
        query.setParameter("titolo", "%" + titoloParziale.toLowerCase() + "%");
        return query.getResultList();
    }
}
