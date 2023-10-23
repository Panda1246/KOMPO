package org.example.solvers;

import org.example.interfaces.SudokuSolver;

import java.util.ArrayList;
import java.util.Collections;

public class BacktrackingSudokuSolver implements SudokuSolver {

    @Override
    public void solve(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = 0;
            }
        }
        solveBoard(0, 0, board);
    }

    private boolean solveBoard(int row, int col, int[][] board) {
        if (row == 9) {                          //end of board - all cells filled with numbers
            return true;
        } else if (col == 9) {                  //end of current row
            return solveBoard(row + 1, 0, board);
        } else if (board[row][col] != 0) {      //cell was not empty - skipping
            return solveBoard(row, col + 1, board);
        } else {                                  //cell empty - ready to be filled
            ArrayList<Integer> valuesList = new ArrayList<>();
            for (int i = 1; i < 10; i += 1) {
                valuesList.add(i);
            }                                   //creating an Arraylist with numbers 1-9
            Collections.shuffle(valuesList);    //then shuffle the numbers
            for (int i = 1; i < 10; i += 1) {
                if (validateBoard(row, col, valuesList.get(i - 1), board)) {     //if number can be placed in cell
                    board[row][col] = valuesList.get(i - 1);              //put the number in cell
                    if (solveBoard(row, col + 1, board)) {      //
                        return true;
                    }
                    board[row][col] = 0;        //any number cannot be inserted into the cell
                }                               //return false and go "upper" - backtracking the right solution
            }
            return false;
        }
    }

    private int[][] setSquareCoordinates(int minRow, int maxRow, int minCol, int maxCol) {
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

    private boolean validateBoard(int row, int col, int value, int[][] board) {
        for (int i = 0; i < 9; i += 1) {
            if (board[row][i] == value || board[i][col] == value) {
                return false;
            }
        }
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
        for (int i = 0, j = 0; i < 9; i += 1) {
            if ((board[squareCoordinates[i][j]][squareCoordinates[i][j + 1]] == value)) {
                return false;
            }
        }
    return true;
    }
}
