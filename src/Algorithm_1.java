import java.util.ArrayList;

public class Algorithm_1 {

    private Board m;
    private Tree a;
    private Node estadoActual;
    private ArrayList<String> movimientos;
    private int num_nodos = 0;
  //  private ArrayList<Nodo> visitados;

    public Algorithm_1() throws Exception {
        movimientos = new ArrayList<>();
        //visitados = new ArrayList<>();

        this.m = new Board();
        this.a = new Tree(m);
        estadoActual = new Node(this.a.getRaiz().getValor());
        estadoActual.getValor().mostrarMatriz();
       // visitados.add(this.estadoActual);
        expandir(estadoActual);
    }

    public void expandir( Node estadoActual) throws Exception {
        generarHijos(estadoActual);
        Node siguiente = estadoActual.mejorCaso();
        setMovimientos(estadoActual.getValor().getMov());

        if(estadoActual.getValor().contarPosiciones() != 14) {
            if(continuar(estadoActual,siguiente)){
                expandir(siguiente);
            }
        }else{ //En caso de haber hallado una solucion , se muestran los movimientos realizados y el tablero resultante
            printMovimientos();
            estadoActual.getValor().mostrarMatriz();
        }
    }

    public boolean continuar(Node estadoActual, Node siguiente){
        if(estadoActual.getH() <= siguiente.getH()){
            return true;
        }else{
            return false;
        }
    }

    public void generarHijos(Node estadoActual) throws Exception {
        estadoActual.getValor().calcularZeros(); //Calcula posicion de los 0
        int[] aux = estadoActual.getValor().getCoord();
        generarMovimientos(estadoActual,aux[0],aux[1]);
        generarMovimientos(estadoActual,aux[2],aux[3]);

    }

//    boolean commpruebaVisitado (Tablero t) {
//        boolean visitado = false;
//        for (int i = 0; i < visitados.size(); i++) {
//            if(visitados.get(i).getValor() == t) {
//                visitado = true;
//            }
//        }
//        return visitado;
//    }

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

                            estadoActual.setHijos(copia1);
                            num_nodos ++;
                    }
                    caso++;
                }break;
                case 1:{
                    copiaMatriz(estadoActual.getValor(), copia2);
                    if(copia2.moverAbajo(fila, col)){
                        copia2.setMov(copia2.getMatriz()[fila][col]+"N");
                            estadoActual.setHijos(copia2);
                            num_nodos ++;
                    }
                    caso++;
                }break;
                case 2:{
                    copiaMatriz(estadoActual.getValor(), copia3);
                    if(copia3.moverDer(fila, col)){
                        copia3.setMov(copia3.getMatriz()[fila][col]+"O");
                            estadoActual.setHijos(copia3);
                            num_nodos ++;
                    }
                    caso++;
                }break;
                case 3:{ copiaMatriz(estadoActual.getValor(), copia4);
                    if(copia4.moverIzq(fila, col)){
                        copia4.setMov(copia4.getMatriz()[fila][col]+"E");
                            estadoActual.setHijos(copia4);
                            num_nodos ++;
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
