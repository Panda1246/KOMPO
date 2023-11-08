package org.example.models;

import org.example.interfaces.SudokuSolver;
import org.example.models.SudokuField;

public class SudokuBoard {
    private final int boardSize = 9;
    private SudokuSolver sudokuSolver;
    private SudokuField[][] sudokuFields = new SudokuField[boardSize][boardSize];

    public SudokuBoard(SudokuSolver sudokuSolver) {
        if (sudokuSolver == null) {
            throw new NullPointerException("SudokuBoard must be initialized");
        }
        this.sudokuSolver = sudokuSolver;

        //initialization of empty board
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                SudokuField sudokuField = new SudokuField();
                sudokuFields[i][j] = sudokuField;
            }
            
        }
        
    }

    public int[][] getBoard() {
        int[][] secondBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j ++) {
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
        SudokuRow sudokuRow = new SudokuRow();
        for(int i = 0; i < boardSize; i++) {
            sudokuRow.setValidatorField(sudokuFields[rowNumber][i], i);
        }
        return sudokuRow;
    }

    public SudokuColumn getColumn(int colNumber) {
        SudokuColumn sudokuColumn = new SudokuColumn();
        for (int i = 0; i < boardSize; i++) {
            sudokuColumn.setValidatorField(sudokuFields[colNumber][i], i);
        }
        return sudokuColumn;
    }

    public SudokuBox getBox(int topLeftCol, int topLeftRow) {
        SudokuBox sudokuBox = new SudokuBox();
        for(int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                sudokuBox.setValidatorField(sudokuFields[topLeftRow][topLeftCol], i);
                sudokuBox.setValidatorField(sudokuFields[topLeftRow][topLeftCol+1], i);
                sudokuBox.setValidatorField(sudokuFields[topLeftRow][topLeftCol+2], i);
            }
            topLeftRow += 1 ;
        }
        return sudokuBox;
    }
    
     private boolean checkBoard() {
        SudokuRow currentRow;
        SudokuColumn currentColumn;
        SudokuBox currentBox;
        for (int i = 0, boxRow = 0, boxCol = 0; i < boardSize; i++) {
            currentRow = getRow(i);
            currentColumn = getColumn(i);
            currentBox = getBox(boxCol, boxRow);
            if((i+1) % 3 == 0) {
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


    private int calculateBoxNumber(int row, int col) {
        int topLeftRow = row - (row % 3);
        int topLeftCol = col - (col % 3);
        if (topLeftRow == 0) {
            if (topLeftCol == 0) {
                return 0;
            } else if (topLeftCol == 3) {
                return 1;
            } else {
                return 2;
            }
            
        } else if (topLeftCol == 3) {
            if (topLeftCol == 0) {
                return 3;
            } else if (topLeftCol == 3) {
                return 4;
            } else {
                return 5;
            }
        } else {
            if (topLeftCol == 6) {
                return 6;
            } else if (topLeftCol == 3) {
                return 7;
            } else {
                return 8;
            }    
        }
    }

    private int calculateBoxIndex(int row, int col) {
        int currentRowNumber =  row % 3;
        int currentColumnNumber = col % 3;
        if(currentRowNumber == 0) {
            return currentColumnNumber;
        } else if(currentRowNumber == 1) {
            return 3 + currentColumnNumber;
        } else {
            return 6 + currentColumnNumber;
        }

    }
}
