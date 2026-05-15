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

    // INSERTAR AL FINAL
    public void insertar(int dato) {

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

    // MOSTRAR LISTA
    public String mostrar() {

        String lista = "";

        Nodo actual = cabeza;

        while (actual != null) {
            lista += actual.dato + " -> ";
            actual = actual.siguiente;
        }

        lista += "null";

        return lista;
    }

    // INVERTIR LISTA
    public void invertir() {

        Nodo anterior = null;
        Nodo actual = cabeza;
        Nodo siguiente;

        while (actual != null) {

            siguiente = actual.siguiente;
            actual.siguiente = anterior;
            anterior = actual;
            actual = siguiente;
        }

        cabeza = anterior;
    }

    // DETECTAR CICLO
    public boolean detectarCiclo() {

        Nodo lento = cabeza;
        Nodo rapido = cabeza;

        while (rapido != null && rapido.siguiente != null) {

            lento = lento.siguiente;
            rapido = rapido.siguiente.siguiente;

            if (lento == rapido) {
                return true;
            }
        }

        return false;
    }

    // CREAR CICLO
    public void crearCiclo() {

        Nodo actual = cabeza;

        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }

        actual.siguiente = cabeza;
    }

    // FUSIONAR LISTAS
    public static ListaEnlazada fusionar(
            ListaEnlazada l1,
            ListaEnlazada l2) {

        ListaEnlazada nueva = new ListaEnlazada();

        Nodo a = l1.cabeza;
        Nodo b = l2.cabeza;

        while (a != null && b != null) {

            if (a.dato < b.dato) {
                nueva.insertar(a.dato);
                a = a.siguiente;
            } else {
                nueva.insertar(b.dato);
                b = b.siguiente;
            }
        }

        while (a != null) {
            nueva.insertar(a.dato);
            a = a.siguiente;
        }

        while (b != null) {
            nueva.insertar(b.dato);
            b = b.siguiente;
        }

        return nueva;
    }
    public void eliminarCiclo() {

        if (!detectarCiclo()) {
            return;
        }

        Nodo lento = cabeza;
        Nodo rapido = cabeza;

        do {
            lento = lento.siguiente;
            rapido = rapido.siguiente.siguiente;
        } while (lento != rapido);

        lento = cabeza;

        while (lento.siguiente != rapido.siguiente) {
            lento = lento.siguiente;
            rapido = rapido.siguiente;
        }

        rapido.siguiente = null;
    }
}

public class Main {

    public static String menu() {

        return """
                
                ===== MENU =====
                1. Insertar en lista 1
                2. Insertar en lista 2
                3. Mostrar listas
                4. Invertir lista 1
                5. Detectar ciclo lista 1
                6. Crear ciclo lista 1
                7. Fusionar listas
                8. eliminar ciclo
                9. Salir
                
                Opcion:
                """;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ListaEnlazada lista1 = new ListaEnlazada();
        ListaEnlazada lista2 = new ListaEnlazada();

        int opcion;
        int dato;

        do {

            System.out.println(menu());
            opcion = sc.nextInt();

            switch (opcion) {

                case 1 -> {

                    System.out.print("Dato: ");
                    dato = sc.nextInt();

                    lista1.insertar(dato);
                }

                case 2 -> {

                    System.out.print("Dato: ");
                    dato = sc.nextInt();

                    lista2.insertar(dato);
                }

                case 3 -> {

                    System.out.println("Lista 1:");
                    System.out.println(lista1.mostrar());

                    System.out.println("Lista 2:");
                    System.out.println(lista2.mostrar());
                }

                case 4 -> {

                    lista1.invertir();

                    System.out.println("Lista invertida");
                }

                case 5 -> {

                    if (lista1.detectarCiclo()) {
                        System.out.println("Hay ciclo");
                    } else {
                        System.out.println("No hay ciclo");
                    }
                }

                case 6 -> {

                    lista1.crearCiclo();

                    System.out.println("Ciclo creado");
                }

                case 7 -> {

                    ListaEnlazada fusion =
                            ListaEnlazada.fusionar(lista1, lista2);

                    System.out.println("Lista fusionada:");
                    System.out.println(fusion.mostrar());
                }
                case 8 -> {
                    lista1.eliminarCiclo();
                    System.out.println("ciclo eliminado");

                case 9 -> {
                    System.out.println("Programa finalizado");
                }

                default -> {
                    System.out.println("Opcion invalida");
                }
            }

        } while (opcion != 8);
    }
}
