package by.epamtc.tsalko.main.task03;

public class Program {

    public void showFormedMatrix(int n) {
        StringBuilder result = new StringBuilder();
        int[][] matrix;
        matrix = formMatrix(n);

        for (int[] line : matrix) {
            for (int num : line) {
                result.append(String.format("%3d", num));
            }
            result.append("\n");
        }

        System.out.print(result);
    }

    private int[][] formMatrix(int n) {
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n - i; j++) {
                matrix[i][j] = 1;
                matrix[n - i - 1][j] = 1;
            }
        }

        return matrix;
    }
}
