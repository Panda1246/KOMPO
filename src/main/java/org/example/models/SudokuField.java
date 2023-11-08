package org.example.models;

public class SudokuField {
    private Integer value;
    
    public SudokuField() {
        this.value = 0;
    } 

    public Integer getFieldValue() {
        return value;
    }

    public void setFieldValue(Integer value) {
        if (value < 0 || value > 9) {
            throw new NumberFormatException("Number must be in range 0-9 inclusive");
        }
        this.value = value;
    }

}

