package com.fitmegut.dciwarehousefinalproject.web.controller;

import com.fitmegut.dciwarehousefinalproject.service.interfaces.ItemServiceInterface;
import com.fitmegut.dciwarehousefinalproject.service.interfaces.WardrobeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wardrobe")
public class WardrobeManagerController {

    private ItemServiceInterface itemService;
    private WardrobeServiceInterface wardrobeService;

    @Autowired
    public WardrobeManagerController(ItemServiceInterface itemService, WardrobeServiceInterface wardrobeService) {
        this.itemService = itemService;
        this.wardrobeService = wardrobeService;
    }
}
