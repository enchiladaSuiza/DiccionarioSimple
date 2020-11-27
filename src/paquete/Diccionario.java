package paquete;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

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

    public static HashMap<String, ArrayList<Palabra>> conseguirListas() {
        HashMap<String, ArrayList<Palabra>> hashMap = new HashMap<>();
        ArrayList<String> listas = new ArrayList<>();
        try {
            Statement declaracion = connection.createStatement();
            String consulta = "SELECT lista FROM listas";
            ResultSet resultados = declaracion.executeQuery(consulta);

            if (!resultados.next()) {
                return null;
            }

            String primeraLista = resultados.getString("lista");
            listas.add(primeraLista);

            while (resultados.next()) {
                String lista = resultados.getString("lista");
                listas.add(lista);
            }
            resultados.close();
            declaracion.close();

            for (String lista : listas) {
                ArrayList<Palabra> palabras = new ArrayList<>();
                Statement declaracionPalabras = connection.createStatement();
                String consultaPalabras = "SELECT p.palabra\n" +
                        "FROM lista_palabras lp\n" +
                        "JOIN palabras p ON p.idpalabra = lp.idpalabra\n" +
                        "JOIN listas l ON l.idlista = lp.idlista\n" +
                        "WHERE l.lista = '" + lista + "'";
                ResultSet resultadosPalabras = declaracionPalabras.executeQuery(consultaPalabras);

                while (resultadosPalabras.next()) {
                    String texto = resultadosPalabras.getString("palabra");
                    Palabra palabra = new Palabra(texto);
                    palabras.add(palabra);
                }
                resultadosPalabras.close();
                declaracionPalabras.close();
                hashMap.put(lista, palabras);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hashMap;
    }

    public static int conseguirIDlista(String lista) {
        int idlista = 0;
        try {
            Statement declaracionLista = connection.createStatement();
            String consultaLista = "SELECT idlista FROM listas\n" +
                    "WHERE lista = '" + lista + "'";
            ResultSet resultadoLista = declaracionLista.executeQuery(consultaLista);

            if (!resultadoLista.next()) {
                System.out.println("Checar nombre de la lista!");
                return idlista;
            }
            idlista = resultadoLista.getInt("idlista");
            resultadoLista.close();
            declaracionLista.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return idlista;
    }

    public static int conseguirIDpalabra(String palabra) {
        int idpalabra = 0;
        try {
            Statement declaracionPalabra = connection.createStatement();
            String consultaPalabra = "SELECT idpalabra FROM palabras\n" +
                    "WHERE palabra = '" + palabra + "'";
            ResultSet resultadoPalabra = declaracionPalabra.executeQuery(consultaPalabra);

            if (!resultadoPalabra.next()) {
                System.out.println("Checar palabra!");
                return idpalabra;
            }

            idpalabra = resultadoPalabra.getInt("idpalabra");
            resultadoPalabra.close();
            declaracionPalabra.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return idpalabra;
    }

    public static void agregarALaLista(String lista, String palabra) {
        int idlista = conseguirIDlista(lista);
        int idpalabra = conseguirIDpalabra(palabra);
        try {
            Statement declaracionInsercion = connection.createStatement();
            String insercion = "INSERT INTO lista_palabras\n" +
                    "VALUES (" + idlista + ", " + idpalabra +")";
            declaracionInsercion.execute(insercion);
            declaracionInsercion.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void quitarDeLaLista(String lista, String palabra) {
        int idlista = conseguirIDlista(lista);
        int idpalabra = conseguirIDpalabra(palabra);
        try {
            Statement declaracionEliminacion = connection.createStatement();
            String eliminacion = "DELETE FROM lista_palabras\n" +
                    "WHERE idlista = " + idlista + " AND idpalabra = " + idpalabra;
            declaracionEliminacion.execute(eliminacion);
            declaracionEliminacion.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void nuevaLista(String nombre) {
        try {
            Statement declaracion = connection.createStatement();
            String insercion = "INSERT INTO listas (lista)\n" +
                    "VALUES ('" + nombre + "')";
            declaracion.execute(insercion);
            declaracion.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void eliminarLista(String nombre) {
        int idlista = conseguirIDlista(nombre);
        if (idlista == 0) {
            return;
        }
        try {
            Statement declaracionPalabras = connection.createStatement();
            String eliminacionPalabras = "DELETE FROM lista_palabras\n" +
                    "WHERE idlista = " + idlista;
            declaracionPalabras.execute(eliminacionPalabras);
            declaracionPalabras.close();

            Statement declaracionLista = connection.createStatement();
            String eliminacionLista = "DELETE FROM listas\n" +
                    "WHERE idlista = " + idlista;
            declaracionLista.execute(eliminacionLista);
            declaracionLista.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
