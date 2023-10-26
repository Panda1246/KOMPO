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
        checkRows(sudoku);
        checkCols(sudoku);
        checkNumersIntegrity(sudoku);
        squareCheck(sudoku);
    }

    @Test
    void checkIsBoardGeneratedRandomly() {
        sudoku.solveGame();
        int[][] board = sudoku.getBoard();
        SudokuBoard secondSudokuBoard = new SudokuBoard(sudokuSolver);
        int[][] secondBoard = secondSudokuBoard.getBoard();
        assertFalse(Arrays.deepEquals(board, secondBoard), "Boards are not unique");
    }

    @Test
    void checkValueSetter() {
        sudoku.solveGame();
        int exampleValue = ThreadLocalRandom.current().nextInt(1, 9);
        sudoku.set(4, 4, exampleValue);
        int[][] currentBoard = sudoku.getBoard();
        assertEquals(currentBoard[4][4], exampleValue);
    }

    @Test
    void checkValueGetter() {
        sudoku.solveGame();
        int[][] currentBoard = sudoku.getBoard();
        assertEquals(currentBoard[7][3], sudoku.get(7, 3));
    }

    @Test 
    void sudokuSolverTest_withSomeValuesInseted() {
        sudoku.set(2, 4, 8);
        sudoku.set(1, 3, 4);
        sudoku.solveGame();
        checkRows(sudoku);
        checkCols(sudoku);
        checkNumersIntegrity(sudoku);
        squareCheck(sudoku);
    }

    @Test
    void sudokuBoardNullPointerException_nullAsConstructorParameter() {
        assertThrows(NullPointerException.class, 
        () -> new SudokuBoard(null)
        );
    }

    void checkRows(SudokuBoard sudokuBoard) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                valueCounter = 0;
                for (int z = 0; z < 9; z++) {
                    if (sudokuBoard.get(i, j) == sudokuBoard.get(i, z)) {
                        valueCounter += 1;
                    }
                    assertFalse(valueCounter > 1, "Row error");

                }
            }
        }
    }

    void checkCols(SudokuBoard sudokuBoard) {
           for (int i = 0; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                valueCounter = 0;
                for (int z = 0; z < 9; z += 1) {
                    if (sudokuBoard.get(j, i) == sudokuBoard.get(z, i)) {
                        valueCounter += 1;
                    }
                    assertFalse(valueCounter > 1, "Column error");

                }
            }
        }
    }

    void checkNumersIntegrity(SudokuBoard sudokuBoard) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertFalse(sudokuBoard.get(i, j) > 9 || sudokuBoard.get(i, j) < 1, "Number integrity error");
            }
        }
    }

    private void squareCheck(SudokuBoard sudokuBoard) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertTrue(validateSquare(i, j, sudokuBoard.get(i, j), sudokuBoard), "Square duplicate number error");
            }
        }
    }

    private boolean validateSquare(int row, int col, int value, SudokuBoard sudokuBoard) {
        int topLeftRow = row - (row % 3);
        int topLeftCol = col - (col % 3);
        int currentValue = 0;
        for (int i = topLeftRow; i < topLeftRow + 3; i++) {
            for (int j = topLeftCol; j < topLeftCol + 3; j++) {
                if (currentValue > 1) return false; 
                if (sudokuBoard.get(i, j) == value) currentValue++;
            }
        }
        return true;
    }
}