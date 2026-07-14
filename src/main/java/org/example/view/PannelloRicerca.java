package org.example.view;

import org.example.filter.*;
import org.example.service.GestoreCollezione;
import org.example.model.Genere;
import org.example.model.StatoVisione;
import javax.swing.*;

public class PannelloRicerca extends JPanel {
    private final GestoreCollezione gestore = GestoreCollezione.getInstance();
    private final TabellaFilm modelloTabella;
    private JTextField campoTesto;
    private JComboBox<String> comboTipoRicerca;
    private JComboBox<Genere> comboGenere;
    private JComboBox<StatoVisione> comboStato;
    private JComboBox<String> comboOrdinamento;

    public PannelloRicerca(TabellaFilm modelloTabella) {
        this.modelloTabella = modelloTabella;
        campoTesto = new JTextField(15);
        comboTipoRicerca = new JComboBox<>(new String[]{"Titolo", "Regista"});
        comboGenere = new JComboBox<>(Genere.values());
        comboGenere.insertItemAt(null, 0);
        comboGenere.setSelectedIndex(0);
        comboStato = new JComboBox<>(StatoVisione.values());
        comboStato.insertItemAt(null, 0);
        comboStato.setSelectedIndex(0);
        comboOrdinamento = new JComboBox<>(new String[]{"Nessuno", "Titolo", "Anno", "Valutazione"});

        JButton bottoneCerca = new JButton("Cerca");
        bottoneCerca.addActionListener(e -> eseguiRicerca());
        JButton bottoneReset = new JButton("Cerca");
        bottoneReset.addActionListener(e -> resetFiltri());
        add(new JLabel("Cerca per:"));
        add(comboTipoRicerca);
        add(campoTesto);
        add(new JLabel("Genere:"));
        add(comboGenere);
        add(new JLabel("Stato:"));
        add(comboStato);
        add(new JLabel("Ordina per:"));
        add(comboOrdinamento);
        add(bottoneCerca);
        add(bottoneReset);
    }


    private FiltroFilm costruisciFiltro() {
        String testo = campoTesto.getText().trim();
        Genere genere = (Genere) comboGenere.getSelectedItem();
        StatoVisione stato = (StatoVisione) comboStato.getSelectedItem();
        FiltroFilm filtro = null;

        if (!testo.isEmpty()) {
            String tipo = (String) comboTipoRicerca.getSelectedItem();
            filtro = "Titolo".equals(tipo) ? new FiltroPerTitolo(testo) : new FiltroPerRegista(testo);
        }
        if (genere != null) {
            filtro = combinaFiltri(filtro, new FiltroPerGenere(genere));
        }
        if (stato != null) {
            filtro = combinaFiltri(filtro, new FiltroPerStato(stato));
        }
        return filtro;

    }

    private FiltroFilm combinaFiltri(FiltroFilm esistente, FiltroFilm nuovo) {
        if (esistente == null) {
            return nuovo;
        }
        FiltroFilm precedente = esistente;
        return film -> precedente.filtra(film) && nuovo.filtra(film);
    }


    private void resetFiltri() {
        campoTesto.setText("");
        comboGenere.setSelectedIndex(0);
        comboStato.setSelectedIndex(0);
        modelloTabella.aggiorna(gestore.getTutti());

    }

    private void eseguiRicerca() {
        FiltroFilm filtro = costruisciFiltro();
        var risultato = filtro != null ? gestore.filtra(filtro) : gestore.getTutti();
        String ordinamento = (String) comboOrdinamento.getSelectedItem();
        if (!"Nessuno".equals(ordinamento)) {
            var comparator = switch (ordinamento) {
                case "Titolo" -> GestoreCollezione.perTitolo();
                case "Anno" -> GestoreCollezione.perAnno();
                case "Valutazione" -> GestoreCollezione.perValutazione();
                default -> null;
            };
            if (comparator != null) {
                risultato.sort(comparator);
            }
        }
        modelloTabella.aggiorna(risultato);
    }
}
