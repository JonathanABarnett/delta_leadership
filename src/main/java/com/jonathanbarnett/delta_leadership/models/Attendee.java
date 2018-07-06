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
    private int numOfChildren = 0;
    @OneToMany(mappedBy = "attendee", cascade = CascadeType.ALL)
    private List<FamilyMember> familyMembers;

    public Attendee() {}

    public void addFamilyMember(FamilyMember familyMember) {
        if (familyMembers == null) {
            familyMembers = new ArrayList<>();
        }
        familyMembers.add(familyMember);
        familyMember.setAttendee(this);
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

    public int getNumOfChildren() {
        return numOfChildren;
    }

    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
    }

    public List<FamilyMember> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<FamilyMember> familyMembers) {
        this.familyMembers = familyMembers;
    }
}
