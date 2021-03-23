package main;

import javax.swing.*;
import java.util.Random;

public class GameOfFifteen {

    static int[][] matrix;
    static Window mainWindow = new Window(matrix);
    static int counter = 0;

    public static void main(String[] args) {

//        matrix = new int[][]{{1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}, {4, 8, 12, 0}};
//        matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 15, 0}, {13, 14, 12, 11}};
//        matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 0, 15}};
        matrix = generate();

        if (isSolvable(matrix)) {
            refresh(matrix);
        } else {
            main(null);
        }
    }

    protected static void play(int[][] matrix, int number) {

        if (gameOver(matrix)) {
            int[][] postMatrix = move(matrix, number);
            refresh(postMatrix);
            if (!gameOver(matrix)) {
                mainWindow.getContentPane().removeAll();
                mainWindow.paintEndScreen();
            }
        }
    }

    private static void refresh(int[][] matrix) {

        mainWindow.getContentPane().removeAll();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                JButton jButton = mainWindow.addButton(matrix[i][j], i, j);
                mainWindow.add(jButton);
                mainWindow.setMatrixGlob(matrix);
            }
        }
        mainWindow.repaint();
    }

    private static int[][] generate() {

        Random random = new Random();
        int[][] matrix = new int[4][4];
        boolean condition = true;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; ) {
                if (i == 3 && j == 3) {
                    matrix[3][3] = 0;
                    break;
                }
                int number = random.nextInt(15) + 1;
                for (int k = 3; k >= 0; k--) {
                    for (int l = 3; l >= 0; l--) {
                        if (matrix[k][l] == number) {
                            condition = false;
                            break;
                        }
                        if (!condition) {
                            break;
                        }
                    }
                }
                if (condition) {
                    matrix[i][j] = number;
                    j++;
                } else {
                    condition = true;
                }
            }
        }
        return matrix;
    }

    private static int[][] move(int[][] matrix, int number) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == number) {
                    try {
                        if (i > 0) {
                            if (matrix[i - 1][j] == 0) {
                                matrix[i][j] = 0;
                                matrix[i - 1][j] = number;
                                counter++;
                                return matrix;
                            }
                        }
                        if (i < 3) {
                            if (matrix[i + 1][j] == 0) {
                                matrix[i][j] = 0;
                                matrix[i + 1][j] = number;
                                counter++;
                                return matrix;
                            }
                        }
                        if (j > 0) {
                            if (matrix[i][j - 1] == 0) {
                                matrix[i][j] = 0;
                                matrix[i][j - 1] = number;
                                counter++;
                                return matrix;
                            }
                        }
                        if (j < 3) {
                            if (matrix[i][j + 1] == 0) {
                                matrix[i][j] = 0;
                                matrix[i][j + 1] = number;
                                counter++;
                                return matrix;
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("index error");
                    }
                }
            }
        }
        return matrix;
    }

    private static boolean gameOver(int[][] matrix) {

        int counter = 0;
        int memory = 0;

        for (int[] a : matrix) {
            for (int b : a) {
                if (memory < b) {
                    counter++;
                    if (counter == 15) {
//                        System.out.println("you win");
                        return false;
                    }
                }
                memory = b;
            }
        }
        return true;
    }

    private static boolean isSolvable(int[][] matrix) {

        int counter = 0;
        int temp = 0;

        for (int[] a : matrix) {
            for (int b : a) {
                if (temp < b) {
                    counter++;
                }
                temp = b;
            }
        }
        return counter % 2 == 0;
    }
}