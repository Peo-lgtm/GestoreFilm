package org.example.repository;

import org.example.model.Film;
import java.io.IOException;
import java.util.List;


public interface FilmRepository {

    List<Film> caricaTutti() throws IOException;

    void salvaTutti(List<Film> film) throws IOException;
}