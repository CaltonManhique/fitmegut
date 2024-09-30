package com.fitmegut.dciwarehousefinalproject.web.controller;

import com.fitmegut.dciwarehousefinalproject.service.interfaces.*;
import com.fitmegut.dciwarehousefinalproject.web.dto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/wardrobe")
public class WardrobeManagerController {

    private static final String UPLOADED_ITEMS_DIRECTORY = System.getProperty("user.dir") + "/items";

    private ItemServiceInterface itemService;
    private WardrobeServiceInterface wardrobeService;
    private MemberServiceInterface memberService;

    @Autowired
    public WardrobeManagerController(ItemServiceInterface itemService, WardrobeServiceInterface wardrobeService
            , MemberServiceInterface memberService) {
        this.itemService = itemService;
        this.wardrobeService = wardrobeService;
        this.memberService = memberService;
    }

    @GetMapping("/items")
    public String items(@ModelAttribute("member") MemberRegistrationDto memberRegistrationDto, Model model) {
        Authentication loggedMember = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedMember.getName();

        memberRegistrationDto = memberService.findByEmail(email);
        model.addAttribute("member", memberRegistrationDto);

        Long id = memberRegistrationDto.getId();

        List<ItemDto> items = itemService.findAll();
        List<ItemDto> filteredItems = new ArrayList<>();
        List<WardrobeDto> wardrobesDto = wardrobeService.findAll();

        //Optimize
        for (ItemDto itemDto : items) {
            for (WardrobeDto wardrobeDto : wardrobesDto) {
                if ((wardrobeDto.getMemberDto().getId().equals(id)) &&
                        (wardrobeDto.getId() == itemDto.getWardrobeDto().getId())) {
                    filteredItems.add(itemDto);
                }
            }
        }

        model.addAttribute("wardrobes", filteredItems);

        return "wardrobe/wardrobe";
    }

    @GetMapping("/add")
    public String addItem(Model model) {
        model.addAttribute("newItem", new WardrobeManagerDto());
        return "wardrobe/add-item";
    }

    @PostMapping("/add")
    public String processAddItem(@Valid @ModelAttribute("newItem") WardrobeManagerDto wardrobeManagerDto,
                                 BindingResult bindingResult, @RequestParam("image") MultipartFile image, Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            return "wardrobe/add-item";
        }

        Authentication loggedMember = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedMember.getName();

        WardrobeDto wardrobeDto = new WardrobeDto(wardrobeManagerDto.getWardrobeDto().getClothingCategories(), false);
        wardrobeDto.setMemberDto(memberService.findByEmail(email));

        // Image handling
        StringBuilder fileNames = new StringBuilder();
        if(image != null && !image.isEmpty()) {
            Path fileNameAndPath = Paths.get(UPLOADED_ITEMS_DIRECTORY, image.getOriginalFilename());
            fileNames.append(image.getOriginalFilename());
            Files.write(fileNameAndPath, image.getBytes());
        }

        WardrobeDto savedWardrobeDto = wardrobeService.save(wardrobeDto);

        ItemDto itemDto = getItemDto(wardrobeManagerDto, fileNames, savedWardrobeDto);
        itemService.save(itemDto);

        return "redirect:/wardrobe/items";
    }

    private static ItemDto getItemDto(WardrobeManagerDto wardrobeManagerDto, StringBuilder fileNames, WardrobeDto savedWardrobeDto) {
        String imageName = UPLOADED_ITEMS_DIRECTORY + "/" + fileNames.toString();
        System.out.println(imageName);
        ItemDto itemDto = new ItemDto(wardrobeManagerDto.getItemDto().getItemName(), wardrobeManagerDto.getItemDto().getItemBrand(),
                wardrobeManagerDto.getItemDto().getSize(), wardrobeManagerDto.getItemDto().getColor(),
                wardrobeManagerDto.getItemDto().getItemCondition(), wardrobeManagerDto.getItemDto().getDescription(),
                /*wardrobeManagerDto.getItemDto().getImage()*/ imageName);

        itemDto.setWardrobeDto(savedWardrobeDto);
        return itemDto;
    }

    @GetMapping("/edit/{id}")
    public String editItem(@PathVariable("id") Long id, Model model) {

        model.addAttribute("editItem", itemService.findById(id));
        return "wardrobe/edit-item";
    }

    @PostMapping("/edit/{id}")
    public String processEditItem(@Valid @PathVariable("id") Long id, @ModelAttribute("editItem") ItemDto dto,
                                  BindingResult bindingResult) {
        System.out.println(id + " Controller" + dto.getItemName());
        if (bindingResult.hasErrors()) {
            return "wardrobe/edit-item";
        }

        WardrobeDto wardrobeDto = new WardrobeDto(dto.getWardrobeDto().getId(), dto.getWardrobeDto().getClothingCategories(),
                dto.getWardrobeDto().isPosted());
        wardrobeService.update(wardrobeDto);

        ItemDto itemDto = new ItemDto(id, dto.getItemName(), dto.getItemBrand(), dto.getSize(), dto.getColor(),
                dto.getItemCondition(), dto.getDescription(), dto.getImage(), wardrobeDto);

        itemService.update(itemDto);

        return "redirect:/wardrobe/items";
    }

    @GetMapping("/post/{id}")
    public String postItem(@PathVariable("id") Long id) {
        ItemDto itemDto = itemService.findById(id);
        WardrobeDto wardrobeDto = null;

        if (itemDto.getWardrobeDto().isPosted()) {
            wardrobeDto = new WardrobeDto(itemDto.getWardrobeDto().getId(), itemDto.getWardrobeDto().getClothingCategories(),
                    false);
        } else {
            wardrobeDto = new WardrobeDto(itemDto.getWardrobeDto().getId(), itemDto.getWardrobeDto().getClothingCategories(),
                    true);
        }
        wardrobeService.update(wardrobeDto);

        return "redirect:/wardrobe/items";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        ItemDto itemDto = itemService.findById(id);
        itemService.deleteItemById(id);
        wardrobeService.deleteWardrobeEntryById(itemDto.getWardrobeDto().getId());

        return "redirect:/wardrobe/items";
    }
}
