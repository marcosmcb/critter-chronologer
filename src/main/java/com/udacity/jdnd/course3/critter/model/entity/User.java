package com.udacity.jdnd.course3.critter.model.entity;

import org.hibernate.annotations.Nationalized;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="user")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Nationalized
    private String name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
