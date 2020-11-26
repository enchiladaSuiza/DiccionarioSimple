package paquete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Busqueda extends JPanel implements ActionListener {
    JTextField textField;
    JLabel labelPalabra;
    JTextArea labelDefiniciones;
    JTextArea labelEjemplos;

    Busqueda() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(180, 24));
        textField.addActionListener(this);

        labelPalabra = new JLabel();

        labelDefiniciones = new JTextArea();
        labelDefiniciones.setEditable(false);

        labelEjemplos = new JTextArea();
        labelEjemplos.setEditable(false);

        this.add(textField);
        this.add(labelPalabra);
        this.add(labelDefiniciones);
        this.add(labelEjemplos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == textField) {
            String texto = textField.getText();
            Palabra palabra = new Palabra(texto);
            mostrarDetalles(palabra);
        }
    }

    private void mostrarDetalles(Palabra palabra) {
        String palabraTexto = palabra.getPalabra() + " (" + palabra.getLexico() + ")";
        labelPalabra.setText(palabraTexto);

        String definicionesTexto = "";
        for (int i = 0; i < palabra.getDefiniciones().length; i++) {
            String def = palabra.getDefiniciones()[i];
            definicionesTexto += (i + 1) + ". " + def;
            if (i < palabra.getDefiniciones().length - 1) {
                definicionesTexto += "\n";
            }
        }
        labelDefiniciones.setText(definicionesTexto);

        String ejemploTexto = "";
        for (int i = 0; i < palabra.getEjemplos().length; i++) {
            String eg = palabra.getEjemplos()[i];
            ejemploTexto += eg;
            if (i < palabra.getEjemplos().length - 1) {
                ejemploTexto += "\n";
            }
        }
        labelEjemplos.setText(ejemploTexto);
        revalidate();
        repaint();
    }
}
