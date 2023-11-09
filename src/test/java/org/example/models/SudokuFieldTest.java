package org.example.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ThreadLocalRandom;

public class SudokuFieldTest {
    SudokuField sudokuField = new SudokuField();

    @Test
    void sudokuFieldConstructorTest() {
        assertEquals(sudokuField.getFieldValue(), 0);
    }

    @Test
    void sudokuFieldParameterConstructorTest() {
        int randomField = ThreadLocalRandom.current().nextInt(1, 9);
        SudokuField secondField = new SudokuField(randomField);
        assertEquals(secondField.getFieldValue(), randomField);
        assertThrows(NumberFormatException.class, 
        () -> new SudokuField(29));
        assertThrows(NumberFormatException.class, 
        () -> new SudokuField(-1));
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
