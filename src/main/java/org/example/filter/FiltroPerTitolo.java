package org.example.filter;

import org.example.model.Film;

public class FiltroPerTitolo implements FiltroFilm {
    private final String testo;

    public FiltroPerTitolo(String testo) {
        this.testo = testo.toLowerCase();
    }

    @Override
    public boolean filtra(Film film) {
        return film.getTitolo().toLowerCase().contains(testo);
    }
}