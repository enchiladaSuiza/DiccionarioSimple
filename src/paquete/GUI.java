package paquete;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    Busqueda busqueda;
    JTabbedPane tabbedPane;

    GUI() {
        super("Diccionario");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        tabbedPane.add("Búsqueda", busqueda);
        this.add(tabbedPane);
        setVisible(true);
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
