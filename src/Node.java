import java.util.LinkedList;
import java.util.Random;

public class Node {

    private Board valor;
    private Random rand;
    private Node padre;
    private LinkedList<Node> hijos = new LinkedList<>();
    private int h;
    private LinkedList<Node> mejores = new LinkedList<>();
    private LinkedList<Node> mejores2 = new LinkedList<>();

    public Node(Board valor) throws Exception {
        this.valor = valor;
        this.h = this.valor.contarPosiciones();
    }

    public void setValor(Board valor) {
        this.valor = valor;
    }

    public Board getValor() {
        return valor;
    }

    public Node getPadre() {
        return padre;
    }

    public void setPadre(Node padre) {
        this.padre = padre;
    }

    public LinkedList<Node> getHijos() {
        return hijos;
    }

    public void setHijos(Board m) throws Exception {
        Node n = new Node(m);
        this.hijos.add(n);
    }

    public boolean compareMatrix(Board t, Board t2){
        boolean aux = true;
        for(int i = 0; i < t.getSize(); i++){
            for(int j = 0; j < t.getSize();j++){
                if(t.getMatriz()[i][j] != t2.getMatriz()[i][j]){
                    aux = false;
                }
            }
        }
        return aux;
    }

    public int getH() {
        return h;
    }

    public void setMejores(Board m) throws Exception {
        Node n = new Node(m);
        this.mejores.add(n);
    }

    public void setMejores2(Board m) throws Exception {
        Node n = new Node(m);
        this.mejores2.add(n);
    }

    public Node mejorCaso() throws Exception {
        int nodoMejor = 0;
        int cont = 0;
        int cont2 = 0;
        int pos = 0;
        int mejor = hijos.getFirst().getH();
        int mejor2 = hijos.getFirst().getH();
        for (int i = 1; i < hijos.size(); i++) {
            if (hijos.get(i).getH() > mejor) {
                mejor = hijos.get(i).getH();
                setMejores(hijos.get(i).getValor());
                nodoMejor = i;
                cont++;
            } else if (hijos.get(i).getH() == mejor2) {
                mejor2 = hijos.get(i).getH();
                setMejores2(hijos.get(i).getValor());
                cont2++;
            }
        }
        if (mejores.size() != 0) {
            rand = new Random();
            pos = rand.nextInt(cont);
            return this.mejores.get(pos);
        } else if (mejores2.size() != 0) {
            rand = new Random();
            pos = rand.nextInt(cont2);
            return this.mejores2.get(pos);
        } else {
            return hijos.get(nodoMejor);
        }
    }

    public Node mejorCasoS(){
        int nodoMejor = 0;
        int mejor = hijos.getFirst().getH();
        for (int i = 1; i < hijos.size(); i++) {
            if(hijos.get(i).getH() > mejor) {
                mejor = hijos.get(i).getH();
                nodoMejor = i;
            }
        }
        return this.hijos.get(nodoMejor);
    }

}


