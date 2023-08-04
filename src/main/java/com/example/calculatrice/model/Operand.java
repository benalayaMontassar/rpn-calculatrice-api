package com.example.calculatrice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "operand")
public class Operand {
    @Id
    private String id;
    @Column
    private String name;

    public Operand() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
