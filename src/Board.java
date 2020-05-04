public class Board {

    // Atributos

    private int[][] matriz; //Matriz Principal
    private int size = 4;
    private int[] coord; // Vector que contiene la posicion de los dos '0'
    private String mov; // Utilizado para mostrar graficamente el ultimo movimiento realizado

    // Constructor

    public Board() throws Exception {
        ReadFile l = new ReadFile(size); // Carga los datos del fichero en la matriz.
        this.matriz = l.getMatriz();
        this.coord = new int[]{0,0,0,0};
        mov = " ";
    }

    public String getMov() {
        return mov;
    }

    public void setMov(String mov) {
        this.mov = mov;
    }

    public int[] getCoord() {
        return coord;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void mostrarMatriz() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(this.matriz[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println("\n");
    }

    // Comprueba si se puede mover a una casilla dependiendo de si esta es 0 o no
    public boolean permisoMover(int f, int c){
        if(this.matriz[f][c] != 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean moverArriba(int f, int c) {
        if (f > 0 && permisoMover(f - 1, c)) {
            int aux = this.matriz[f][c];
            this.matriz[f][c] = this.matriz[f-1][c];
            this.matriz[f-1][c] = aux;
            return true;
        }else{
            return false;
        }
    }

    public boolean moverAbajo(int f, int c){
        if(f < this.size-1 && permisoMover(f + 1, c)){
            int aux = this.matriz[f][c];
            this.matriz[f][c] = this.matriz[f+1][c];
            this.matriz[f+1][c] = aux;
            return true;
        }else{
            return false;
        }
    }

    public boolean moverIzq(int f, int c){
        if(c > 0 && permisoMover(f,c-1)){
            int aux = this.matriz[f][c];
            this.matriz[f][c] = this.matriz[f][c-1];
            this.matriz[f][c-1] = aux;
            return true;
        }else{
            return false;
        }
    }

    public boolean moverDer(int f, int c){
        if(c < this.size-1 && permisoMover(f, c+1)){
            int aux = this.matriz[f][c];
            this.matriz[f][c] = this.matriz[f][c+1];
            this.matriz[f][c+1] = aux;
            return true;
        }else{
            return false;
        }
    }

    public int contarPosiciones(){
        int counter = 0;
        int dato = 1;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                    if(this.matriz[i][j] == dato)
                        counter++;
                dato++;
            }
        }
        return counter;
    }

    // Calcula las posiciones de los dos '0'
    public void calcularZeros(){
        int pos = 0;
        for(int i = 0; i < this.size && pos < 4; i++){
            for(int j = 0; j < this.size && pos < 4; j++){
                if(this.matriz[i][j] == 0){
                    this.coord[pos] = i;
                    this.coord[pos+1] = j;
                    pos = pos + 2;
                }
            }
        }
    }

}
