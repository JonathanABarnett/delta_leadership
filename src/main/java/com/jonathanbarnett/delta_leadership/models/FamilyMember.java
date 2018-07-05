package com.jonathanbarnett.delta_leadership.models;

import javax.persistence.*;

@Entity
public class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "attendee_id")
    private Attendee attendee;

    public FamilyMember() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Attendee getAttendee() {
        return attendee;
    }

    public void setAttendee(Attendee attendee) {
        this.attendee = attendee;
    }
}
