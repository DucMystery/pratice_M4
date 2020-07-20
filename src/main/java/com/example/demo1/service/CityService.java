package com.example.demo1.service;

import com.example.demo1.model.City;
import com.example.demo1.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface CityService {

    Iterable<City> findAll();
    City findById(Long id);
    void  save(City city);
    void remove(Long id);
    Page<City> findAll(Pageable pageable);
    Page<City> findAllByNameContaining(String name, Pageable pageable);
}
