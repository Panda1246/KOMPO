package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    SudokuBoard sudoku = new SudokuBoard();

    @Test
    void checkRows() {
        sudoku.fillBoard();
        byte valueCounter = 0;
        int[][] board = sudoku.getBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                valueCounter = 0;
                for (int z = 0; z < 9; z += 1) {
                    if (board[i][j] == board[i][z]) {
                        valueCounter += 1;
                    }
                    assertFalse(valueCounter > 1);

                }
            }
        }
    }


    @Test
    void checkColumns() {
        sudoku.fillBoard();
        int currentNumber = 0;
        byte valueCounter = 0;
        int[][] board = sudoku.getBoard();
        for (int i = 0; i < 9; i++) {
            currentNumber = board[0][i];
            for (int j = 1; j < 9; j++) {
                valueCounter = 0;
                for (int z = 0; z < 9; z += 1) {
                    if (board[j][i] == board[z][i]) {
                        valueCounter += 1;
                    }
                    assertFalse(valueCounter > 1);

                }
            }
        }
    }

    @Test
    void checkNumbersIntegrity() {
        sudoku.fillBoard();
        int[][] board = sudoku.getBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertFalse(board[i][j] > 9 || board[i][j] < 1);
            }
        }
    }

    @Test
    void checkIsBoardGeneratedRandomly() {
        sudoku.fillBoard();
        int[][] board = sudoku.getBoard();
        sudoku.fillBoard();
        int[][] secondBoard = sudoku.getBoard();
        assertFalse(Arrays.deepEquals(board, secondBoard));
    }

    @Test
    void squareCheck() {
        sudoku.fillBoard();
        int[][] board = sudoku.getBoard();
        int currentValue = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                currentValue = board[i][j];
                assertTrue(validateSquare(i, j, currentValue, board));
            }
        }
    }

     boolean validateSquare(int row, int col, int value, int[][] board) {
        int[][] squareCoordinates = new int[9][2];
        if (col >= 0 && col <= 2 && row >= 0 && row <= 2) {
            squareCoordinates = setSquareCoordinates(0, 2, 0, 2);
        } else if (col >= 3 && col <= 5 && row >= 0 && row <= 2) {
            squareCoordinates = setSquareCoordinates(0, 2, 3, 5);
        } else if (col >= 6 && col <= 8 && row >= 0 && row <= 2) {
            squareCoordinates = setSquareCoordinates(0, 2, 6, 8);
        } else if (col >= 0 && col <= 2 && row >= 3 && row <= 5) {
            squareCoordinates = setSquareCoordinates(3, 5, 0, 2);
        } else if (col >= 3 && col <= 5 && row >= 3 && row <= 5) {
            squareCoordinates = setSquareCoordinates(3, 5, 3, 5);
        } else if (col >= 6 && col <= 8 && row >= 3 && row <= 5) {
            squareCoordinates = setSquareCoordinates(3, 5, 6, 8);
        } else if (col >= 0 && col <= 2 && row >= 6 && row <= 8) {
            squareCoordinates = setSquareCoordinates(6, 8, 0, 2);
        } else if (col >= 3 && col <= 5 && row >= 6 && row <= 8) {
            squareCoordinates = setSquareCoordinates(6, 8, 3, 5);
        } else if (col >= 6 && col <= 8 && row >= 6 && row <= 8) {
            squareCoordinates = setSquareCoordinates(6, 8, 6, 8);
        }
        byte valueCounter = 0;
        for (int i = 0, j = 0; i < 9; i += 1) {
            if ((board[squareCoordinates[i][j]][squareCoordinates[i][j + 1]] == value)) {
                valueCounter++;
                if (valueCounter > 1) {
                    return false;
                }
            }
        }
        return true;
    }

     int[][] setSquareCoordinates(int minRow, int maxRow, int minCol, int maxCol) {
        int[][] squareCoordinates = new int[9][2];
        int i = 0;
        while (i < 9) {
            for (int currRow = minRow, currCol = minCol; currRow <= maxRow; currRow += 1) {
                squareCoordinates[i][0] = currRow;
                squareCoordinates[i++][1] = currCol;
            }
            minCol += 1;
        }
        return squareCoordinates;
    }
}