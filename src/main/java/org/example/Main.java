package org.example;

import org.example.repository.FilmRepository;
import org.example.repository.JsonFilmRepository;
import org.example.service.GestoreCollezione;
import org.example.view.MainUI;
import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FilmRepository repository = new JsonFilmRepository("film.json");
            GestoreCollezione.init(repository);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Errore nel caricamento dei dati: " + e.getMessage());
            return;
        }

        SwingUtilities.invokeLater(() -> {
            MainUI finestra = new MainUI();
            finestra.setVisible(true);
        });
    }
}