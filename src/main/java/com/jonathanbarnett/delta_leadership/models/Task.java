package com.jonathanbarnett.delta_leadership.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String addedBy;
    private Date addedOn;
    private Date completeBy;
    private Date completedOn;

    @ManyToMany
    @JoinTable(
            name = "task_user",
            joinColumns =  @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> taskOwners;

    @ManyToMany
    @JoinTable(
            name = "task_family_member",
            joinColumns =  @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "family_member_id")
    )
    private List<FamilyMember> familyMembers;

    @OneToOne
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToMany
    @JoinTable(
            name = "task_task_type",
            joinColumns =  @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "task_type_id")
    )
    private List<TaskType> taskTypes;

    public Task() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public Date getCompleteBy() {
        return completeBy;
    }

    public void setCompleteBy(Date completeBy) {
        this.completeBy = completeBy;
    }

    public Date getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(Date completedOn) {
        this.completedOn = completedOn;
    }

    public List<User> getTaskOwners() {
        return taskOwners;
    }

    public void setTaskOwners(List<User> taskOwners) {
        this.taskOwners = taskOwners;
    }

    public List<FamilyMember> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<FamilyMember> familyMembers) {
        this.familyMembers = familyMembers;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public List<TaskType> getTaskTypes() {
        return taskTypes;
    }

    public void setTaskTypes(List<TaskType> taskTypes) {
        this.taskTypes = taskTypes;
    }
}
