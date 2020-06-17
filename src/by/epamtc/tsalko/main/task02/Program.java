package by.epamtc.tsalko.main.task02;

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
        int num;

        for (int i = 0; i < n; i++) {
            num = (i + 1) * (i + 2);
            matrix[i][i] = num;
        }

        return matrix;
    }
}
