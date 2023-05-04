package com.bca.travel.controller;

import com.bca.travel.model.HolidayPackage;
import com.bca.travel.repository.HolidayPackageRepository;
import com.bca.travel.services.HolidayPackageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("holidayPackage")
public class HolidayPackageController {

	@Autowired
	private HolidayPackageService holidayService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<HolidayPackage> getAll(){
        List<HolidayPackage> x = holidayService.findAll();
        return x;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HolidayPackage getHolidayPackageById(@PathVariable("id") Integer id){
       return holidayService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteHolidayPackage(@PathVariable("id") Integer id){
    	HolidayPackage data = getHolidayPackageById(id);
        holidayService.delete(data);
        return "Delete Succesfull";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public HolidayPackage createPackage(@RequestBody HolidayPackage holiPackage){
        return holidayService.save(holiPackage);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HolidayPackage updatePackage(@PathVariable("id") Integer id, @RequestBody HolidayPackage holiPackage){
        HolidayPackage oldPackage = holidayService.findById(id);

        oldPackage.setPackageName(holiPackage.getPackageName());
        oldPackage.setPackageInformation(holiPackage.getPackageInformation());
        oldPackage.setPackagePrice((holiPackage.getPackagePrice()));

        return holidayService.save(oldPackage);

    }

}
