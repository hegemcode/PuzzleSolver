
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

// ESCALADA SIMPLE
public class Algorithm_2 {

    //Atributos
    private Tree a;
    private Board m;
    private Node estadoActual;
    private ArrayList<String> movimientos;
    private int num_nodos = 0; //Contabiliza el numero de nodos generados por el algoritmo

    private Random rand = new Random();

    public Algorithm_2() throws Exception {
        this.movimientos = new ArrayList<>();
        this.m = new Board();
        this.a = new Tree(m);
        this.estadoActual = new Node(this.a.getRaiz().getValor());
        System.out.println("PUZZLE: ");
        this.estadoActual.getValor().mostrarMatriz();
        expandir(this.estadoActual);
    }

    //Copia un tablero fuente a un tablero destino
    public void copiarMatriz(Board original, Board copia) {
        for (int i = 0; i < original.getSize(); i++) {
            for (int j = 0; j < original.getSize(); j++) {
                copia.getMatriz()[i][j] = original.getMatriz()[i][j];
            }
        }
    }

    // Expande el arbol de decisiones
    public void expandir(Node estadoActual) throws Exception {
        generarHijos(estadoActual); // Genera hijos del estado actual
        Node siguiente = this.estadoActual;
        setMovimientos(estadoActual.getValor().getMov());

        if (estadoActual.getValor().contarPosiciones() != 14) { //Comprobamos que no hayamos completado el puzzle
            if (continuar(estadoActual, siguiente)) { //Si hay algun estado mejor que el actual expandimos
                expandir(siguiente);
            } else {
                System.out.println("A better state was no found. CURRENT STATE: \n");
                estadoActual.getValor().mostrarMatriz();
                System.out.println("Explored Nodes: " + num_nodos);
            }
        } else { //En caso de haber hallado una solucion , se muestran los movimientos realizados y el tablero resultante

            System.out.println("Final Board: ");
            estadoActual.getValor().mostrarMatriz();
            printMovimientos();
        }
    }

    public boolean generarMovimientos(Node estadoActual, int fila, int col) throws Exception {

        LinkedList<Integer> generados = new LinkedList<>();
        boolean fin = false;

        Board arriba = new Board();
        copiarMatriz(this.estadoActual.getValor(), arriba);

        Board abajo = new Board();
        copiarMatriz(this.estadoActual.getValor(), abajo);

        Board derecha = new Board();
        copiarMatriz(this.estadoActual.getValor(), derecha);

        Board izquierda = new Board();
        copiarMatriz(this.estadoActual.getValor(), izquierda);


        while (!fin && generados.size() != 4) {
            int caso = rand.nextInt(4);
            switch (caso) {
                case 0: {
                    if (!buscarLista(generados, caso)) {
                        if (arriba.moverArriba(fila, col)) {
                            arriba.setMov(arriba.getMatriz()[fila][col] + "S");
                            estadoActual.setHijos(arriba);
                            num_nodos++;
                            if (arriba.contarPosiciones() > this.estadoActual.getValor().contarPosiciones()) {
                                this.estadoActual = new Node(arriba);

                                return true;
                            }
                        }
                        generados.add(caso);
                    }
                }
                break;
                case 1: {
                    if (!buscarLista(generados, caso)) {
                        if (abajo.moverAbajo(fila, col)) {
                            abajo.setMov(abajo.getMatriz()[fila][col] + "N");
                            estadoActual.setHijos(abajo);
                            num_nodos++;
                            if (abajo.contarPosiciones() > this.estadoActual.getValor().contarPosiciones()) {
                                this.estadoActual = new Node(abajo);

                                return true;
                            }
                        }
                        generados.add(caso);
                    }
                }
                break;
                case 2: {
                    if (!buscarLista(generados, caso)) {
                        if (derecha.moverDer(fila, col)) {
                            derecha.setMov(derecha.getMatriz()[fila][col] + "O");
                            estadoActual.setHijos(derecha);
                            num_nodos++;
                            if (derecha.contarPosiciones() > this.estadoActual.getValor().contarPosiciones()) {
                                this.estadoActual = new Node(derecha);

                                return true;
                            }
                        }
                        generados.add(caso);
                    }
                }
                break;
                case 3: {
                    if (!buscarLista(generados, caso)) {
                        if (izquierda.moverIzq(fila, col)) {
                            izquierda.setMov(izquierda.getMatriz()[fila][col] + "E");
                            estadoActual.setHijos(izquierda);
                            num_nodos++;
                            if (izquierda.contarPosiciones() > this.estadoActual.getValor().contarPosiciones()) {
                                this.estadoActual = new Node(izquierda);

                                return true;
                            }
                        }
                        generados.add(caso);
                    }
                }
            }

        }
        return false; // No se ha encontrado ningun estado mejor que el actual
    }

    //Comprueba si un entero pertence a la lista
    boolean buscarLista(LinkedList<Integer> l, int caso) {
        boolean enc = false;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i) == caso) {
                return true;
            }
        }
        return enc;
    }

    //Genera los estados para los dos '0' del tablero.
    public void generarHijos(Node estadoActual) throws Exception {

        LinkedList<Integer> generados = new LinkedList<>();
        boolean fin = false;

        //Calcula posiciones de los 0 y las almacena en un vector.
        estadoActual.getValor().calcularZeros();
        int[] aux = estadoActual.getValor().getCoord();

        while (!fin && generados.size() != 2) {
            int caso = rand.nextInt(2);
            //Aleatoriamente expandimos primero por uno de los dos '0'
            switch (caso) {
                case 0: {
                    if (!buscarLista(generados, caso)) {
                        if (generarMovimientos(estadoActual, aux[0], aux[1])) {
                            fin = true;
                        }
                        generados.add(caso);
                    }
                }
                break;
                case 1: {
                    if (!buscarLista(generados, caso)) {
                        if (generarMovimientos(estadoActual, aux[2], aux[3])) {
                            fin = true;
                        }
                        generados.add(caso);
                    }
                }
            }
        }
    }

    // Imprime la solución
    public void printMovimientos() {
        String sol = "Solution:";
        for (int i = 0; i < this.movimientos.size(); i++) {
            sol = sol + " " + this.movimientos.get(i);
        }
        System.out.println(sol);
        System.out.println("Explored nodes: " + num_nodos);
    }

    public void setMovimientos(String mov) {
        this.movimientos.add(mov);
    }

    // Comprieba si el estado actual es mejor que el siguiente para continuar o no
    public boolean continuar(Node estadoActual, Node siguiente) {
        if (estadoActual.getH() < siguiente.getH()) {
            return true;
        } else {
            return false;
        }
    }


}


