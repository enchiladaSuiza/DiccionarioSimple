package paquete;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public abstract class ConnectionDB {

    private static final File datosUsuario = new File("datos.txt");
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/diccionario?serverTimezone=UTC";
    private static String usuario;
    private static String password;

    public static Connection getConnection() {
        ingresarDatos();
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, usuario, password);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            GUI.popupError();
            datosUsuario.delete();
            return null;
        }
    }

    private static void ingresarDatos() {
        try {
            if (datosUsuario.createNewFile()) {
                String[] datos = GUI.popupDatos();
                usuario = datos[0];
                password = datos[1];
                FileWriter escritor = new FileWriter(datosUsuario);
                escritor.write(usuario + "\n" + password);
                escritor.close();
            }
            else {
                FileReader lector = new FileReader(datosUsuario);
                usuario = "";
                password = "";
                int caracter = lector.read();
                while(caracter != -1 && caracter != '\n') {
                    usuario += (char)caracter;
                    caracter = lector.read();
                }
                caracter = lector.read();
                while (caracter != -1 && caracter != '\n') {
                    password += (char)caracter;
                    caracter = lector.read();
                }
                lector.close();
            }
        }
        catch (IOException e) {
            e.getMessage();
        }
    }
}
