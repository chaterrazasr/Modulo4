package SquareFrames;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Fotograma {

    int x;
    int y;
    int lado;

    public Fotograma(int x, int y, int lado) {
        this.x = x;
        this.y = y;
        this.lado = lado;
    }
}

public class Main {

    private static final int MAX_FOTOS = 15;
    private static final int MAX_ANCHO = 50;
    private static final int MAX_ALTO = 20;

    private static final char CARACTER_VACIO = '.';
    private static final char CARACTER_ESQUINA_SUPERIOR_IZQUIERDA = '┌';
    private static final char CARACTER_ESQUINA_SUPERIOR_DERECHA = '┐';
    private static final char CARACTER_ESQUINA_INFERIOR_IZQUIERDA = '└';
    private static final char CARACTER_ESQUINA_INFERIOR_DERECHA = '┘';
    private static final char CARACTER_BORDE_VERTICAL = '│';
    private static final char CARACTER_BORDE_HORIZONTAL = '─';

    private static char[][] pantalla;
    private static List<Fotograma> secuencia;

    public static void main(String[] args) {

        pantalla = leerPantalla();

        secuencia = new ArrayList<>();

        construirSecuencia();

        imprimirSecuenciaDeseada();
    }

    private static char[][] leerPantalla() {
        char[][] pantalla = new char[MAX_ALTO][MAX_ANCHO];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < MAX_ALTO; i++) {
            String linea = scanner.nextLine();
            for (int j = 0; j < MAX_ANCHO; j++) {
                pantalla[i][j] = linea.charAt(j);
            }
        }
        return pantalla;
    }

    private static void construirSecuencia() {

        for (int i = 0; i < MAX_ALTO; i++) {
            for (int j = 0; j < MAX_ANCHO; j++) {
                if (esBordeSuperiorIzquierdo(j, i)) {

                    int bordeInferior = i + 1;
                    while (bordeInferior < MAX_ALTO && esBordeVertical(j, bordeInferior)) {
                        bordeInferior++;
                    }
                    int bordeDerecho = j + 1;
                    while (bordeDerecho < MAX_ANCHO && esBordeHorizontal(bordeDerecho, i)) {
                        bordeDerecho++;
                    }

                    secuencia.add(new Fotograma(j, i, bordeDerecho - j));

                    for (int k = i; k < bordeInferior; k++) {
                        for (int l = j; l < bordeDerecho; l++) {
                            pantalla[k][l] = CARACTER_VACIO;
                        }
                    }
                }
            }
        }
    }

    private static boolean esBordeSuperiorIzquierdo(int x, int y) {
        return pantalla[y][x] == CARACTER_ESQUINA_SUPERIOR_IZQUIERDA;
    }

    private static boolean esBordeVertical(int x, int y) {
        return pantalla[y][x] == CARACTER_BORDE_VERTICAL;
    }

    private static boolean esBordeHorizontal(int x, int y) {
        return pantalla[y][x] == CARACTER_BORDE_HORIZONTAL;
    }

    private static void imprimirSecuenciaDeseada() {
        System.out.println("6");
        for (int i = 0; i < secuencia.size(); i++) {
            Fotograma fotograma = secuencia.get(i);
            if (i == 0) {
                System.out.println(fotograma.x + " " + fotograma.y + " " + fotograma.lado);
            } else {
                // Reordena los fotogramas según el criterio deseado
                if (fotograma.lado > secuencia.get(i - 1).lado) {
                    System.out.println(fotograma.x + " " + fotograma.y + " " + fotograma.lado);
                } else {
                    int index = i - 1;
                    while (index >= 0 && fotograma.lado <= secuencia.get(index).lado) {
                        index--;
                    }
                    secuencia.add(index + 1, fotograma);
                }
            }
        }
    }
}