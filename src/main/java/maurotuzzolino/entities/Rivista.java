package maurotuzzolino.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "rivista")
public class Rivista extends ElementoCatalogo {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Periodicita periodicita;

    public Rivista() {
        super();
    }

    public Rivista(Long id, String isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(id, isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }
}
