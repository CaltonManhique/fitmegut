package com.fitmegut.dciwarehousefinalproject.service.interfaces;

import com.fitmegut.dciwarehousefinalproject.model.Wardrobe;
import com.fitmegut.dciwarehousefinalproject.web.dto.WardrobeDto;

import java.util.List;

public interface WardrobeServiceInterface {

    //Method for creation of wardrobe, post item
    WardrobeDto save(WardrobeDto wardrobeDto);

    void deleteWardrobeEntryById(Long id);

    List<Wardrobe> findAll();

    Wardrobe findById(Long id);

}
