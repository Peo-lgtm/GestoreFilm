package org.example.filter;
import org.example.model.Film;

public class FiltroPerRegista implements FiltroFilm {
    private final String testo;

    public FiltroPerRegista(String testo) {
        this.testo = testo.toLowerCase();
    }

    @Override
    public boolean filtra(Film film) {
        return film.getRegista().toLowerCase().contains(testo);
    }
}