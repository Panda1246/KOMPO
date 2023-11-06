package org.example.models;

import org.example.interfaces.SudokuSolver;
import org.example.models.SudokuField;

import java.util.ArrayList;

public class SudokuBoard {
    private SudokuSolver sudokuSolver;
    private ArrayList<SudokuField> sudokuFields = new ArrayList<>();

    public SudokuBoard(SudokuSolver sudokuSolver) {
        if (sudokuSolver == null) {
            throw new NullPointerException("SudokuBoard must be initialized");
        }
        this.sudokuSolver = sudokuSolver;

        //initialization of empty board

        for (int i = 0; i < 81; i ++) {
            SudokuField emptyField = new SudokuField();
            emptyField.setFieldValue(0);
            sudokuFields.add(emptyField);
        }
    }

    public int[][] getBoard() {
        int[][] secondBoard = new int[9][9];
        for (int i = 0,  counter = 0; i < 9; i++) {
            for(int j = 0; j < 9; j ++) {
                secondBoard[i][j] = sudokuFields.get(counter).getFieldValue();
                counter++;
            }
        }
        return secondBoard;
    } 
    

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    public void set(int row, int col, int value) {
       Integer fieldNumber = calculateFieldNumber(row, col);
       sudokuFields.get(fieldNumber).setFieldValue(value);
    }

    public int get(int row, int col) {
        Integer fieldNumber = calculateFieldNumber(row, col);
        return sudokuFields.get(fieldNumber).getFieldValue();
    }
    
     private boolean checkBoard() {
        return true;
    }

   /*  public SudokuRow getRow(Integer y) {

    } */

    private Integer calculateFieldNumber(int row, int col) {
        return 9 * row + col;
    }
}
