package org.example.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SudokuRowTest {
        private static final int BOARD_SIZE = 9;

    @Test
    void noArgumentsConstructorTest() {
        SudokuRow SudokuRow = new SudokuRow();
        for (int i = 0; i < BOARD_SIZE; i++) {
            assertEquals(SudokuRow.getFieldValue(i), 0);
        }
    }
}

