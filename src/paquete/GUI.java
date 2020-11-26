package paquete;

import javax.swing.*;

public class GUI extends JFrame {

    Busqueda busqueda;
    JTabbedPane tabbedPane;

    GUI() {
        super("Diccionario");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);

        busqueda = new Busqueda();

        tabbedPane = new JTabbedPane();
        tabbedPane.add("Búsqueda", busqueda);
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
                "El usuario o contraseña son incorrectos. Vuelva a intentar.",
                "Error ingresando a la base de datos",
                JOptionPane.WARNING_MESSAGE);
    }
}
