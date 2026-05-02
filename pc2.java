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
