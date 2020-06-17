package by.epamtc.tsalko.main.task05;

public class Main {

    public static void main(String[] args) {
        Program program = new Program();

        // random values
        int[][] firstMatrix = {
                {2, 4, 3, 5, 1, 6},
                {3, 9, 7, 4, 2, 8},
                {1, 7, 4, 2, 5, 3},
                {6, 9, 5, 4, 7, 8},
                {5, 1, 2, 5, 4, 9},
                {3, 1, 1, 8, 4, 7}
        };

        int[][] secondMatrix = {
                {5, 1, 6, 4, 8, 9},
                {8, 7, 6, 1, 2, 6},
                {4, 5, 6, 8, 2, 3},
                {1, 2, 4, 8, 3, 6},
                {4, 5, 8, 7, 9, 5},
                {2, 3, 5, 4, 7, 9}
        };

        program.showMatrixMultiplication(firstMatrix, secondMatrix);
    }
}
