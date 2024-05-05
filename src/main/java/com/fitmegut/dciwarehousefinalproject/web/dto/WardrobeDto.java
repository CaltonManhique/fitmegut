package com.fitmegut.dciwarehousefinalproject.web.dto;

import com.fitmegut.dciwarehousefinalproject.model.Member;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class WardrobeDto {

    private long id;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String clothingCategories; // dropdown list
    private boolean posted;

    private MemberRegistrationDto memberDto;

    public WardrobeDto() {
    }

    public WardrobeDto(String clothingCategories, boolean posted) {
        this.clothingCategories = clothingCategories;
        this.posted = posted;
    }

    public WardrobeDto(long id, String clothingCategories, boolean posted) {
        this.id = id;
        this.clothingCategories = clothingCategories;
        this.posted = posted;
    }

    public WardrobeDto(long id, String clothingCategories, boolean posted, MemberRegistrationDto memberDto) {
        this.id = id;
        this.clothingCategories = clothingCategories;
        this.posted = posted;
        this.memberDto = memberDto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClothingCategories() {
        return clothingCategories;
    }

    public void setClothingCategories(String clothingCategories) {
        this.clothingCategories = clothingCategories;
    }

    public boolean isPosted() {
        return posted;
    }

    public void setPosted(boolean posted) {
        this.posted = posted;
    }

    public MemberRegistrationDto getMemberDto() {
        return memberDto;
    }

    public void setMemberDto(MemberRegistrationDto memberDto) {
        this.memberDto = memberDto;
    }
}
