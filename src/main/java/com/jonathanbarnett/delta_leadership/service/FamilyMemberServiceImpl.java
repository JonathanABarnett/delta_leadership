package com.jonathanbarnett.delta_leadership.service;

import com.jonathanbarnett.delta_leadership.models.FamilyMember;
import com.jonathanbarnett.delta_leadership.repository.FamilyMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyMemberServiceImpl implements FamilyMemberService{

    @Autowired
    private FamilyMemberRepository familyMemberRepository;

    @Override
    public void saveAll(List<FamilyMember> familyMembers) {
        familyMemberRepository.saveAll(familyMembers);
    }

    @Override
    public FamilyMember save(FamilyMember familyMember) {
        return familyMemberRepository.save(familyMember);
    }

    @Override
    public FamilyMember findById(int id) {
        return familyMemberRepository.getOne(id);
    }




}
