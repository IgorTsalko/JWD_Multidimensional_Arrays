package by.epamtc.tsalko.main.task06;

public class MagicSquare {

    private int[][] magicSquare;

    public MagicSquare(int order) {
        magicSquare = createMagicSquare(order);
    }

    // Возрващает магический квадарат
    public int[][] createMagicSquare(int order) {
        if (order % 2 != 0) {
            // Если квадрат нечетный
            return fillOddMagicSquare(order, 1);
        } else if (order % 4 != 0) {
            // Если квадрат двойной четности
            return createSinglyEvenMagicSquare(order);
        } else {
            // Если квадрат одинарной четности
            return createDoublyEvenMagicSquare(order);
        }
    }

    /*
     Создает и возвращает нечетный магический квадрат
     Алгоритм решения взять отсюда: http://www.1728.org/magicsq1.htm
     */
    private int[][] fillOddMagicSquare(int order, int start) {
        int[][] oddMagicSquare = new int[order][order];
        int numberOfCells = order * order;
        int lineIndex = 0;
        int columnIndex = order / 2;
        int previousLineIndex = 0;
        int previousColumnIndex = 0;
        int startNumber = start;

        for (int i = 0; i < numberOfCells; i++) {
            if (lineIndex < 0) {
                lineIndex = order - 1;
            }
            if (columnIndex > order - 1) {
                columnIndex = 0;
            }

            if (oddMagicSquare[lineIndex][columnIndex] != 0) {
                lineIndex = previousLineIndex + 1;
                columnIndex = previousColumnIndex;
            }

            oddMagicSquare[lineIndex][columnIndex] = startNumber++;

            previousLineIndex = lineIndex--;
            previousColumnIndex = columnIndex++;
        }

        return oddMagicSquare;
    }

    /*
    Создает и возвращает магический квадарт одинарной четности
    Алгоритм решения взять отсюда: http://www.1728.org/magicsq3.htm
     */
    private int[][] createSinglyEvenMagicSquare(int order) {
        int[][] singlyEvenMagicSquare = new int[order][order];

        // Заполняем 4 части матрицы как нечетные магические квадарта
        fillFourOddParts(singlyEvenMagicSquare, order);

        // Исправляем неточности в заполнении
        correctSquare(singlyEvenMagicSquare);

        return singlyEvenMagicSquare;
    }

    /*
     Согласно алгоритму делим магический квадарт на части, и получаем
     4 квадарата нечетного порядка, далее заполняем их
     */
    private void fillFourOddParts(int[][] square, int order) {
        int numberOfCells = order * order;
        int lineStart = 0;
        int columnStart = 0;
        int numStart = 1;
        int orderPartOfSquare = order / 2;
        int[][] oddPartOfSquare;

        // Заполняем квадрат A
        oddPartOfSquare = fillOddMagicSquare(orderPartOfSquare, numStart);
        copyMatrixToMatrix(square, oddPartOfSquare, lineStart, columnStart);

        // Заполняем квадрат B
        numStart = numberOfCells / 4 + 1;
        oddPartOfSquare = fillOddMagicSquare(orderPartOfSquare, numStart);
        lineStart = order / 2;
        columnStart = order / 2;
        copyMatrixToMatrix(square, oddPartOfSquare, lineStart, columnStart);

        // Заполняем квадрат С
        numStart = numberOfCells / 2 + 1;
        oddPartOfSquare = fillOddMagicSquare(orderPartOfSquare, numStart);
        lineStart = 0;
        copyMatrixToMatrix(square, oddPartOfSquare, lineStart, columnStart);

        // Заполняем квадрат D
        numStart = numberOfCells - numberOfCells / 4 + 1;
        oddPartOfSquare = fillOddMagicSquare(orderPartOfSquare, numStart);
        lineStart = order / 2;
        columnStart = 0;
        copyMatrixToMatrix(square, oddPartOfSquare, lineStart, columnStart);
    }

    // Копируем одну матрицу в другую в указаные ячейки
    private void copyMatrixToMatrix(int[][] target, int[][] source, int lineStart, int columnStart) {
        for (int i = 0, k = lineStart; i < source.length; i++, k++) {
            for (int j = 0, l = columnStart; j < source[i].length; j++, l++) {
                target[k][l] = source[i][j];
            }
        }
    }

    /*
    Согласно алгоритму после заполнения 4-ех частей целого квадарата,
    нужно поменять местами некоторые числа между квадратами A и D
     */
    private void correctSquare(int[][] square) {
        /*
         Если квадрат порядка 6, числа для замены всегда в одинаковых ячейках,
         поэтому меняем без алгоритма
         */
        if (square.length == 6) {
            int subSquareA1 = square[0][0];
            int subSquareA2 = square[1][1];
            int subSquareA3 = square[2][0];

            square[0][0] = square[3][0];
            square[1][1] = square[4][1];
            square[2][0] = square[5][0];

            square[3][0] = subSquareA1;
            square[4][1] = subSquareA2;
            square[5][0] = subSquareA3;
        } else if (square.length > 6) {
            int[][] subSquareA1 = createSubSquareA1(square);
            int[] subSquareA2 = createSubSquareA2(square, subSquareA1);
            int[][] subSquareA3 = createSubSquareA3(square, subSquareA1);

            replacePartDToPartA(square, subSquareA1);
            copyPartAToPartD(square, subSquareA1, subSquareA2, subSquareA3);

            correctLastColumns(square);
        }
    }

