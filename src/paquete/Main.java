package paquete;

import java.sql.Connection;

public class Main {
    public static void main (String[] args) {
        Connection connection = ConnectionDB.getConnection();
        if (connection != null) {
            Diccionario.setConnection(connection);
            new GUI();
        }
    }
}
