package paquete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ConnectionDB {

    static Scanner scanner = new Scanner(System.in);
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/diccionario?serverTimezone=UTC";
    static String usuario;
    static String password;

    public static Connection getConnection() {
        try {
            System.out.println("Ingresando a la base de datos.");
            System.out.print("Nombre de usuario (\"root\" por defecto): ");
            usuario = scanner.nextLine();
            System.out.print("Contraseña: ");
            password = scanner.nextLine();

            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexión exitosa.");
            return connection;
        }
        catch (Exception e) {
            System.out.println("Conexión fallida.");
            System.out.println(e.getMessage());
        }
        return null;
    }
}
