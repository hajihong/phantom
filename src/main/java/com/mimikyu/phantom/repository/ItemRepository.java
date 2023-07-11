package com.mimikyu.phantom.repository;

import com.mimikyu.phantom.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
