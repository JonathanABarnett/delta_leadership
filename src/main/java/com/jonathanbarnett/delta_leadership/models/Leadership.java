package com.jonathanbarnett.delta_leadership.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Leadership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leadershipId;
    @NotEmpty(message = "Please enter name of role.")
    private String name;

}
