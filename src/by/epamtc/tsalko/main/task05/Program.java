package by.epamtc.tsalko.main.task05;

public class Program {

    public void showMatrixMultiplication(int[][] firstMatrix, int[][] secondMatrix) {
        StringBuilder result = new StringBuilder();
        int[][] multipliedMatrix;
        multipliedMatrix = multiplyMatrix(firstMatrix, secondMatrix);

        for (int[] line : multipliedMatrix) {
            for (int num : line) {
                result.append(String.format("%4d", num));
            }
            result.append("\n");
        }

        System.out.print(result);
    }

    private int[][] multiplyMatrix(int[][] firstMatrix, int[][] secondMatrix) {
        int[][] multipliedMatrix = new int[firstMatrix.length][firstMatrix[0].length];

        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < firstMatrix[i].length; j++) {
                int numFromFirstMatrix = firstMatrix[i][j];
                int numFromSecondMatrix = secondMatrix[i][j];

                multipliedMatrix[i][j] = numFromFirstMatrix * numFromSecondMatrix;
            }
        }

        return multipliedMatrix;
    }
}
