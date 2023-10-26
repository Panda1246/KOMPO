package org.example.solvers;

import org.example.interfaces.SudokuSolver;
import org.example.models.SudokuBoard;

import java.util.ArrayList;
import java.util.Collections;

public class BacktrackingSudokuSolver implements SudokuSolver {

    @Override
    public void solve(SudokuBoard sudokuBoard) {
        solveBoard(0, 0, sudokuBoard);
    }

    private boolean solveBoard(int row, int col, SudokuBoard sudokuBoard) {
        if (row == 9) {                          //end of board - all cells filled with numbers
            return true;
        } else if (col == 9) {                  //end of current row
            return solveBoard(row + 1, 0, sudokuBoard);
        } else if (sudokuBoard.get(row, col) != 0) {      //cell was not empty - skipping
            return solveBoard(row, col + 1, sudokuBoard);
        } else {                                  //cell empty - ready to be filled
            ArrayList<Integer> valuesList = new ArrayList<>();
            for (int i = 1; i < 10; i += 1) {
                valuesList.add(i);
            }                                   //creating an Arraylist with numbers 1-9
            Collections.shuffle(valuesList);    //then shuffle the numbers
            for (int i = 1; i < 10; i += 1) {
                if (validateBoard(row, col, valuesList.get(i - 1), sudokuBoard)) {     //if number can be placed in cell
                    sudokuBoard.set(row, col, valuesList.get(i - 1));              //put the number in cell
                    if (solveBoard(row, col + 1, sudokuBoard)) {      
                        return true;
                    }
                    sudokuBoard.set(row, col, 0);         //any number cannot be inserted into the cell
                }                               //return false and go "upper" - backtracking the right solution
            }
            return false;
        }
    }

    private boolean validateBoard(int row, int col, int value, SudokuBoard sudokuBoard) {
        //rows and columns check
        for (byte i = 0; i < 9; i += 1) {
            if (sudokuBoard.get(row, i) == value || sudokuBoard.get(i, col) == value) {
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
                if (sudokuBoard.get(i, j) == value) {
                    return false;
                }
            }
        }

    return true;
    }
}
