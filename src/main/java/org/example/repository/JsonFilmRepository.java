package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Film;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JsonFilmRepository implements FilmRepository {

    private final File file;
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonFilmRepository(String percorsoFile) {
        this.file = new File(percorsoFile);
        this.mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
    }

    @Override
    public List<Film> caricaTutti() throws IOException {
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return mapper.readValue(file, new TypeReference<List<Film>>() {});
    }

    @Override
    public void salvaTutti(List<Film> film) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, film);
    }
}