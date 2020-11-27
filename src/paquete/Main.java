/**
 * Proyecto final para Programación Oriendata a Objetos
 * Diccionario simple
 * Tercer Semestre Grupo 4
 * @author Santiago Alonso Vergara Lorenzo
 */
package paquete;

import java.sql.Connection;

public abstract class Main {

    private static GUI gui;

    public static void main (String[] args) {
        Connection connection = ConnectionDB.getConnection();
        if (connection != null) {
            Diccionario.setConnection(connection);
            gui = new GUI();
        }
    }

    public static GUI getGUI() {
        return gui;
    }
}
