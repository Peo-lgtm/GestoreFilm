package org.example.service;

import org.example.filter.FiltroFilm;
import org.example.model.Film;
import org.example.repository.FilmRepository;
import org.example.filter.ApplicatoreFiltro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GestoreCollezione {
    private static GestoreCollezione instance;
    private final FilmRepository repository;
    private final List<Film> collezione;
    private final List<CollezioneListener> listeners = new ArrayList<>();

    private GestoreCollezione(FilmRepository repository) throws IOException {
        this.repository = repository;
        this.collezione = new ArrayList<>(repository.caricaTutti());
    }
    public static void init(FilmRepository repository) throws IOException {
        if (instance == null) {
            instance = new GestoreCollezione(repository);
        }
    }

    public static GestoreCollezione getInstance() {
        if (instance == null) {
            throw new IllegalStateException("GestoreCollezione non inizializzato: chiama init() prima");
        }
        return instance;
    }
// operazioni CRUD

    public void aggiungiFilm(Film film) throws IOException {
        if (film == null) {
            throw new IllegalArgumentException("Il film non può essere null");
        }
        collezione.add(film);
        salva();
    }

    public boolean rimuoviFilm(UUID id) throws IOException {
        Film daRimuovere = trovaPerId(id);
        if (daRimuovere == null) {
            return false;
        }
        collezione.remove(daRimuovere);
        salva();
        return true;
    }

    public boolean modificaFilm(UUID id, Film filmAggiornato) throws IOException {
        Film esistente = trovaPerId(id);
        if (esistente == null) {
            return false;
        }
        int index = collezione.indexOf(esistente);
        collezione.set(index, filmAggiornato);
        salva();
        return true;
    }

    public Film trovaPerId(UUID id) {
        for (Film f : collezione) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }

    public List<Film> getTutti() {
        return new ArrayList<>(collezione);
    }

    public void addListener(CollezioneListener listener) {
        listeners.add(listener);
    }

// filtri
    public List<Film> filtra(FiltroFilm filtro) {
        return ApplicatoreFiltro.applica(collezione, filtro);
    }

    // ordinamento

    public List<Film> ordina(Comparator<Film> criterio) {
        return collezione.stream().sorted(criterio).collect(Collectors.toList());
    }

    public static Comparator<Film> perTitolo() {
        return Comparator.comparing(Film::getTitolo, String.CASE_INSENSITIVE_ORDER);
    }

    public static Comparator<Film> perAnno() {
        return Comparator.comparingInt(Film::getAnno);
    }

    public static Comparator<Film> perValutazione() {
        return Comparator.comparingInt(Film::getValutazione).reversed();
    }

    // salvataggio

    private void salva() throws IOException {
        repository.salvaTutti(collezione);
        notificaCambiamento();
    }

    private void notificaCambiamento() {
        for (CollezioneListener l : listeners) {
            l.onCambiamento();
        }
    }
}