import java.util.Scanner;

public class main {
    // Contains the number of puzzle to execute
    public static String puzzleNum = "1";

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        long starTime, endTime, totalTime;
        boolean exit = false;
        int num;

        System.out.println("===== PUZZLE SOLVER =====");
        System.out.println("Enter the number of the puzzle to solve (1 - 10)...");

        while (!exit) {
            while (!scanner.hasNextInt()) {
                System.out.println("The number is not correct...");
                scanner.next();
            }
            num = scanner.nextInt();
            if (num <= 10 && num > 0) {
                puzzleNum = Integer.toString(num);
                exit = true;
            } else {
                System.out.println("Puzzle not found...");
            }
        }

        exit = false;
        while (!exit) {
            System.out.println("\nEnter a number to execute an algorithm:" +
                    "\n1. Steepest ascent hill climbing" +
                    "\n2. Simple hill climbing" +
                    "\n3. Best-first search" +
                    "\n4. Exit");
            Scanner scanner1 = new Scanner(System.in);
            switch (scanner1.next()) {
                case "1": {
                    starTime = System.currentTimeMillis();
                    Algorithm_1 a1 = new Algorithm_1();
                    endTime = System.currentTimeMillis();
                    totalTime = endTime - starTime;
                    System.out.println("Time: " + totalTime + "ms");
                    System.out.println("\n");
                }
                break;
                case "2": {
                    starTime = System.currentTimeMillis();
                    Algorithm_2 a1 = new Algorithm_2();
                    endTime = System.currentTimeMillis();
                    totalTime = endTime - starTime;
                    System.out.println("Time: " + totalTime + "ms");
                    System.out.println("\n");
                }
                break;
                case "3": {
                    starTime = System.currentTimeMillis();
                    Algorithm_3 a1 = new Algorithm_3();
                    endTime = System.currentTimeMillis();
                    totalTime = endTime - starTime;
                    System.out.println("Time: " + totalTime + "ms");
                    System.out.println("\n");
                }
                break;

                case "4": {
                    exit = true;
                }
                break;

                default:
                    System.out.println("Algorithm not found...");
            }
        }
    }
}
