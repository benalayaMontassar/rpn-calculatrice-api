package com.example.calculatrice.dto;

import java.math.BigDecimal;
import java.util.Stack;

public class StackRpnDto {
    private long id;
    private String name;
    private Stack<Float> content;

    public StackRpnDto(long id, String name, Stack<Float> value) {
        this.id = id;
        this.name = name;
        this.content = value;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stack<Float> getContent() {
        return content;
    }

    public void setContent(Stack<Float> content) {
        this.content = content;
    }
}
