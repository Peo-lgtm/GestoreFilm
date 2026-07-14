package org.example.view;

import org.example.model.Film;
import org.example.service.GestoreCollezione;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainUI extends JFrame{
    private final GestoreCollezione gestore = GestoreCollezione.getInstance();
    private final TabellaFilm modelloTabella;
    private final JTable tabella;
    private final PannelloRicerca pannelloRicerca;
    public MainUI(){
        super("Gestore Film");
        modelloTabella = new TabellaFilm(gestore.getTutti());
        tabella = new JTable((modelloTabella));
        JButton bottoneAggiungi = new JButton("Aggiungi Film");
        bottoneAggiungi.addActionListener(e -> apriDialogAggiunta());
        JButton bottoneRimuovi = new JButton("Rimuovi");
        bottoneRimuovi.addActionListener(e -> rimuoviSelezionato());
        JPanel toolbar = new JPanel();
        toolbar.add(bottoneAggiungi);
        toolbar.add(bottoneRimuovi);
        pannelloRicerca = new PannelloRicerca(modelloTabella);

        JPanel pannelloSuperiore = new JPanel(new BorderLayout());
        pannelloSuperiore.add(toolbar, BorderLayout.NORTH);
        pannelloSuperiore.add(pannelloRicerca, BorderLayout.SOUTH);
        setLayout(new BorderLayout());
        add(pannelloSuperiore, BorderLayout.NORTH);
        add(new JScrollPane(tabella), BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,500);
        setLocationRelativeTo(null);
        
    }

    private void apriDialogAggiunta() {
        DialogFilm dialog = new DialogFilm(this);
        Film nuovo = dialog.mostra();
        if(nuovo != null){
            try{
                gestore.aggiungiFilm((nuovo));
                modelloTabella.aggiorna(gestore.getTutti());
            }catch(IOException ex){
                JOptionPane.showMessageDialog(this, "Errore nel salvataggio:"+ ex);
            }
        }
    }

    private void rimuoviSelezionato() {
        int riga = tabella.getSelectedRow();
        if(riga == -1){
            JOptionPane.showMessageDialog(this, "Seleziona prima un film dalla tabella");
            return;
        }
        Film selezionato = modelloTabella.getFilmAlla(riga);
        try{
            gestore.rimuoviFilm(selezionato.getId());
            modelloTabella.aggiorna(gestore.getTutti());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Errore:"+ ex.getMessage());
        }

    }
}
