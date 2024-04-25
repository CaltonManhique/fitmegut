package com.fitmegut.dciwarehousefinalproject.repository;

import com.fitmegut.dciwarehousefinalproject.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
