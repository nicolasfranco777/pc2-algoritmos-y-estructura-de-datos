package javaapplication2;
class ColaCircular {
    private int[] arr;
    private int frente, fin, tamaño, capacidad;

    public ColaCircular(int capacidad) {
        this.capacidad = capacidad;
        arr = new int[capacidad];
        frente = 0;
        fin = -1;
        tamaño = 0;
    }

    public void enqueue(int valor) {
        if (tamaño == capacidad) {
            System.out.println("Cola llena");
            return;
        }
        fin = (fin + 1) % capacidad;
        arr[fin] = valor;
        tamaño++;
    }

    public int dequeue() {
        if (tamaño == 0) {
            System.out.println("Cola vacía");
            return -1;
        }
        int valor = arr[frente];
        frente = (frente + 1) % capacidad;
        tamaño--;
        return valor;
    }

    public void mostrar() {
        for (int i = 0; i < tamaño; i++) {
            System.out.print(arr[(frente + i) % capacidad] + " ");
        }
        System.out.println();
    }
}


//main

package javaapplication2;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ColaCircular cola = new ColaCircular(5);
        int opcion;

        do {
            opcion = mostrarMenu(
                    "COLA CIRCULAR",
                    "Colar",
                    "Desencolar",
                    "Mostrar",
                    "Salir"
            );

            switch (opcion) {
                case 1 -> {
                    System.out.print("Valor: ");
                    int valor = sc.nextInt();
                    cola.enqueue(valor);
                }

                case 2 -> {
            int eliminado = cola.dequeue();

            if (eliminado == -1) {
                System.out.println("No hay valores en la cola");
            } else {
                System.out.println("Eliminado: " + eliminado);
            }
        }

                case 3 -> cola.mostrar();

            }

        } while (opcion != 4);
    }

    public static int mostrarMenu(String titulo, String... opciones) {
        System.out.println("\n=== " + titulo + " ===");

        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }

        System.out.print("Opción: ");
        return sc.nextInt();
    }
}
