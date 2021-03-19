package com.udacity.jdnd.course3.critter.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Customer extends User {

    @Type(type="nstring")
    private String phoneNumber;

    @Column(length = 500)
    private String notes;

    @OneToMany(targetEntity = Pet.class)
    @JsonBackReference
    private List<Pet> pets;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        if (this.pets == null) {
            this.pets = new ArrayList<>();
        }
        this.pets.addAll(pets);
    }
}