    private int[][] createSubSquareA1(int[][] square) {
        int[][] subSquareA1;
        int median = square.length/2/2;
        subSquareA1 = new int[median][median];
        for (int i = 0; i < subSquareA1.length; i++) {
            System.arraycopy(square[i], 0,
                    subSquareA1[i], 0, subSquareA1[i].length);
        }
        return subSquareA1;
    }

    private int[] createSubSquareA2(int[][] square, int[][] subSquareA1) {
        int[] subSquareA2 = new int[subSquareA1.length];
        System.arraycopy(square[subSquareA1.length], 1,
                subSquareA2, 0, subSquareA2.length);
        return subSquareA2;
    }

    private int[][] createSubSquareA3(int[][] square, int[][] subSquareA1) {
        int[][] subSquareA3 = new int[subSquareA1.length][subSquareA1.length];
        for (int i = 0; i < subSquareA1.length; i++) {
            System.arraycopy(square[i + subSquareA1.length + 1], 0,
                    subSquareA3[i], 0, subSquareA1.length);
        }
        return subSquareA3;
    }

    private void replacePartDToPartA(int[][] square, int[][] subSquareA1) {
        int half = square.length / 2;
        for (int i = 0, k = i + subSquareA1.length + 1; i < subSquareA1.length; i++, k++) {
            for (int j = 0; j < subSquareA1.length; j++) {
                square[i][j] = square[i + half][j];
                square[k][j] = square[k + half][j];
            }
        }
        System.arraycopy(square[subSquareA1.length + half], 1,
                square[subSquareA1.length], 1, subSquareA1.length);
    }

    private void copyPartAToPartD(int[][] square, int[][] subSquareA1, int[] subSquareA2, int[][] subSquareA3) {
        int half = square.length / 2;
        for (int i = 0, k = i + subSquareA1.length + 1; i < subSquareA1.length; i++, k++) {
            for (int j = 0; j < subSquareA1.length; j++) {
                square[i + half][j] = subSquareA1[i][j];
                square[k + half][j] = subSquareA3[i][j];
            }
        }
        System.arraycopy(subSquareA2, 0, square[subSquareA1.length + half],
                1, subSquareA1.length);
    }

    private void correctLastColumns(int[][] square) {
        int columnWight = (square.length - 6) / 4;
        int copyNum;
        int j = square.length / 2;
        int k = square.length - columnWight;

        if (columnWight == 1) {
            for (int i = 0; i < square.length / 2; i++, j++) {
                copyNum = square[i][k];
                square[i][k] = square[j][k];
                square[j][k] = copyNum;
            }
        } else {
            for (int i = 0; i < columnWight; i++) {
                for (int l = 0; l < square.length / 2; l++, j++) {
                    copyNum = square[l][k];
                    square[l][k] = square[j][k];
                    square[j][k] = copyNum;
                }
                k--;
                j = square.length/2;
            }
        }
    }

    /*
    Создает и возвращает магический квадарт двойной четности
    Алгоритм решения взять отсюда: http://www.1728.org/magicsq2.htm
     */
    private int[][] createDoublyEvenMagicSquare(int order) {
        int[][] doublyEvenMagicSquare = new int[order][order];
        // Создаем массив с координатами для заполнения
        int[][] coordinates;

        // Устанавливаем координаты для первого прохода
        coordinates = createCoordinatesForFirstPass(order);
        // Заполняем полученные координаты значениями
        fillValuesOfFirstPass(doublyEvenMagicSquare, coordinates);

        // Устанавливаем координаты для второго прохода
        coordinates = createCoordinatesForSecondPass(order);
        // Заполняем полученные координаты значениями
        fillValuesOfSecondPass(doublyEvenMagicSquare, coordinates);

        return doublyEvenMagicSquare;
    }

