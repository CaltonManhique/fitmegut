package com.fitmegut.dciwarehousefinalproject.service.interfaces;

import com.fitmegut.dciwarehousefinalproject.model.Wardrobe;
import com.fitmegut.dciwarehousefinalproject.web.dto.WardrobeDto;

public interface WardrobeServiceInterface {

    //Method for creation of wardrobe, post item
    void save(WardrobeDto wardrobeDto);

    void deleteWardrobeEntryById(Long id);

}
