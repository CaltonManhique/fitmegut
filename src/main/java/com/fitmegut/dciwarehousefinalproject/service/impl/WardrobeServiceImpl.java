package com.fitmegut.dciwarehousefinalproject.service.impl;

import com.fitmegut.dciwarehousefinalproject.model.Member;
import com.fitmegut.dciwarehousefinalproject.model.Wardrobe;
import com.fitmegut.dciwarehousefinalproject.repository.WardrobeRepository;
import com.fitmegut.dciwarehousefinalproject.service.interfaces.WardrobeServiceInterface;
import com.fitmegut.dciwarehousefinalproject.web.dto.WardrobeDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WardrobeServiceImpl implements WardrobeServiceInterface {

    private WardrobeRepository wardrobeRepository;

    @Autowired
    public WardrobeServiceImpl(WardrobeRepository wardrobeRepository) {
        this.wardrobeRepository = wardrobeRepository;
    }

    @Override
    @Transactional
    public WardrobeDto save(WardrobeDto wardrobeDto) {

        Member member = new Member();
        member.setId(wardrobeDto.getMemberDto().getId());

        Wardrobe wardrobe = new Wardrobe(wardrobeDto.getClothingCategories(), wardrobeDto.isPosted());
        wardrobe.setMember(member);

        Wardrobe saved = wardrobeRepository.save(wardrobe);
      return  new WardrobeDto(saved.getId(),saved.getClothingCategories(), saved.isPosted());
    }

    @Override
    @Transactional
    public void deleteWardrobeEntryById(Long id) {

        Optional<Wardrobe> wardrobe = wardrobeRepository.findById(id);

        if (wardrobe.isPresent()) {
            wardrobeRepository.delete(wardrobe.get());
        }else {
            throw new RuntimeException("Wardrobe not found or already deleted with id: " + id );
        }
    }

    @Override
    public List<Wardrobe> findAll() {
        return wardrobeRepository.findAll();
    }

    @Override
    public Wardrobe findById(Long id) {
        Optional<Wardrobe> wardrobe = wardrobeRepository.findById(id);

        if (wardrobe.isPresent()) {
            return wardrobe.get();
        }else {
            throw new RuntimeException("Wardrobe not found or already deleted with id: " + id );
        }
    }
}
