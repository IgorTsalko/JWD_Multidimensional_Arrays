package by.epamtc.tsalko.main.task04;

public class Program {

    public void showSquareMatrix(double[] arr) {
        StringBuilder result = new StringBuilder();
        double[][] matrix;
        matrix = formMatrix(arr);

        for (double[] line : matrix) {
            for (double num : line) {
                result.append(String.format("%8.2f", num));
            }
            result.append("\n");
        }

        System.out.print(result);
    }

    private double[][] formMatrix(double[] arr) {
        double[][] squareMatrix = new double[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                squareMatrix[i][j] = Math.pow(arr[j], i + 1);
            }
        }

        return squareMatrix;
    }
}
