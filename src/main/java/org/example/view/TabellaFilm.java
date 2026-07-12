package org.example.view;
import org.example.model.Film;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TabellaFilm extends AbstractTableModel {

    private final String[] colonne = {"Titolo", "Regista", "Anno", "Genere", "Valutazione", "Stato"};
    private List<Film> film;

    public TabellaFilm(List<Film> film) {
        this.film = film;
    }

    public void aggiorna(List<Film> nuovaLista) {
        this.film = nuovaLista;
        fireTableDataChanged();
    }

    public Film getFilmAlla(int riga) {
        return film.get(riga);
    }

    @Override
    public int getRowCount() {
        return film.size();
    }

    @Override
    public int getColumnCount() {
        return colonne.length;
    }

    @Override
    public String getColumnName(int col) {
        return colonne[col];
    }

    @Override
    public Object getValueAt(int riga, int colonna) {
        Film f = film.get(riga);
        switch (colonna) {
            case 0: return f.getTitolo();
            case 1: return f.getRegista();
            case 2: return f.getAnno();
            case 3: return f.getGenere();
            case 4: return f.getValutazione();
            case 5: return f.getStatoVisione();
            default: return "";
        }
    }
}