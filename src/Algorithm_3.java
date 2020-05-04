import java.util.*;

public class Algorithm_3 {

    private Tree a;
    private Board m;
    private Node raiz;
    private ArrayList<String> movimientos;
    private int num_nodos = 0;
    private PriorityQueue<Node> Abiertos;
    private LinkedList<Node> Cerrados;
    private int negados = 0;
    private int seguidos = 0;

    private boolean continuar = true;
    Random rand = new Random();

    public Algorithm_3() throws Exception {
        movimientos = new ArrayList<>();
        Abiertos = new PriorityQueue<>(Comparator.comparing(Node::getH).reversed());
        Cerrados = new LinkedList<>();
        this.m = new Board();
        this.a = new Tree(m);
        raiz = new Node(this.a.getRaiz().getValor());
        raiz.getValor().mostrarMatriz();
        Abiertos.add(raiz);
        expandir();
    }

    public void expandir() throws Exception {
        Node mejor = Abiertos.poll();

        if (!Cerrados.isEmpty()) {
            if (!continuar) {
                movimientos.remove(mejor.getValor().getMov());
            }
        }

        Cerrados.add(mejor);
        generarHijos(mejor);
        setMovimientos(mejor.getValor().getMov());
        if (mejor.getH() != 14) {
            if (continuar && !Abiertos.isEmpty()) {
                expandir();
            } else {
                System.out.println("A better state was no found. CURRENT STATE: \n");
                mejor.getValor().mostrarMatriz();
                System.out.println("Explored Nodes: " + num_nodos);
            }
        } else { //En caso de haber hallado una solucion , se muestran los movimientos realizados y el tablero resultante
            System.out.println("Final Board: ");
            mejor.getValor().mostrarMatriz();
            printMovimientos();
        }
    }

    public void generarHijos(Node estadoActual) throws Exception {
        negados = 0;
        estadoActual.getValor().calcularZeros();
        int[] aux = estadoActual.getValor().getCoord();
        generarMovimientos(estadoActual, aux[0], aux[1]);
        generarMovimientos(estadoActual, aux[2], aux[3]);
        if (negados == 8) {
            continuar = false;
        }
    }


    public boolean anadirLista(Board t) throws Exception {
        boolean anado = true;
        for (Node n : Abiertos) {
            if (n.compareMatrix(n.getValor(), t)) {
                anado = false;
            }
        }
        for (Node n : Cerrados) {
            if (n.compareMatrix(n.getValor(), t)) {
                anado = false;
            }
        }
        return anado;
    }

    public void generarMovimientos(Node estadoActual, int fila, int col) throws Exception {
        int caso = 0;
        boolean fin = false;
        Board copia1 = new Board();
        Board copia2 = new Board();
        Board copia3 = new Board();
        Board copia4 = new Board();
        while (!fin) {
            switch (caso) {
                case 0: {
                    copiaMatriz(estadoActual.getValor(), copia1);
                    if (copia1.moverArriba(fila, col)) {
                        copia1.setMov(copia1.getMatriz()[fila][col] + "S");
                        if (anadirLista(copia1)) {
                            estadoActual.setHijos(copia1);
                            num_nodos++;
                        } else {
                            negados++;
                        }
                    } else {
                        negados++;
                    }
                    caso++;
                }
                break;
                case 1: {
                    copiaMatriz(estadoActual.getValor(), copia2);
                    if (copia2.moverAbajo(fila, col)) {
                        copia2.setMov(copia2.getMatriz()[fila][col] + "N");
                        if (anadirLista(copia2)) {
                            estadoActual.setHijos(copia2);
                            num_nodos++;
                        } else {
                            negados++;
                        }
                    } else {
                        negados++;
                    }
                    caso++;
                }
                break;
                case 2: {
                    copiaMatriz(estadoActual.getValor(), copia3);
                    if (copia3.moverDer(fila, col)) {
                        copia3.setMov(copia3.getMatriz()[fila][col] + "O");
                        if (anadirLista(copia3)) {
                            estadoActual.setHijos(copia3);
                            num_nodos++;
                        } else {
                            negados++;
                        }
                    } else {
                        negados++;
                    }
                    caso++;
                }
                break;
                case 3: {
                    copiaMatriz(estadoActual.getValor(), copia4);
                    if (copia4.moverIzq(fila, col)) {
                        copia4.setMov(copia4.getMatriz()[fila][col] + "E");
                        if (anadirLista(copia4)) {
                            estadoActual.setHijos(copia4);
                            num_nodos++;
                        } else {
                            negados++;
                        }
                    } else {
                        negados++;
                    }
                    caso++;
                    fin = true;
                }
                break;
                default:
                    throw new IllegalStateException("Unexpected value: " + caso);
            }
        }
        while (!estadoActual.getHijos().isEmpty()) {
            Abiertos.add(estadoActual.getHijos().pollFirst());
        }
    }

    public void copiaMatriz(Board original, Board copia) {
        for (int i = 0; i < original.getSize(); i++) {
            for (int j = 0; j < original.getSize(); j++) {
                copia.getMatriz()[i][j] = original.getMatriz()[i][j];
            }
        }
    }

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
}
