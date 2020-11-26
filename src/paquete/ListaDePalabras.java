package paquete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListaDePalabras extends JScrollPane implements ActionListener {

    private ArrayList<String> palabras;
    private ArrayList<JButton> botones;
    private JPanel grid;

    ListaDePalabras() {
        super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setPreferredSize(new Dimension(600, 400));
        grid = new JPanel(new GridLayout(0, 5));
        palabras = Diccionario.conseguirTodasLasPalabras();
        botones = new ArrayList<>();

        for (String palabra : palabras) {
            JButton boton = new JButton(palabra);
            boton.setBackground(null);
            boton.addActionListener(this);
            botones.add(boton);
        }

        for (JButton boton : botones) {
            grid.add(boton);
        }
        this.setViewportView(grid);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton presionado = (JButton)e.getSource();
        String texto = presionado.getText();
        Palabra palabra = new Palabra(texto);
        Main.getGUI().buscarPalabra(palabra);
    }
}
