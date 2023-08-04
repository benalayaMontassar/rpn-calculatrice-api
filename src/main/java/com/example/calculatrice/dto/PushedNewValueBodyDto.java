package com.example.calculatrice.dto;

public class PushedNewValueBodyDto {
    private String newValue;
    public PushedNewValueBodyDto(){}
    public PushedNewValueBodyDto(String newValue) {
        this.newValue = newValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}
