package com.example.demo1.controller;

import com.example.demo1.model.City;
import com.example.demo1.model.Country;
import com.example.demo1.service.CityService;
import com.example.demo1.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @ModelAttribute("/countries")
    public Iterable<Country> getCountry(){
        return countryService.findAll();
    }


    @GetMapping("/home")
    public ModelAndView showHome(@RequestParam("s")Optional<String> s, @PageableDefault(size = 18)Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("home");
        Page<City> cities;
        if (s.isPresent()){
            cities = cityService.findAllByNameContaining(s.get(),pageable);
        }else {
            cities = cityService.findAll(pageable);
        }
        modelAndView.addObject("cities",cities);
        return modelAndView;
    }

    @GetMapping("/create-city")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("createCity");
        modelAndView.addObject("countries",countryService.findAll());
        modelAndView.addObject("city",new City());
        return modelAndView;
    }

    @PostMapping("/create-city")
    public ModelAndView addCity(@ModelAttribute("city") City city){
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("createCity");
        modelAndView.addObject("message","Thêm Thành Phố Thành Công !");
        modelAndView.addObject("city",new City());
        modelAndView.addObject("countries",countryService.findAll());
        return modelAndView;

    }
    @GetMapping("/edit-city/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        City city = cityService.findById(id);
        if (city!=null){
            ModelAndView modelAndView = new ModelAndView("editCity");
            modelAndView.addObject("city",city);
            modelAndView.addObject("countries",countryService.findAll());
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("editCity");
            modelAndView.addObject("message","Edit Error !");
            return modelAndView;
        }
    }

    @PostMapping("/edit-city")
    public ModelAndView updateCity(@ModelAttribute("city") City city){
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("editCity");
        modelAndView.addObject("city",city);
        modelAndView.addObject("message","Sửa Thông Tin Thành Công !");
        return modelAndView;
    }

    @GetMapping("/delete-city/{id}")
    public ModelAndView showDelete(@PathVariable Long id){
        City city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("deleteCity");
        modelAndView.addObject("city",city);
        return modelAndView;
    }

    @PostMapping("/delete-city")
    public ModelAndView deleteCity(@ModelAttribute("city") City city){
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        cityService.remove(city.getId());
        return modelAndView;
    }

    @GetMapping("/showCity/{id}")
    public ModelAndView showCity(@PathVariable Long id){
        City city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("InfCity");
        modelAndView.addObject("city",city);
        return modelAndView;

    }
}
