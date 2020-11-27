package paquete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ElegirListas extends JDialog implements ActionListener, PropertyChangeListener {
    ArrayList<JCheckBox> checkboxes;
    ArrayList<String> seleccionados;
    ArrayList<String> deseleccionados;
    String guardar = "Guardar";
    String cancelar = "Cancelar";
    JOptionPane optionPane;
    Palabra palabra;

    ElegirListas(ArrayList<String> strings, Palabra palabra) {
        super((Dialog) null, "Agregar a lista", true);
        this.palabra = palabra;
        checkboxes = new ArrayList<>();
        seleccionados = new ArrayList<>();
        deseleccionados = new ArrayList<>();
        for (String string : strings) {
            JCheckBox checkbox = new JCheckBox(string);
            checkboxes.add(checkbox);
            checkbox.setSelected(Main.getGUI().palabraEnLista(string, palabra.getPalabra()));
            checkbox.addActionListener(this);
        }

        String[] opciones = {guardar, cancelar};
        optionPane = new JOptionPane(
                checkboxes.toArray(),
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION,
                null,
                opciones,
                opciones[0]);
        optionPane.addPropertyChangeListener(this);
        this.setContentPane(optionPane);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JCheckBox seleccionado = (JCheckBox)e.getSource();
        String lista = seleccionado.getText();
        if (seleccionado.isSelected()) {
            seleccionados.add(lista);
            deseleccionados.remove(lista);
        }
        else {
            deseleccionados.add(lista);
            seleccionados.remove(lista);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String p = evt.getPropertyName();
        if (evt.getSource() == optionPane && JOptionPane.VALUE_PROPERTY.equals(p)) {
            String valor = (String) optionPane.getValue();
            if (valor.equals(guardar)) {
                for (String selec : seleccionados) {
                    Main.getGUI().agregarALaLista(selec, palabra.getPalabra());
                }
                for (String deselec : deseleccionados) {
                    Main.getGUI().quitarDeLaLista(deselec, palabra.getPalabra());
                }
            }
            this.dispose();
        }
    }
}
