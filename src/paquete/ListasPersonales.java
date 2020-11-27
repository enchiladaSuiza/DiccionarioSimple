package paquete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ListasPersonales extends JScrollPane implements ActionListener {

    private HashMap<String, ArrayList<Palabra>> listas;
    private ArrayList<JLabel> labels;
    private ArrayList<JScrollPane> scrollPanes;
    private JPanel contenedor;
    private JPanel footer;
    private JButton nuevaLista;
    private JButton eliminarLista;

    ListasPersonales() {
        super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        labels = new ArrayList<>();
        // jlists = new ArrayList<>();
        scrollPanes = new ArrayList<>();
        listas = new HashMap<>();

        nuevaLista = new JButton("Nueva lista...");
        nuevaLista.addActionListener(this);
        eliminarLista = new JButton("Eliminar lista...");
        eliminarLista.addActionListener(this);

        footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.X_AXIS));
        footer.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        footer.add(nuevaLista);
        footer.add(Box.createHorizontalGlue());
        footer.add(eliminarLista);

        actualizar();

        this.setViewportView(contenedor);
    }

    public void actualizar() {
        contenedor.removeAll();
        labels.clear();
        // jlists.clear();
        scrollPanes.clear();
        listas = Diccionario.conseguirListas();
        if (listas != null) {
            for (String lista : listas.keySet()) {
                JLabel label = new JLabel(lista);
                ArrayList<String> strings = new ArrayList<>();
                for (Palabra palabra : listas.get(lista)) {
                    strings.add(palabra.getPalabra());
                }
                DefaultListModel<String> defaultListModel = new DefaultListModel<>();
                defaultListModel.addAll(strings);
                JList<String> jlist = new JList<>(defaultListModel);
                jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                jlist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
                jlist.setVisibleRowCount(-1);
                jlist.setAlignmentX(JList.LEFT_ALIGNMENT);
                jlist.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Object objeto = e.getSource();
                        JList<String> lista = (JList<String>) objeto;
                        if (e.getClickCount() == 2) {
                            int indice = lista.locationToIndex(e.getPoint());
                            String palabra = lista.getModel().getElementAt(indice);
                            Palabra p = new Palabra(palabra);
                            Main.getGUI().buscarPalabra(p);
                        }
                    }
                });
                JScrollPane scrollPane = new JScrollPane(jlist);
                scrollPane.setAlignmentX(JScrollPane.LEFT_ALIGNMENT);
                scrollPane.setPreferredSize(new Dimension(500, 75));
                labels.add(label);
                // jlists.add(jlist);
                scrollPanes.add(scrollPane);
            }

            for (int i = 0; i < listas.size(); i++) {
                contenedor.add(labels.get(i));
                contenedor.add(Box.createRigidArea(new Dimension(0, 5)));
                contenedor.add(scrollPanes.get(i));
                contenedor.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        contenedor.add(footer);
        revalidate();
        repaint();
    }

    public boolean palabraEstaEnLista(String lista, String palabra) {
        ArrayList<Palabra> palabras = listas.get(lista);
        for (Palabra p : palabras) {
            if (p.getPalabra().equals(palabra)) {
                return true;
            }
        }
        return false;
    }

    public void agregarALaLista (String lista, String palabra) {
        if (!palabraEstaEnLista(lista, palabra)) {
            Diccionario.agregarALaLista(lista, palabra);
            actualizar();
        }
    }

    public void quitarDeLaLista (String lista, String palabra) {
        Diccionario.quitarDeLaLista(lista, palabra);
        actualizar();
    }

    public HashMap<String, ArrayList<Palabra>> getListas() {
        return listas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nuevaLista) {
            String nombre = JOptionPane.showInputDialog("Escriba el nombre de la nueva lista");
            if (nombre == null) {
                return;
            }
            if (nombre.isEmpty()) {
                nombre = "Lista" + (listas.size() + 1);
            }
            Diccionario.nuevaLista(nombre);
        }
        else if (e.getSource() == eliminarLista) {
            String nombre = JOptionPane.showInputDialog("Escriba el nombre de la lista que desea eliminar");
            if (nombre == null || nombre.isEmpty()) {
                return;
            }
            Diccionario.eliminarLista(nombre);
        }
        actualizar();
    }
}
