package com.example.demo1.repository;

import com.example.demo1.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CityRepository extends PagingAndSortingRepository<City,Long> {
    Page<City> findAllByNameContaining(String name, Pageable pageable);
}
