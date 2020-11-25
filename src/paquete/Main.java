package paquete;

public class Main {
    public static void main (String[] args) throws Exception {
        // Es importante que esta línea sea lo primero que se ejecute!
        Diccionario.setConnection(ConnectionDB.getConnection());

        Palabra tiempo = new Palabra("tiempo");
        System.out.println(tiempo);
    }
}