    private int[][] createCoordinatesForFirstPass(int order) {
        int[][] coordinates = new int[(order * order) / 2][2];
        int coordinatesIndex = 0;
        int sideOfCornerSquare = order / 4;

        // 4-мя циклами заполняем координаты четыреъ угловых квадартов
        for (int i = 0; i < sideOfCornerSquare; i++) {
            for (int j = 0; j < sideOfCornerSquare; j++) {
                coordinates[coordinatesIndex] = new int[]{i, j};
                coordinatesIndex++;
            }
        }

        for (int i = 0; i < sideOfCornerSquare; i++) {
            for (int j = order - sideOfCornerSquare; j < order; j++) {
                coordinates[coordinatesIndex] = new int[]{i, j};
                coordinatesIndex++;
            }
        }

        for (int i = order - sideOfCornerSquare; i < order; i++) {
            for (int j = 0; j < sideOfCornerSquare; j++) {
                coordinates[coordinatesIndex] = new int[]{i, j};
                coordinatesIndex++;
            }
        }

        for (int i = order - sideOfCornerSquare; i < order; i++) {
            for (int j = order - sideOfCornerSquare; j < order; j++) {
                coordinates[coordinatesIndex] = new int[]{i, j};
                coordinatesIndex++;
            }
        }

        // Заполняем один центральный квадрат
        for (int i = sideOfCornerSquare; i < sideOfCornerSquare * 3; i++) {
            for (int j = sideOfCornerSquare; j < sideOfCornerSquare * 3; j++) {
                coordinates[coordinatesIndex] = new int[]{i, j};
                coordinatesIndex++;
            }
        }

        return coordinates;
    }

    private int[][] createCoordinatesForSecondPass(int order) {
        int[][] coordinates = new int[(order * order) / 2][2];
        int coordinatesIndex = 0;
        int sideOfCornerSquare = order / 4;

        // 4-мя циклами заполняем координаты 4-ех оставшихся прямоугольников
        for (int i = 0; i < sideOfCornerSquare; i++) {
            for (int j = sideOfCornerSquare; j < order - sideOfCornerSquare; j++) {
                coordinates[coordinatesIndex] = new int[]{i, j};
                coordinatesIndex++;
            }
        }

        for (int i = sideOfCornerSquare; i < order - sideOfCornerSquare; i++) {
            for (int j = 0; j < sideOfCornerSquare; j++) {
                coordinates[coordinatesIndex] = new int[]{i, j};
                coordinatesIndex++;
            }
        }

        for (int i = sideOfCornerSquare; i < order - sideOfCornerSquare; i++) {
            for (int j = order - sideOfCornerSquare; j < order; j++) {
                coordinates[coordinatesIndex] = new int[]{i, j};
                coordinatesIndex++;
            }
        }

        for (int i = order - sideOfCornerSquare; i < order; i++) {
            for (int j = sideOfCornerSquare; j < order - sideOfCornerSquare; j++) {
                coordinates[coordinatesIndex] = new int[]{i, j};
                coordinatesIndex++;
            }
        }

        return coordinates;
    }

    private void fillValuesOfFirstPass(int[][] doublyEvenMagicSquare, int[][] coordinates) {
        int count = 1;
        for (int i = 0; i < doublyEvenMagicSquare.length; i++) {
            for (int j = 0; j < doublyEvenMagicSquare.length; j++) {
                if (checkCoordinate(i, j, coordinates)) {
                    doublyEvenMagicSquare[i][j] = count;
                }
                count++;
            }
        }
    }

    private void fillValuesOfSecondPass(int[][] doublyEvenMagicSquare, int[][] coordinates) {
        int count = doublyEvenMagicSquare.length * doublyEvenMagicSquare.length;
        for (int i = 0; i < doublyEvenMagicSquare.length; i++) {
            for (int j = 0; j < doublyEvenMagicSquare.length; j++) {
                if (checkCoordinate(i, j, coordinates)) {
                    doublyEvenMagicSquare[i][j] = count;
                }
                count--;
            }
        }
    }

    private boolean checkCoordinate(int i, int j, int[][] coordinates) {
        for (int[] coordinate : coordinates) {
            if (coordinate[0] == i && coordinate[1] == j) {
                return true;
            }
        }
        return false;
    }

    // Вывод магического квадрата на консоль
    public void printMagicSquare() {
        StringBuilder square = new StringBuilder();
        for (int[] line : magicSquare) {
            int sumOfLine = sumOfLine(line);
            for (int num : line) {
                square.append(String.format("%4d", num));
            }
            square.append(" ― ")
                    .append(sumOfLine)
                    .append("\n");
        }
        // Добавляем последнюю строку (Вывод сумм каждого столбца)
        square.append(sumOfColumns());

        System.out.print(square);
    }

    // Считает сумму всех значений в строке
    private int sumOfLine(int[] line) {
        int sum = 0;
        for (int value : line) {
            sum += value;
        }
        return sum;
    }

    // Считает сумму всех значений в столбце
    private String sumOfColumns() {
        StringBuilder builder = new StringBuilder(" ");
        builder.append("  | ".repeat(magicSquare.length))
                .append("\n")
                .append("  ");
        for (int i = 0; i < magicSquare.length; i++) {
            int sum = 0;
            for (int j = 0; j < magicSquare[i].length; j++) {
                sum += magicSquare[j][i];
            }
            builder.append(String.format("%2d", sum)).append(" ");
        }
        return builder.toString();
    }
}