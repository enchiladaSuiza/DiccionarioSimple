package paquete;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Diccionario {

    private static Connection connection = ConnectionDB.getConnection();
    public static final String[] lexicos = {"Adverbio, Adjetivo, Sustantivo, Verbo"};

    public static ArrayList<String> conseguirDefiniciones(String palabra) {
        ArrayList<String> definiciones = new ArrayList<>();
        try {
            Statement delcaracion = connection.createStatement();
            String consulta = "SELECT d.definicion\n" +
                    "FROM palabra_definiciones pd\n" +
                    "JOIN palabras p ON p.idpalabra = pd.idpalabra\n" +
                    "JOIN definiciones d ON d.iddefinicion = pd.iddefinicion\n" +
                    "WHERE p.palabra = '" + palabra + "'";
            ResultSet resultados = delcaracion.executeQuery(consulta);

            if (!resultados.next()) {
                return null;
            }

            String primeraDefinicion = resultados.getString("definicion");
            definiciones.add(primeraDefinicion);

            while (resultados.next()) {
                String definicion = resultados.getString("definicion");
                definiciones.add(definicion);
            }
            resultados.close();
            delcaracion.close();
        }
        catch (SQLException e) {
            System.out.println("Algo salió mal.");
            System.out.println(e.getMessage());
        }
        return definiciones;
    }

    public static ArrayList<String> conseguirEjemplos(String palabra) {
        ArrayList<String> ejemplos = new ArrayList<>();
        try {
            Statement delcaracion = connection.createStatement();
            String consulta = "SELECT ejemplo\n" +
                    "FROM palabra_ejemplos pe\n" +
                    "JOIN palabras p ON p.idpalabra = pe.idpalabra\n" +
                    "JOIN ejemplos e ON  e.idejemplo = pe.idejemplo\n" +
                    "WHERE p.palabra = '" + palabra + "'";
            ResultSet resultados = delcaracion.executeQuery(consulta);

            if (!resultados.next()) {
                return null;
            }

            String primerEjemplo = resultados.getString("ejemplo");
            ejemplos.add(primerEjemplo);

            while (resultados.next()) {
                String ejemplo = resultados.getString("ejemplo");
                ejemplos.add(ejemplo);
            }
            resultados.close();
            delcaracion.close();
        }
        catch (SQLException e) {
            System.out.println("Algo salió mal.");
            System.out.println(e.getMessage());
        }
        return ejemplos;
    }

    public static String conseguirLexico(String palabra) {
        String lexico = lexicos[0];
        try {
            Statement declaracion = connection.createStatement();
            String consulta = "SELECT lexico \n" +
                    "FROM palabras p\n" +
                    "JOIN lexicos l ON p.idlexico = l.idlexico\n" +
                    "WHERE p.palabra = '" + palabra + "'";
            ResultSet resultados = declaracion.executeQuery(consulta);

            if (!resultados.next()) {
                return null;
            }
            lexico = resultados.getString("lexico");
            resultados.close();
            declaracion.close();
        }
        catch (SQLException e) {
            System.out.println("Algo salió mal.");
            System.out.println(e.getMessage());
        }
        return lexico;
    }
}
