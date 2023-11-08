package org.example.models;

public class SudokuValidator {
    private static final int VALIDATOR_SIZE = 9;
    private SudokuField[] sudokuFields = new SudokuField[VALIDATOR_SIZE];

    public SudokuValidator() {
        for(int i = 0; i < VALIDATOR_SIZE; i++) {
            SudokuField sudokuField = new SudokuField();
            sudokuFields[i] = sudokuField;
        }
    }

    public SudokuValidator(SudokuField[] sudokuFields) {
        if (sudokuFields.length != VALIDATOR_SIZE) {
            throw new NumberFormatException("Sudokufields size must be equal to 9");
        }
        for (int i = 0; i < VALIDATOR_SIZE; i++) {
                this.sudokuFields[i] = sudokuFields[i];
           }
    }

    public SudokuField[] getValues() {
        return sudokuFields;
    }

    public Integer getFieldValue(int position) {
        if (position < 0 || position > 8) {
            throw new NumberFormatException("Position must be in range 0-8 inclusive");
        }
        return sudokuFields[position].getFieldValue();
    }

    public void setValidatorField(SudokuField sudokuField, int position) {
        sudokuFields[position] = sudokuField;
    }

    public boolean verify() {
        int[] validatorFields = new int[VALIDATOR_SIZE];
        for (int i = 0; i < VALIDATOR_SIZE; i++) {
            for (int element : validatorFields) {
                if (element == sudokuFields[i].getFieldValue()) {
                    return false;
                }
            validatorFields[i] = sudokuFields[i].getFieldValue();
            }
        }
        return true;
    }
}
