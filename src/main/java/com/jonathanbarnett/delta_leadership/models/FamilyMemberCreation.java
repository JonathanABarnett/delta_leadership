package com.jonathanbarnett.delta_leadership.models;

import java.util.ArrayList;
import java.util.List;


public class FamilyMemberCreation {
    private List<FamilyMember> familyMemberList = new ArrayList<>();

    public FamilyMemberCreation() {}

    public void addFamilyMember(FamilyMember familyMember) {
        this.familyMemberList.add(familyMember);
    }

    public List<FamilyMember> getFamilyMemberList() {
        return familyMemberList;
    }

    public void setFamilyMemberList(List<FamilyMember> familyMemberList) {
        this.familyMemberList = familyMemberList;
    }
}
