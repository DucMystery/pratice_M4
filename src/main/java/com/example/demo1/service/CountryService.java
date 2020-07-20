package com.example.demo1.service;

import com.example.demo1.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {

    Iterable<Country> findAll();
    Country findById(Long id);
    void save(Country country);
    void remove(Long id);
    Page<Country> findAll(Pageable pageable);
    Page<Country> findAllByNameContaining(String name, Pageable pageable);
}
