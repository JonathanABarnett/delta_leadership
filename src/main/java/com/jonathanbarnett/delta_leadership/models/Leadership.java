package com.jonathanbarnett.delta_leadership.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Leadership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leadershipId;
    private String name;
    @OneToMany(mappedBy = "leadership", cascade = CascadeType.ALL)
    private List<User> userList;

    public int getLeadershipId() {
        return leadershipId;
    }

    public void setLeadershipId(int leadershipId) {
        this.leadershipId = leadershipId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
