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

    Busqueda() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // this.setPreferredSize(new Dimension(550, 400));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(160, 24));
        textField.setMaximumSize(new Dimension(160, 24));
        textField.addActionListener(this);
        textField.setAlignmentX(JTextField.LEFT_ALIGNMENT);

        labelPalabra = new JLabel();
        labelPalabra.setHorizontalTextPosition(JLabel.CENTER);
        labelPalabra.setVerticalTextPosition(JLabel.CENTER);
        labelPalabra.setBackground(Color.cyan);
        // labelPalabra.setPreferredSize(new Dimension(500, 18));
        Font fuentePalabra = new Font("Arial", Font.BOLD, 14);
        labelPalabra.setFont(fuentePalabra);

        labelLexico = new JLabel();
        labelLexico.setHorizontalTextPosition(JLabel.LEFT);
        labelLexico.setVerticalTextPosition(JLabel.CENTER);
        labelLexico.setBackground(Color.cyan);
        // labelLexico.setPreferredSize(new Dimension(500, 18));
        Font fuenteEjemplo = new Font("Arial", Font.ITALIC, 14);
        labelLexico.setFont(fuenteEjemplo);

        labelDefiniciones = new JTextArea();
        labelDefiniciones.setEditable(false);
        labelDefiniciones.setBackground(null);
        labelDefiniciones.setLineWrap(true);
        labelDefiniciones.setWrapStyleWord(true);
        // labelDefiniciones.setPreferredSize(new Dimension(500, 90));
        Font fuenteDefinicion = new Font("Arial", Font.PLAIN, 14);
        labelDefiniciones.setFont(fuenteDefinicion);
        labelDefiniciones.setAlignmentX(JTextArea.LEFT_ALIGNMENT);

        labelEjemplos = new JTextArea();
        labelEjemplos.setEditable(false);
        labelEjemplos.setBackground(null);
        labelEjemplos.setLineWrap(true);
        labelEjemplos.setWrapStyleWord(true);
        // labelEjemplos.setPreferredSize(new Dimension(500, 90));
        labelEjemplos.setFont(new Font("Arial", Font.ITALIC, 14));
        labelEjemplos.setFont(fuenteEjemplo);
        labelEjemplos.setLayout(new BorderLayout());
        labelEjemplos.setAlignmentX(JTextArea.LEFT_ALIGNMENT);

        this.add(textField);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(labelPalabra);
        this.add(Box.createRigidArea(new Dimension(0, 15)));
        this.add(labelLexico);
        this.add(Box.createRigidArea(new Dimension(0, 15)));
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

    public void mostrarDetalles(Palabra palabra) {
        labelPalabra.setText(palabra.getPalabra());
        labelLexico.setText(palabra.getLexico());

        StringBuilder definicionesTexto = new StringBuilder();
        for (int i = 0; i < palabra.getDefiniciones().length; i++) {
            String def = palabra.getDefiniciones()[i];
            definicionesTexto.append(i + 1).append(". ").append(def);
            if (i < palabra.getDefiniciones().length - 1) {
                definicionesTexto.append("\n\n");
            }
        }
        labelDefiniciones.setText(definicionesTexto.toString());

        StringBuilder ejemploTexto = new StringBuilder();
        for (int i = 0; i < palabra.getEjemplos().length; i++) {
            String eg = palabra.getEjemplos()[i];
            ejemploTexto.append(eg);
            if (i < palabra.getEjemplos().length - 1) {
                ejemploTexto.append("\n\n");
            }
        }
        labelEjemplos.setText(ejemploTexto.toString());
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
