package com.jonathanbarnett.delta_leadership.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean member = true;
    private String lastName;
    @OneToMany(mappedBy = "attendee", cascade = CascadeType.ALL)
    private List<FamilyMember> familyMembers = new ArrayList<>();

    public Attendee() {}

    public void addFamilyMember(FamilyMember familyMember) {
        this.familyMembers.add(familyMember);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<FamilyMember> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<FamilyMember> familyMembers) {
        this.familyMembers = familyMembers;
    }
}
