package org.example.filter;
import org.example.model.Film;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicatoreFiltro {
    public static List<Film> applica(List<Film> film, FiltroFilm filtro) {
        return film.stream()
                .filter(filtro::filtra)
                .collect(Collectors.toList());
    }
}