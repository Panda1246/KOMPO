package org.example.models;

import org.example.interfaces.SudokuSolver;
import org.example.models.SudokuField;
import org.example.models.SudokuRow;

public class SudokuBoard {
    private final static int BOARD_SIZE = 9;
    private SudokuSolver sudokuSolver;
    private SudokuField[][] sudokuFields = new SudokuField[BOARD_SIZE][BOARD_SIZE];

    public SudokuBoard(SudokuSolver sudokuSolver) {
        if (sudokuSolver == null) {
            throw new NullPointerException("SudokuBoard must be initialized");
        }
        this.sudokuSolver = sudokuSolver;

        //initialization of empty board
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                SudokuField sudokuField = new SudokuField();
                sudokuFields[i][j] = sudokuField;
            }
            
        }
        
    }

    public int[][] getBoard() {
        int[][] secondBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j ++) {
                secondBoard[i][j] = sudokuFields[i][j].getFieldValue();
            }
        }
        return secondBoard;
    } 
    

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    public void set(int row, int col, int value) {
        sudokuFields[row][col].setFieldValue(value);
    }

    public int get(int row, int col) {
        return sudokuFields[row][col].getFieldValue();
    }

    public SudokuRow getRow(int rowNumber) {
        return new SudokuRow(sudokuFields[rowNumber]);
    }

    public SudokuColumn getColumn(int colNumber) {
        SudokuField[] sudokuColumns = new SudokuField[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            sudokuColumns[i] = sudokuFields[i][colNumber];
        }

        return new SudokuColumn(sudokuColumns);
    }

    public SudokuBox getBox(int topLeftCol, int topLeftRow) {
        SudokuField[] sudokuBoxFields = new SudokuField[BOARD_SIZE];
        for (int j = 0, counter=0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                sudokuBoxFields[counter] = sudokuFields[topLeftRow][topLeftCol];
                sudokuBoxFields[counter+1] = sudokuFields[topLeftRow][topLeftCol+1];
                sudokuBoxFields[counter+2] = sudokuFields[topLeftRow][topLeftCol+2];
            }
            topLeftRow += 1;
            counter += 3;
        }
        return new SudokuBox(sudokuBoxFields);
    }
    
     private boolean checkBoard() {
        SudokuRow currentRow;
        SudokuColumn currentColumn;
        SudokuBox currentBox;
        for (int i = 0, boxRow = 0, boxCol = 0; i < BOARD_SIZE; i++) {
            currentRow = getRow(i);
            currentColumn = getColumn(i);
            currentBox = getBox(boxCol, boxRow);
            if ((i+1) % 3 == 0) {
                boxCol = 0;
                boxRow += 3;
            } else {
                boxCol += 3;
            }

            if (currentColumn.verify() == false || currentRow.verify() == false || 
            currentBox.verify() == false) {
                return false;
            }
        }
        return true; 
    }

}
