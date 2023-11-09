package org.example.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SudokuColumnTest {
    private static final int BOARD_SIZE = 9;

    @Test
    void noArgumentsConstructorTest() {
        SudokuColumn sudokuColumn = new SudokuColumn();
        for (int i = 0; i < BOARD_SIZE; i++) {
            assertEquals(sudokuColumn.getFieldValue(i), 0);
        }
    }
}
