package com.example.shop_api.repository;

import com.example.shop_api.entity.Products;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryProductRepository implements ProductsRepository {
    private Map<Integer, Products> data =new HashMap<>();
    private  int nextId = 1;
    public InMemoryProductRepository() {

        save(new Products("Áo", 200000));
        save(new Products("Quần", 300000));
        save(new Products("Mũ", 100000));
    }
    @Override
    public int save(Products products) {
        int id = nextId;
        nextId++;
        data.put(id, products);
        return id;
    }

    @Override
    public Optional<Products> findbyId(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Products> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void update(int id, Products products) {
         data.put(id,products);
    }

    @Override
    public boolean deletebyId(int id) {
        return data.remove(id) != null;
    }
}
