package com.example.shop_api.repository;

import com.example.shop_api.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryCategoryRepository implements CategoryRepository {
    private Map<Integer, Category> data = new HashMap<>();
    private int nextId = 1;
    public InMemoryCategoryRepository(){
        save(new Category("Áo"));
        save(new Category("Quần"));
        save(new Category("Mũ"));
        save(new Category("Dép"));
        save(new Category("Váy"));
    }
    @Override
    public int save(Category category) {
        int id = nextId;
        nextId++;
        data.put(id, category);
        return id;
    }

    @Override
    public Optional<Category> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Category> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Category update(int id, Category category) {
        return data.put(id, category);
    }

    @Override
    public boolean deleteById(int id) {
        return data.remove(id) != null;
    }
}
