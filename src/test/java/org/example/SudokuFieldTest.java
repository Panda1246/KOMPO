package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.example.models.SudokuField;

public class SudokuFieldTest {
    SudokuField sudokuField = new SudokuField();

    @Test
    void SudokuFieldConstructorTest() {
        assertEquals(sudokuField.getFieldValue(), 0);
    }

    @Test
    void sudokuFieldSetterAndGetterTest() {
        assertEquals(sudokuField.getFieldValue(), 0);
        sudokuField.setFieldValue(5);
        assertEquals(sudokuField.getFieldValue(), 5);
        assertThrows(NumberFormatException.class, 
        () -> sudokuField.setFieldValue(-1));
        assertThrows(NumberFormatException.class, 
        () -> sudokuField.setFieldValue(10));
    }
    
}
