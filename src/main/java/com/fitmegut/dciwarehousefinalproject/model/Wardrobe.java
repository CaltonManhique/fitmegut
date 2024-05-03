package com.fitmegut.dciwarehousefinalproject.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "wardrobes")
public class Wardrobe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String clothingCategories;
    private boolean posted;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "wardrobe", cascade = CascadeType.ALL)
    private List<Item> clothing;

    public Wardrobe() {}

    public Wardrobe(String clothingCategories, boolean posted) {
        this.clothingCategories = clothingCategories;
        this.posted = posted;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Item> getClothing() {
        return clothing;
    }

    public void setClothing(List<Item> clothing) {
        this.clothing = clothing;
    }
}
