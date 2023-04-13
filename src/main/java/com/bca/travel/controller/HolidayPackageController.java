package com.bca.travel.controller;

import com.bca.travel.model.HolidayPackage;
import com.bca.travel.repository.HolidayPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("holidayPackage")
public class HolidayPackageController {

    @Autowired
    private HolidayPackageRepository holidayPackageRepository;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<HolidayPackage> getAll(){
        List<HolidayPackage> x = holidayPackageRepository.findAll();
        return x;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HolidayPackage getHolidayPackageById(@PathVariable("id") Integer id){
       return holidayPackageRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteHolidayPackage(@PathVariable("id") Integer id){
        holidayPackageRepository.deleteById(id);
        return "Delete Succesfull";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public HolidayPackage createPackage(@RequestBody HolidayPackage holiPackage){
        return holidayPackageRepository.save(holiPackage);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HolidayPackage updatePackage(@PathVariable("id") Integer id, @RequestBody HolidayPackage holiPackage){
        HolidayPackage oldPackage = holidayPackageRepository.findById(id).get();

        oldPackage.setPackageName(holiPackage.getPackageName());
        oldPackage.setPackageInformation(holiPackage.getPackageInformation());
        oldPackage.setPackagePrice((holiPackage.getPackagePrice()));

        return holidayPackageRepository.save(oldPackage);

    }

}
