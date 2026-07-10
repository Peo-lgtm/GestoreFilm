package org.example.filter;
import org.example.model.Film;
import org.example.model.StatoVisione;

public class FiltroPerStato implements FiltroFilm {
    private final StatoVisione stato;

    public FiltroPerStato(StatoVisione stato) {
        this.stato = stato;
    }

    @Override
    public boolean filtra(Film film) {
        return film.getStatoVisione() == stato;
    }
}