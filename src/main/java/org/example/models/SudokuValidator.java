package org.example.models;

public class SudokuValidator {
    private final int validatorSize = 9;
    private SudokuField[] sudokuFields = new SudokuField[validatorSize];

    public SudokuValidator() {
        for(int i = 0; i < validatorSize; i++) {
            SudokuField sudokuField = new SudokuField();
            sudokuFields[i] = sudokuField;
        }
    }

    public SudokuField[] getValues() {
        return sudokuFields;
    }

    public void setValidatorField(SudokuField sudokuField, int position) {
        sudokuFields[position] = sudokuField;
    }

    public boolean verify() {
        int[] validatorFields = new int[validatorSize];
        for(int i = 0; i < validatorSize; i++) {
            for(int element : validatorFields) {
                if(element == sudokuFields[i].getFieldValue()) {
                    return false;
                }
            validatorFields[i] = sudokuFields[i].getFieldValue();
            }
        }
        return true;
    }
}
