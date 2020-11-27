package paquete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Busqueda extends JPanel implements ActionListener {
    private JTextField textField;
    private JPanel header;
    private JPanel footer;
    private JLabel labelPalabra;
    private JLabel labelSinonimos;
    private JLabel labelLexico;
    private JLabel labelTraducciones;
    private JTextArea labelDefiniciones;
    private JTextArea labelEjemplos;
    private JButton agregarLista;
    private Palabra palabraActual;
    private Font negrita = new Font("Arial", Font.BOLD, 14);
    private Font regular = new Font("Arial", Font.PLAIN, 14);
    private Font cursiva = new Font("Arial", Font.ITALIC, 14);

    Busqueda() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(160, 24));
        textField.setMaximumSize(new Dimension(160, 24));
        textField.addActionListener(this);
        textField.setAlignmentX(JTextField.LEFT_ALIGNMENT);

        labelPalabra = new JLabel();
        labelPalabra.setHorizontalTextPosition(JLabel.LEFT);
        labelPalabra.setVerticalTextPosition(JLabel.CENTER);
        labelPalabra.setFont(negrita);

        labelSinonimos = new JLabel();
        labelSinonimos.setHorizontalTextPosition(JLabel.LEFT);
        labelSinonimos.setVerticalTextPosition(JLabel.CENTER);
        labelSinonimos.setFont(regular);

        header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
        header.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        header.add(labelPalabra);
        header.add(Box.createHorizontalGlue());
        header.add(labelSinonimos);
        
        labelLexico = new JLabel();
        labelLexico.setHorizontalTextPosition(JLabel.CENTER);
        labelLexico.setVerticalTextPosition(JLabel.CENTER);
        labelLexico.setHorizontalAlignment(JLabel.CENTER);
        labelLexico.setFont(cursiva);

        labelDefiniciones = new JTextArea();
        labelDefiniciones.setEditable(false);
        labelDefiniciones.setBackground(null);
        labelDefiniciones.setLineWrap(true);
        labelDefiniciones.setWrapStyleWord(true);
        labelDefiniciones.setFont(regular);
        labelDefiniciones.setAlignmentX(JTextArea.LEFT_ALIGNMENT);

        labelEjemplos = new JTextArea();
        labelEjemplos.setEditable(false);
        labelEjemplos.setBackground(null);
        labelEjemplos.setLineWrap(true);
        labelEjemplos.setWrapStyleWord(true);
        labelEjemplos.setFont(cursiva);
        labelEjemplos.setLayout(new BorderLayout());
        labelEjemplos.setAlignmentX(JTextArea.LEFT_ALIGNMENT);

        labelTraducciones = new JLabel();
        labelTraducciones.setHorizontalTextPosition(JLabel.LEFT);
        labelTraducciones.setVerticalTextPosition(JLabel.CENTER);
        labelTraducciones.setFont(regular);

        agregarLista = new JButton("Añadir a una lista...");
        agregarLista.addActionListener(this);

        footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.X_AXIS));
        footer.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        footer.add(labelTraducciones);
        footer.add(Box.createHorizontalGlue());
        footer.add(agregarLista);
        footer.setVisible(false);

        this.add(textField);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(header);
        this.add(Box.createRigidArea(new Dimension(0, 15)));
        this.add(labelLexico);
        this.add(Box.createRigidArea(new Dimension(0, 15)));
        this.add(labelDefiniciones);
        this.add(labelEjemplos);
        this.add(footer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == textField) {
            String texto = textField.getText();
            texto = texto.toLowerCase();
            palabraActual = new Palabra(texto);
            if (palabraActual.estaEnLaBase()) {
                mostrarDetalles(palabraActual);
            }
            else {
                mostrarError(texto);
            }
        }
        else if (e.getSource() == agregarLista) {
            new ElegirListas(Main.getGUI().obtenerListas(), palabraActual);
        }
    }

    public void mostrarDetalles(Palabra palabra) {
        palabraActual = palabra;
        labelPalabra.setText(palabra.getPalabra());

        if (palabra.tieneSinonimos()) {
            StringBuilder sinonimosTexto = new StringBuilder();
            sinonimosTexto.append("Sinónimo de ");
            for (int i = 0; i < palabra.getSinonimos().length; i++) {
                String sin = palabra.getSinonimos()[0];
                sinonimosTexto.append(sin);
                if (i < palabra.getSinonimos().length - 1) {
                    sinonimosTexto.append(", ");
                }
            }
            labelSinonimos.setText(sinonimosTexto.toString());
            header.add(labelSinonimos);
        }
        else {
            header.remove(labelSinonimos);
        }

        labelLexico.setText(palabra.getLexico());
        
        StringBuilder traduccionesTexto = new StringBuilder();
        for (int i = 0; i < palabra.getTraducciones().length; i++) {
            String trad = palabra.getTraducciones()[i];
            traduccionesTexto.append(trad);
            if (i < palabra.getTraducciones().length - 1) {
                traduccionesTexto.append(", ");
            }
        }
        labelTraducciones.setText(traduccionesTexto.toString());

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
        footer.setVisible(true);
        labelEjemplos.setText(ejemploTexto.toString());
        revalidate();
        repaint();
    }

    private void mostrarError(String texto) {
        String mensaje = "La palabra " + texto + " no se encuentra en la base de datos. Lo sentimos.";
        labelPalabra.setText(mensaje);
        labelSinonimos.setText("");
        labelLexico.setText("");
        labelTraducciones.setText("");
        labelDefiniciones.setText("");
        labelEjemplos.setText("");
        footer.setVisible(false);
        revalidate();
        repaint();
    }
}
