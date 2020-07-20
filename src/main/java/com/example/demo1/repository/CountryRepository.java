package com.example.demo1.repository;

import com.example.demo1.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountryRepository extends PagingAndSortingRepository<Country,Long> {
    Page<Country> findAllByNameContaining(String name, Pageable pageable);
}
