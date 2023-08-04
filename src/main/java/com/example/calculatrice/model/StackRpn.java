package com.example.calculatrice.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Stack;
@Entity
@Table(name = "stack")
public class StackRpn {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    private String content;

    public StackRpn() {
    }

    public StackRpn(long id, String name, String content) {
        this.id =id;
        this.name = name;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
       this.content = content;
    }
    public void setName(String name) {
        this.name = name;
    }
}
