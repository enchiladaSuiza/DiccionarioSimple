package paquete;

import java.util.ArrayList;

public class Palabra {
    private String palabra;
    private String lexico;
    private ArrayList<String> definiciones;
    private ArrayList<String> ejemplos;

    Palabra(String palabra) {
        this.palabra = palabra;
        lexico = Diccionario.conseguirLexico(palabra);
        definiciones = Diccionario.conseguirDefiniciones(palabra);
        ejemplos = Diccionario.conseguirEjemplos(palabra);
    }

    public String toString() {
        String cadena = palabra + " (" + lexico + ")\n";
        for (String definicion : definiciones) {
            cadena += definicion + "\n";
        }
        for (String ejemplo : ejemplos) {
            cadena += ejemplo + "\n";
        }

        return cadena;
    }
}
