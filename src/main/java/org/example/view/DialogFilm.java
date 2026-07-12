package org.example.view;

import org.example.model.Film;
import org.example.model.Genere;
import org.example.model.StatoVisione;

import javax.swing.*;
import java.awt.*;

public class DialogFilm  extends JDialog {

    private JTextField campoTitolo;
    private JTextField campoRegista;
    private JTextField campoAnno;
    private JComboBox<Genere> comboGenere;
    private JComboBox<StatoVisione> comboStato;
    private JComboBox<Integer> comboValutazione;
    private Film filmCreato;

    public DialogFilm(JFrame parent){
        super(parent, "Aggiungi Film", true);
        JPanel form = new JPanel(new GridLayout(0,2,5,5));
        campoTitolo = new JTextField();
        campoRegista = new JTextField();
        campoAnno = new JTextField();
        comboGenere = new JComboBox<>(Genere.values());
        comboStato = new JComboBox<>(StatoVisione.values());
        comboValutazione = new JComboBox<>(new Integer[]{1,2,3,4,5});

        form.add(new JLabel("Titolo:"));
        form.add(campoTitolo);
        form.add(new JLabel("Regista:"));
        form.add(campoRegista);
        form.add(new JLabel("Anno:"));
        form.add(campoAnno);
        form.add(new JLabel("Genere:"));
        form.add(comboGenere);
        form.add(new JLabel("Stato visione:"));
        form.add(comboStato);
        form.add(new JLabel("Valutazione:"));
        form.add(comboValutazione);

        JButton bottoneOk = new JButton("OK");
        JButton bottoneAnnulla = new JButton("Annulla");
        bottoneOk.addActionListener(e -> confermaInserimento());
        bottoneAnnulla.addActionListener(e -> {
            filmCreato = null;
            setVisible(false);});

        JPanel bottoni = new JPanel();
        bottoni.add(bottoneOk);
        bottoni.add(bottoneAnnulla);

        setLayout(new BorderLayout());
        add(form, BorderLayout.CENTER);
        add(bottoni, BorderLayout.SOUTH);
        setSize(350,300);
        setLocationRelativeTo(parent);


    }

    private void confermaInserimento() {
        String titolo = campoTitolo.getText().trim();
        String regista = campoTitolo.getText().trim();
        String annoTesto = campoTitolo.getText().trim();

        if(titolo.isEmpty()){
            JOptionPane.showMessageDialog(this,"il titolo è obbligatorio");
            return;

        }

        int anno;
        try{
            anno=Integer.parseInt(annoTesto);
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "l'anno deve essere un numero");
            return;
        }

        Genere genere = (Genere) comboGenere.getSelectedItem();
        StatoVisione stato = (StatoVisione) comboStato.getSelectedItem();
        int valutazione = (Integer) comboValutazione.getSelectedItem();
        filmCreato = new Film(titolo, regista, anno, genere, valutazione, stato);
        setVisible(false);
    }
    public Film mostra(){
        setVisible(true);
        return filmCreato;
    }

}
