package org.example.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ThreadLocalRandom;

public class SudokuValidatorTest {
    private static final int BOARD_SIZE = 9;
    SudokuField[] sudokuFields = new SudokuField[BOARD_SIZE];
    SudokuValidator validator = new SudokuValidator();
    

    @Test
    void validatorConstructorTest() {
        assertEquals(validator.getFieldValue(0), 0);
    }

    @Test
    void setValidatorFieldTest_SudokuFieldGiven() {
        SudokuField sudokuField = new SudokuField();
        sudokuField.setFieldValue(6);
        validator.setValidatorField(sudokuField, 0);
        assertEquals(sudokuField.getFieldValue(), validator.getFieldValue(0));
    }

    @Test
    void validatorParameterConstructorTest() {
        SudokuField[] incorrectSizeFields = new SudokuField[BOARD_SIZE + 1];
        fillFields(incorrectSizeFields);
        fillFields(sudokuFields);
        assertThrows(NumberFormatException.class, 
        () -> new SudokuValidator(incorrectSizeFields));
        assertDoesNotThrow(() -> new SudokuValidator(sudokuFields));

    }

    @Test
    void validatorGetValuesTest() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            int randomNumber = ThreadLocalRandom.current().nextInt(1, 9);
            validator.setValidatorField(new SudokuField(randomNumber), i);  
            assertEquals(validator.getFieldValue(i), randomNumber);
        }
        assertThrows(NumberFormatException.class, 
        () -> validator.getFieldValue(BOARD_SIZE+1));
        assertThrows(NumberFormatException.class, 
        () -> validator.getFieldValue(BOARD_SIZE - BOARD_SIZE - 1));
    }

    @Test
    void getValuesTest() {
        fillFields(sudokuFields);
        SudokuValidator validator = new SudokuValidator(sudokuFields);
        assertArrayEquals(validator.getValues(), sudokuFields);
    }

    @Test
    void validatorVerifyTest() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            sudokuFields[i] = new SudokuField(i+1);
        }
        SudokuValidator validator = new SudokuValidator(sudokuFields);
        assertTrue(validator.verify());
        validator.setValidatorField(new SudokuField(5), 1);
        assertFalse(validator.verify());

    }

    void fillFields(SudokuField[] fields) {
        for (SudokuField element : fields) {
            element = new SudokuField(ThreadLocalRandom.current().nextInt(1, 9));
        }
    }
}
