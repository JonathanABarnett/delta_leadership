package com.jonathanbarnett.delta_leadership.service;

import com.jonathanbarnett.delta_leadership.models.FamilyMember;

import java.util.List;

public interface FamilyMemberService {

    void saveAll(List<FamilyMember> familyMembers);

    FamilyMember save(FamilyMember familyMember);

    FamilyMember findById(int id);

}
