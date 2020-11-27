package paquete;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListasPersonales extends JScrollPane {

    private HashMap<String, ArrayList<Palabra>> listas;
    private HashMap<JLabel, JList<String>> componentes;
    private JPanel contenedor;

    ListasPersonales() {
        super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        listas = Diccionario.conseguirListas();

        contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        componentes = new HashMap<>();

        for (String lista : listas.keySet()) {
            JLabel label = new JLabel(lista);
            label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            ArrayList<String> strings = new ArrayList<>();
            for (Palabra palabra : listas.get(lista)) {
                strings.add(palabra.getPalabra());
            }
            DefaultListModel<String> defaultListModel = new DefaultListModel<>();
            defaultListModel.addAll(strings);
            JList<String> jlist = new JList<>(defaultListModel);
            jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jlist.setLayoutOrientation(JList.VERTICAL_WRAP);
            jlist.setVisibleRowCount(-1);
            componentes.put(label, jlist);
        }

        for (Map.Entry<JLabel, JList<String>> entrada : componentes.entrySet()) {
            contenedor.add(entrada.getKey());
            contenedor.add(Box.createRigidArea(new Dimension(0, 5)));
            contenedor.add(entrada.getValue());
        }

        this.setViewportView(contenedor);
    }
}
