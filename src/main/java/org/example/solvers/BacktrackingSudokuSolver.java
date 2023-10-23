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
                    if (solveBoard(row, col + 1, board)) {      
                        return true;
                    }
                    board[row][col] = 0;        //any number cannot be inserted into the cell
                }                               //return false and go "upper" - backtracking the right solution
            }
            return false;
        }
    }

    private boolean validateBoard(int row, int col, int value, int[][] board) {
        //rows and columns check
        for (byte i = 0; i < 9; i += 1) {
            if (board[row][i] == value || board[i][col] == value) {
                return false;
            }
        }
        //3x3 box check
        //first of all set the coordinates of left top cell in the box
        //then iterate through the box and check if number is unique
        int topLeftRow = row - (row % 3);
        int topLeftCol = col - (col % 3);
        for (int i = topLeftRow; i < topLeftRow + 3; i++) {
            for (int j = topLeftCol; j < topLeftCol + 3; j++) {
                if (board[i][j] == value) {
                    return false;
                }
            }
        }

    return true;
    }
}
