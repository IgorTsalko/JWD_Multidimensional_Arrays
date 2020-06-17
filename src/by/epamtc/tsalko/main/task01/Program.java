package by.epamtc.tsalko.main.task01;

public class Program {
    
    public void showFormedMatrix(int n) {
        StringBuilder result = new StringBuilder();
        int[][] matrix;
        matrix = formMatrix(n);

        for (int[] line : matrix) {
            for (int num : line) {
                result.append(num).append("  ");
            }
            result.append("\n");
        }

        System.out.print(result);
    }
    
    private int[][] formMatrix(int n) {
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i += 2) {
            int k = n - 1;
            for (int j = 0; j < n; j++) {
                matrix[i][j] = j + 1;
                matrix[i + 1][k--] = j + 1;
            }
        }

        return matrix;
    }
}
