package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.example.models.SudokuField;
import org.example.models.SudokuValidator;

public class SudokuValidatorTest {
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
}
