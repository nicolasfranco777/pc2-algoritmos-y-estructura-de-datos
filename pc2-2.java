import java.util.Scanner;

class Nodo {
    int dato;
    Nodo siguiente;

    public Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

class ListaEnlazada {
    Nodo cabeza;

    public void insertarInicio(int dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }

    public void insertarFinal(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }

        Nodo actual = cabeza;

        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }

        actual.siguiente = nuevo;
    }

    public void eliminar(int dato) {

        if (cabeza == null) {
            return;
        }

        if (cabeza.dato == dato) {
            cabeza = cabeza.siguiente;
            return;
        }

        Nodo actual = cabeza;

        while (actual.siguiente != null &&
               actual.siguiente.dato != dato) {

            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
        }
    }

    public String mostrar() {

        if (cabeza == null) {
            return "Lista vacia";
        }

        String lista = "";

        Nodo actual = cabeza;

        while (actual != null) {
            lista += actual.dato + " -> ";
            actual = actual.siguiente;
        }

        lista += "null";

        return lista;
    }
}

public class Main {

    public static String menu() {

        return """
               ===== MENU =====
               1. Insertar inicio
               2. Insertar final
               3. Eliminar nodo
               4. Mostrar lista
               5. Salir
               """;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ListaEnlazada lista = new ListaEnlazada();

        int opcion;
        int dato;

        do {

            System.out.println(menu());
            opcion = sc.nextInt();

            switch (opcion) {

                case 1 -> {
                    System.out.print("Dato: ");
                    dato = sc.nextInt();

                    lista.insertarInicio(dato);
                }

                case 2 -> {
                    System.out.print("Dato: ");
                    dato = sc.nextInt();

                    lista.insertarFinal(dato);
                }

                case 3 -> {
                    System.out.print("Dato a eliminar: ");
                    dato = sc.nextInt();

                    lista.eliminar(dato);
                }

                case 4 -> {
                    System.out.println(lista.mostrar());
                }

                case 5 -> {
                    System.out.println("Programa finalizado");
                }

                default -> {
                    System.out.println("Opcion invalida");
                }
            }

        } while (opcion != 5);
    }
}
