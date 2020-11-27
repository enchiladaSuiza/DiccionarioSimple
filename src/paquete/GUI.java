package paquete;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GUI extends JFrame {

    private Busqueda busqueda;
    private ListaDePalabras listaDePalabras;
    private ListasPersonales listasPersonales;
    private JTabbedPane tabbedPane;

    GUI() {
        super("Diccionario");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);

        busqueda = new Busqueda();
        listaDePalabras = new ListaDePalabras();
        listasPersonales = new ListasPersonales();

        tabbedPane = new JTabbedPane();
        tabbedPane.add("Búsqueda", busqueda);
        tabbedPane.add("Todas las palabras", listaDePalabras);
        tabbedPane.add("Listas personales", listasPersonales);
        this.add(tabbedPane);
        this.setVisible(true);
    }

    public static String[] popupDatos() {
        String usuario = JOptionPane.showInputDialog("Ingresar nombre de usuario (\"root\" por defecto)");
        String password = JOptionPane.showInputDialog("Ingresar contraseña");
        return new String[]{usuario, password};
    }

    public static void popupError() {
        JOptionPane.showMessageDialog(null,
                "No fue posible conectar con la base de datos.",
                "Error ingresando a la base de datos",
                JOptionPane.WARNING_MESSAGE);
    }

    public void cambiarPantalla(int indice) {
        tabbedPane.setSelectedIndex(indice);
    }

    public void buscarPalabra(Palabra palabra) {
        cambiarPantalla(0);
        busqueda.mostrarDetalles(palabra);
    }

    public ArrayList<String> obtenerListas() {
        HashMap<String, ArrayList<Palabra>> hashMap = listasPersonales.getListas();
        if (hashMap == null) {
            return null;
        }
        return new ArrayList<>(hashMap.keySet());
    }

    public boolean palabraEnLista(String lista, String palabra) {
        return listasPersonales.palabraEstaEnLista(lista, palabra);
    }

    public void agregarALaLista(String lista, String palabra) {
        listasPersonales.agregarALaLista(lista, palabra);
    }

    public void quitarDeLaLista(String lista, String palabra) {
        listasPersonales.quitarDeLaLista(lista, palabra);
    }
}
