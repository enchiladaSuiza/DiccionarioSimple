package paquete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Busqueda extends JPanel implements ActionListener {
    private JTextField textField;
    private JLabel labelPalabra;
    private JLabel labelLexico;
    private JTextArea labelDefiniciones;
    private JTextArea labelEjemplos;
    private Font fuentePalabra = new Font("Arial", Font.BOLD, 14);
    private Font fuenteDefinicion = new Font("Arial", Font.PLAIN, 14);
    private Font fuenteEjemplo = new Font("Arial", Font.ITALIC, 14);

    Busqueda() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        this.setPreferredSize(new Dimension(600, 400));

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(160, 24));
        textField.addActionListener(this);

        labelPalabra = new JLabel();
        labelPalabra.setHorizontalAlignment(JLabel.LEFT);
        labelPalabra.setVerticalAlignment(JLabel.CENTER);
        labelPalabra.setPreferredSize(new Dimension(500, 20));
        labelPalabra.setFont(fuentePalabra);

        labelLexico = new JLabel();
        labelLexico.setHorizontalAlignment(JLabel.LEFT);
        labelLexico.setVerticalAlignment(JLabel.CENTER);
        labelLexico.setPreferredSize(new Dimension(500, 20));
        labelLexico.setFont(fuenteEjemplo);

        labelDefiniciones = new JTextArea();
        labelDefiniciones.setEditable(false);
        labelDefiniciones.setBackground(null);
        labelDefiniciones.setLineWrap(true);
        labelDefiniciones.setWrapStyleWord(true);
        labelDefiniciones.setPreferredSize(new Dimension(500, 100));
        labelDefiniciones.setFont(fuenteDefinicion);

        labelEjemplos = new JTextArea();
        labelEjemplos.setEditable(false);
        labelEjemplos.setBackground(null);
        labelEjemplos.setLineWrap(true);
        labelEjemplos.setWrapStyleWord(true);
        labelEjemplos.setPreferredSize(new Dimension(500, 100));
        labelEjemplos.setFont(new Font("Arial", Font.ITALIC, 14));
        labelEjemplos.setFont(fuenteEjemplo);

        this.add(textField);
        this.add(labelPalabra);
        this.add(labelLexico);
        this.add(labelDefiniciones);
        this.add(labelEjemplos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == textField) {
            String texto = textField.getText();
            texto = texto.toLowerCase();
            Palabra palabra = new Palabra(texto);
            if (palabra.estaEnLaBase()) {
                mostrarDetalles(palabra);
            }
            else {
                mostrarError(texto);
            }
        }
    }

    private void mostrarDetalles(Palabra palabra) {
        labelPalabra.setText(palabra.getPalabra());
        labelLexico.setText(palabra.getLexico());

        String definicionesTexto = "";
        for (int i = 0; i < palabra.getDefiniciones().length; i++) {
            String def = palabra.getDefiniciones()[i];
            definicionesTexto += (i + 1) + ". " + def;
            if (i < palabra.getDefiniciones().length - 1) {
                definicionesTexto += "\n\n";
            }
        }
        labelDefiniciones.setText(definicionesTexto);

        String ejemploTexto = "";
        for (int i = 0; i < palabra.getEjemplos().length; i++) {
            String eg = palabra.getEjemplos()[i];
            ejemploTexto += eg;
            if (i < palabra.getEjemplos().length - 1) {
                ejemploTexto += "\n\n";
            }
        }
        labelEjemplos.setText(ejemploTexto);
        revalidate();
        repaint();
    }

    private void mostrarError(String texto) {
        String mensaje = "La palabra " + texto + " no se encuentra en la base de datos. Lo sentimos.";
        labelPalabra.setText(mensaje);
        labelLexico.setText("");
        labelDefiniciones.setText("");
        labelEjemplos.setText("");
    }
}
