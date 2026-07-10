package org.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class Film {

    private final UUID id;
    private String titolo;
    private String regista;
    private int anno;
    private Genere genere;
    private int valutazione; // da 1 a 5
    private StatoVisione statoVisione;


    public Film(String titolo, String regista, int anno, Genere genere, int valutazione, StatoVisione statoVisione) {
        this(UUID.randomUUID(), titolo, regista, anno, genere, valutazione, statoVisione);
    }


    @JsonCreator
    public Film(@JsonProperty("id") UUID id,
                @JsonProperty("titolo") String titolo,
                @JsonProperty("regista") String regista,
                @JsonProperty("anno") int anno,
                @JsonProperty("genere") Genere genere,
                @JsonProperty("valutazione") int valutazione,
                @JsonProperty("statoVisione") StatoVisione statoVisione) {
        if (titolo == null || titolo.isBlank()) {
            throw new IllegalArgumentException("Il titolo non può essere vuoto");
        }
        if (valutazione < 1 || valutazione > 5) {
            throw new IllegalArgumentException("La valutazione deve essere tra 1 e 5");
        }
        this.id = id != null ? id : UUID.randomUUID();
        this.titolo = titolo;
        this.regista = regista;
        this.anno = anno;
        this.genere = genere;
        this.valutazione = valutazione;
        this.statoVisione = statoVisione;
    }

    // ---------- Getter ----------

    public UUID getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getRegista() {
        return regista;
    }

    public int getAnno() {
        return anno;
    }

    public Genere getGenere() {
        return genere;
    }

    public int getValutazione() {
        return valutazione;
    }

    public StatoVisione getStatoVisione() {
        return statoVisione;
    }

    // ---------- Setter (per la modifica dei film esistenti) ----------

    public void setTitolo(String titolo) {
        if (titolo == null || titolo.isBlank()) {
            throw new IllegalArgumentException("Il titolo non può essere vuoto");
        }
        this.titolo = titolo;
    }

    public void setRegista(String regista) {
        this.regista = regista;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    public void setValutazione(int valutazione) {
        if (valutazione < 1 || valutazione > 5) {
            throw new IllegalArgumentException("La valutazione deve essere tra 1 e 5");
        }
        this.valutazione = valutazione;
    }

    public void setStatoVisione(StatoVisione statoVisione) {
        this.statoVisione = statoVisione;
    }

    // ---------- equals / hashCode basati sull'id ----------

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Film film = (Film) obj;
        return id.equals(film.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                "titolo='" + titolo + '\'' +
                ", regista='" + regista + '\'' +
                ", anno=" + anno +
                ", genere=" + genere +
                ", valutazione=" + valutazione +
                ", statoVisione=" + statoVisione +
                '}';
    }
}