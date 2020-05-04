
public class Tree {

    private Node raiz;

    public Tree(Board m) throws Exception {
        this.raiz = new Node( m );
    }

    public Tree(Node raiz ) {
        this.raiz = raiz;
    }

    public Node getRaiz() {
        return raiz;
    }

    public void setRaiz(Node raiz) {
        this.raiz = raiz;
    }

}