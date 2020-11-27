package paquete;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class Diccionario {

    private static Connection connection;
    public static final String[] lexicos = {"Adverbio", "Adjetivo", "Sustantivo", "Verbo"};

    public static void setConnection(Connection connection) {
        Diccionario.connection = connection;
    }

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
            String consulta = "SELECT idlexico\n" +
                    "FROM palabras\n" +
                    "WHERE palabra = '" + palabra + "'";
            ResultSet resultados = declaracion.executeQuery(consulta);

            if (!resultados.next()) {
                return null;
            }
            int indice = resultados.getInt("idlexico");
            lexico = lexicos[indice - 1]; // En la base de datos los lexicos empiezan desde 1
            resultados.close();
            declaracion.close();
        }
        catch (SQLException e) {
            System.out.println("Algo salió mal.");
            System.out.println(e.getMessage());
        }
        return lexico;
    }

    public static ArrayList<String> conseguirSinonimos(String palabra) {
        ArrayList<String> sinonimos = new ArrayList<>();
        try {
            Statement declaracion = connection.createStatement();
            String consulta = "SELECT p2.palabra AS sinonimo\n" +
                    "FROM sinonimos s\n" +
                    "JOIN palabras p1 ON s.idpalabra1 = p1.idpalabra\n" +
                    "JOIN palabras p2 ON s.idpalabra2 = p2.idpalabra\n" +
                    "WHERE p1.palabra = '" + palabra + "'";
            ResultSet resultados = declaracion.executeQuery(consulta);

            while (resultados.next()) {
                String sinonimo = resultados.getString("sinonimo");
                sinonimos.add(sinonimo);
            }
            resultados.close();
            declaracion.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return sinonimos;
    }

    public static ArrayList<String> conseguirTraducciones(String palabra) {
        ArrayList<String> traducciones = new ArrayList<>();
        try {
            Statement declaracion = connection.createStatement();
            String consulta = "SELECT t.traduccion\n" +
                    "FROM palabra_traducciones pt\n" +
                    "JOIN palabras p ON p.idpalabra = pt.idpalabra\n" +
                    "JOIN traducciones t ON t.idtraduccion = pt.idtraduccion\n" +
                    "WHERE p.palabra = '" + palabra + "'";
            ResultSet resultados = declaracion.executeQuery(consulta);

            if (!resultados.next()) {
                return null;
            }

            String primeraTraduccion = resultados.getString("traduccion");
            traducciones.add(primeraTraduccion);

            while (resultados.next()) {
                String traduccion = resultados.getString("traduccion");
                traducciones.add(traduccion);
            }
            resultados.close();
            declaracion.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return traducciones;
    }

    public static ArrayList<String> conseguirTodasLasPalabras() {
        ArrayList<String> palabras = new ArrayList<>();
        try {
            Statement declaracion = connection.createStatement();
            String consulta = "SELECT palabra FROM palabras ORDER BY palabra";
            ResultSet resultados = declaracion.executeQuery(consulta);

            while (resultados.next()) {
                String palabra = resultados.getString("palabra");
                palabras.add(palabra);
            }

            resultados.close();
            declaracion.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return palabras;
    }
}
