package pc2;
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
//lista enlazada
package pc2;
class Nodo {
    int dato;
    Nodo siguiente;

    public Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

class ColaLista {
    private Nodo frente, fin;

    public ColaLista() {
        frente = fin = null;
    }

    public void enqueue(int valor) {
        Nodo nuevo = new Nodo(valor);
        if (fin == null) {
            frente = fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    public int dequeue() {
        if (frente == null) {
            System.out.println("Cola vacía");
            return -1;
        }
        int valor = frente.dato;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        return valor;
    }

    public void mostrar() {
        Nodo temp = frente;
        while (temp != null) {
            System.out.print(temp.dato + " ");
            temp = temp.siguiente;
        }
        System.out.println();
    }
}

//metodo cola con prioridad 
package pc2;
import java.util.PriorityQueue;

class ColaPrioridad {
    private PriorityQueue<Integer> cola;

    public ColaPrioridad() {
        cola = new PriorityQueue<>((a, b) -> b - a); // orden descendente
    }

    public void enqueue(int valor) {
        cola.add(valor);
    }

    public int dequeue() {
        if (cola.isEmpty()) {
            System.out.println("Cola vacía");
            return -1;
        }
        return cola.poll();
    }

    public void mostrar() {
        System.out.println(cola);
    }
}

//main

package pc2;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ColaCircular colaCircular = new ColaCircular(5);
        ColaLista colaLista = new ColaLista();
        ColaPrioridad colaPrioridad = new ColaPrioridad();

        int opcion;

        do {
            opcion = mostrarMenu("MENÚ PRINCIPAL",
                    "Cola circular",
                    "Cola lista enlazada",
                    "Cola prioridad",
                    "Salir");

            switch (opcion) {
                case 1 -> menuOperaciones(colaCircular);
                case 2 -> menuOperaciones(colaLista);
                case 3 -> menuOperaciones(colaPrioridad);
            }

        } while (opcion != 4);
    }

    // 🔹 MENÚ GENÉRICO
    public static int mostrarMenu(String titulo, String... opciones) {
        System.out.println("\n=== " + titulo + " ===");

        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }

        System.out.print("Opción: ");
        return sc.nextInt();
    }

    // 🔹 MENÚ DE OPERACIONES (sirve para TODAS las colas)
    public static void menuOperaciones(Object cola) {
        int opcion;

        do {
            opcion = mostrarMenu("OPERACIONES",
                    "Enqueue",
                    "Dequeue",
                    "Mostrar",
                    "Volver");

            switch (opcion) {
                case 1 -> {
                    System.out.print("Valor: ");
                    int valor = sc.nextInt();

                    if (cola instanceof ColaCircular c) c.enqueue(valor);
                    else if (cola instanceof ColaLista c) c.enqueue(valor);
                    else if (cola instanceof ColaPrioridad c) c.enqueue(valor);
                }

                case 2 -> {
                    int eliminado = -1;

                    if (cola instanceof ColaCircular c) eliminado = c.dequeue();
                    else if (cola instanceof ColaLista c) eliminado = c.dequeue();
                    else if (cola instanceof ColaPrioridad c) eliminado = c.dequeue();

                    System.out.println("Eliminado: " + eliminado);
                }

                case 3 -> {
                    if (cola instanceof ColaCircular c) c.mostrar();
                    else if (cola instanceof ColaLista c) c.mostrar();
                    else if (cola instanceof ColaPrioridad c) c.mostrar();
                }

            }

        } while (opcion != 4);
    }
}
