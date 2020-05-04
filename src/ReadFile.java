import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadFile {
    private int Matriz[][];
    private int size;

    public ReadFile(int _size) throws Exception {
        this.size = _size;
        Matriz = new int[size][size];
        LeerMatriz(main.puzzleNum);
    }

    private String readFileAsString(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    private void LeerMatriz(String puzzleNum) throws Exception {
        String data = readFileAsString("Puzzles\\puzzle"+puzzleNum+".txt").replace("\n", ",");
        String[] parts = data.split(",");
        int cont = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Matriz[i][j] = Integer.parseInt(parts[cont]);
                cont++;
            }
        }
    }

    public int[][] getMatriz() {
        return Matriz;
    }
}