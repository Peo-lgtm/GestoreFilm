package org.example.filter;
import org.example.model.Film;
import org.example.model.Genere;

public class FiltroPerGenere implements FiltroFilm {
    private final Genere genere;

    public FiltroPerGenere(Genere genere) {
        this.genere = genere;
    }

    @Override
    public boolean filtra(Film film) {
        return film.getGenere() == genere;
    }
}