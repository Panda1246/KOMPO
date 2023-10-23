package org.example;

import org.example.interfaces.SudokuSolver;
import org.example.models.SudokuBoard;
import org.example.solvers.BacktrackingSudokuSolver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
    SudokuBoard sudoku = new SudokuBoard(sudokuSolver);
    byte valueCounter = 0;

    //Checking rows, columns, number integrity and squares of THE SAME BOARD
    @Test
    void complexBoardTest() {
        sudoku.solveGame();
        int[][] board = sudoku.getBoard();
        checkRows(board);
        checkCols(board);
        checkNumersIntegrity(board);
        squareCheck(board);
    }

    @Test
    void checkIsBoardGeneratedRandomly() {
        sudoku.solveGame();
        int[][] board = sudoku.getBoard();
        sudoku.solveGame();
        int[][] secondBoard = sudoku.getBoard();
        assertFalse(Arrays.deepEquals(board, secondBoard), "Boards are not unique");
    }

    @Test
    void checkValueSetter() {
        sudoku.solveGame();
        int exampleValue = ThreadLocalRandom.current().nextInt(1, 9);
        sudoku.setValue(4, 4, exampleValue);
        int[][] currentBoard = sudoku.getBoard();
        assertEquals(currentBoard[4][4], exampleValue);
    }

    @Test
    void checkValueGetter() {
        sudoku.solveGame();
        int[][] currentBoard = sudoku.getBoard();
        assertEquals(currentBoard[7][3], sudoku.getValue(7, 3));
    }

    void checkRows(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                valueCounter = 0;
                for (int z = 0; z < 9; z++) {
                    if (board[i][j] == board[i][z]) {
                        valueCounter += 1;
                    }
                    assertFalse(valueCounter > 1, "Row error");

                }
            }
        }
    }

    void checkCols(int[][] board) {
           for (int i = 0; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                valueCounter = 0;
                for (int z = 0; z < 9; z += 1) {
                    if (board[j][i] == board[z][i]) {
                        valueCounter += 1;
                    }
                    assertFalse(valueCounter > 1, "Column error");

                }
            }
        }
    }

    void checkNumersIntegrity(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertFalse(board[i][j] > 9 || board[i][j] < 1, "Number integrity error");
            }
        }
    }

    private void squareCheck(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertTrue(validateSquare(i, j, board[i][j], board), "Square duplicate number error");
            }
        }
    }

    private boolean validateSquare(int row, int col, int value, int[][] board) {
        int topLeftRow = row - (row % 3);
        int topLeftCol = col - (col % 3);
        int currentValue = 0;
        for (int i = topLeftRow; i < topLeftRow + 3; i++) {
            for (int j = topLeftCol; j < topLeftCol + 3; j++) {
                if (currentValue > 1) return false; 
                if (board[i][j] == value) currentValue++;
            }
        }
        return true;
    }
}