package org.example.models;

import org.example.interfaces.SudokuSolver;

import java.util.Arrays;

public class SudokuBoard {
    private int[][] board;
    private SudokuSolver sudokuSolver;

    public SudokuBoard(SudokuSolver sudokuSolver) {
        board = new int[9][9];
        this.sudokuSolver = sudokuSolver;
        }

    public int[][] getBoard() {
        int[][] secondBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            secondBoard[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return secondBoard;
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    public void setValue(int row, int col, int value) {
        board[row][col] = value;
    }

    public int getValue(int row, int col) {
        return board[row][col];
    }
}
