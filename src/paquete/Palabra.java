package paquete;

import java.util.ArrayList;

public class Palabra {
    private final String palabra;
    private final String lexico;
    private final ArrayList<String> sinonimos;
    private final ArrayList<String> traducciones;
    private final ArrayList<String> definiciones;
    private final ArrayList<String> ejemplos;

    Palabra(String palabra) {
        this.palabra = palabra;
        sinonimos = Diccionario.conseguirSinonimos(palabra);
        traducciones = Diccionario.conseguirTraducciones(palabra);
        lexico = Diccionario.conseguirLexico(palabra);
        definiciones = Diccionario.conseguirDefiniciones(palabra);
        ejemplos = Diccionario.conseguirEjemplos(palabra);
    }

    public boolean estaEnLaBase() {
        return lexico != null || definiciones != null;
    }

    public boolean tieneSinonimos() { return !sinonimos.isEmpty(); }

    public String toString() {
        StringBuilder cadena = new StringBuilder(palabra + " (" + lexico + ")\n");
        for (String definicion : definiciones) {
            cadena.append(definicion).append("\n");
        }
        for (String ejemplo : ejemplos) {
            cadena.append(ejemplo).append("\n");
        }

        return cadena.toString();
    }

    public String getPalabra() {
        return palabra;
    }

    public String getLexico() {
        return lexico;
    }

    public String[] getSinonimos() { return sinonimos.toArray(new String[0]); }

    public String[] getTraducciones() { return traducciones.toArray(new String[0]); }

    public String[] getDefiniciones() {
        return definiciones.toArray(new String[0]);
    }

    public String[] getEjemplos() {
        return ejemplos.toArray(new String[0]);
    }
}
