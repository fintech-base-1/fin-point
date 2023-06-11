package com.fp.finpoint.domain.invest.repository;

import com.fp.finpoint.domain.invest.dto.Item;
import org.springframework.stereotype.Repository;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepository {

    private final Map<Long, Item> store = new HashMap<>();
    private long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }
}
