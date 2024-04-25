package com.fitmegut.dciwarehousefinalproject.service.impl;

import com.fitmegut.dciwarehousefinalproject.model.Wardrobe;
import com.fitmegut.dciwarehousefinalproject.repository.WardrobeRepository;
import com.fitmegut.dciwarehousefinalproject.service.interfaces.WardrobeServiceInterface;
import com.fitmegut.dciwarehousefinalproject.web.dto.WardrobeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WardrobeServiceImpl implements WardrobeServiceInterface {

    private WardrobeRepository wardrobeRepository;

    @Autowired
    public WardrobeServiceImpl(WardrobeRepository wardrobeRepository) {
        this.wardrobeRepository = wardrobeRepository;
    }

    @Override
    public void save(WardrobeDto wardrobeDto) {

        Wardrobe wardrobe = new Wardrobe(wardrobeDto.getClothingCategories(), wardrobeDto.isPosted());

        wardrobeRepository.save(wardrobe);
    }
}
