package com.fitmegut.dciwarehousefinalproject.web.controller;

import com.fitmegut.dciwarehousefinalproject.service.interfaces.ItemServiceInterface;
import com.fitmegut.dciwarehousefinalproject.service.interfaces.MemberServiceInterface;
import com.fitmegut.dciwarehousefinalproject.service.interfaces.WardrobeServiceInterface;
import com.fitmegut.dciwarehousefinalproject.web.dto.ItemDto;
import com.fitmegut.dciwarehousefinalproject.web.dto.MemberRegistrationDto;
import com.fitmegut.dciwarehousefinalproject.web.dto.WardrobeDto;
import com.fitmegut.dciwarehousefinalproject.web.dto.WardrobeManagerDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wardrobe")
public class WardrobeManagerController {

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
        model.addAttribute("wardrobes", itemService.findAll());

        return "wardrobe";
    }

    @GetMapping("/add/{id}")
    public String addItem(@PathVariable String id, Model model) {
        model.addAttribute("newItem", new WardrobeManagerDto());
        return "add-item";
    }

    @PostMapping("/add/{id}")
    public String processAddItem(@Valid @PathVariable String id, @ModelAttribute("newItem") WardrobeManagerDto wardrobeManagerDto,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "add-item";
        }

        WardrobeDto wardrobeDto = new WardrobeDto(wardrobeManagerDto.getWardrobeDto().getClothingCategories(),false);
        wardrobeDto.setMemberDto(memberService.findByEmail(id));

        WardrobeDto savedWardrobeDto =  wardrobeService.save(wardrobeDto);

        ItemDto itemDto  = new ItemDto(wardrobeManagerDto.getItemDto().getItemName(), wardrobeManagerDto.getItemDto().getItemBrand(),
                wardrobeManagerDto.getItemDto().getSize(), wardrobeManagerDto.getItemDto().getColor(),
                wardrobeManagerDto.getItemDto().getItemCondition(), wardrobeManagerDto.getItemDto().getDescription(),
                wardrobeManagerDto.getItemDto().getImage());

        itemDto.setWardrobeDto(savedWardrobeDto);
        itemService.save(itemDto);

        return "redirect:/wardrobe/items";
    }

    @GetMapping("/edit/{id}")
    public String editItem(@PathVariable("id") Long id, Model model) {
        model.addAttribute("wardrobe", new WardrobeManagerDto());
        return "redirect:/wardrobe";
    }

    @PostMapping("/post/{id}")
    public String postItem(@PathVariable("id") Long id, Model model) {
        model.addAttribute("wardrobe", new WardrobeManagerDto());
        return "redirect:/wardrobe";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        return "redirect:/wardrobe";
    }
}
