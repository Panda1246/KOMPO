package org.example.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SudokuBoxTest {
    private static final int BOARD_SIZE = 9;

    @Test
    void noArgumentsConstructorTest() {
        SudokuBox SudokuBox = new SudokuBox();
        for (int i = 0; i < BOARD_SIZE; i++) {
            assertEquals(SudokuBox.getFieldValue(i), 0);
        }
    }
}
