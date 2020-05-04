import java.util.*;

public class Algorithm_3 {

    private Tree a;
    private Board m;
    private Node raiz;
    private ArrayList<String> movimientos;
    private int num_nodos = 0;
    private PriorityQueue<Node> Abiertos;
    private LinkedList<Node> Cerrados;

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

    public void expandir() throws Exception{

        Node mejor = Abiertos.poll();
       // System.out.println(mejor.getH());
        //System.out.println(mejor.getValor().getMov());

        Cerrados.add(mejor);
        generarHijos(mejor);
        setMovimientos(mejor.getValor().getMov());
        if (mejor.getH() != 14) {
            expandir();
        } else { //En caso de haber hallado una solucion , se muestran los movimientos realizados y el tablero resultante
            printMovimientos();
            mejor.getValor().mostrarMatriz();
        }
    }
    public void generarHijos(Node estadoActual) throws Exception {
        estadoActual.getValor().calcularZeros();
        int[] aux = estadoActual.getValor().getCoord();
        generarMovimientos(estadoActual,aux[0],aux[1]);
        generarMovimientos(estadoActual,aux[2],aux[3]);

    }


    public void anadirListas(Board t) throws Exception {
        Node n = new Node(t);
        if(!Abiertos.contains(n) && !Cerrados.contains(n)){
            Abiertos.add(n);
        }
    }

    public void generarMovimientos(Node estadoActual, int fila, int col) throws Exception {
        int caso = 0;
        boolean fin = false;
        Board copia1 = new Board();
        Board copia2= new Board();
        Board copia3= new Board();
        Board copia4= new Board();
        while(!fin){
            switch (caso){
                case 0:{
                    copiaMatriz(estadoActual.getValor(), copia1);
                    if(copia1.moverArriba(fila, col)){
                        copia1.setMov(copia1.getMatriz()[fila][col]+"S");
                        anadirListas(copia1);
                        estadoActual.setHijos(copia1);
                        num_nodos++;

                    }
                    caso++;
                }break;
                case 1:{
                    copiaMatriz(estadoActual.getValor(), copia2);
                    if(copia2.moverAbajo(fila, col)){
                        copia2.setMov(copia2.getMatriz()[fila][col]+"N");
                        anadirListas(copia2);
                        estadoActual.setHijos(copia2);
                        num_nodos++;
                    }
                    caso++;
                }break;
                case 2:{
                    copiaMatriz(estadoActual.getValor(), copia3);
                    if(copia3.moverDer(fila, col)){
                        copia3.setMov(copia3.getMatriz()[fila][col]+"O");
                        anadirListas(copia3);
                        estadoActual.setHijos(copia3);
                        num_nodos++;
                    }
                    caso++;
                }break;
                case 3:{ copiaMatriz(estadoActual.getValor(), copia4);
                    if(copia4.moverIzq(fila, col)){
                        copia4.setMov(copia4.getMatriz()[fila][col]+"E");
                        anadirListas(copia4);
                        estadoActual.setHijos(copia4);
                        num_nodos++;
                    }
                    caso++;
                    fin = true;
                }
                break;
                default:
                    throw new IllegalStateException("Unexpected value: " + caso);
            }
        }
    }

    public void copiaMatriz(Board original, Board copia){
        for(int i = 0; i < original.getSize(); i++){
            for(int j = 0; j < original.getSize(); j++){
                copia.getMatriz()[i][j] = original.getMatriz()[i][j];
            }
        }
    }

    public void printMovimientos() {
        String sol = "SOLUCION:";
        for(int i = 0; i < this.movimientos.size(); i++){
            sol = sol + " " + this.movimientos.get(i);
        }
        System.out.println(sol);
        System.out.println("NODOS EXPLORADOS: " + num_nodos);
    }

    public void setMovimientos(String mov) {
        this.movimientos.add(mov);
    }
}
