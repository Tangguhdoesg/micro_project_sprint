package com.bca.travel.controller;

import com.bca.travel.model.HolidayPackage;
import com.bca.travel.repository.HolidayPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("holidayPackage")
public class HolidayPackageController {

    @Autowired
    private HolidayPackageRepository holidayPackageRepository;

    @GetMapping("/all")
    public @ResponseBody List<HolidayPackage> getAll(){

        List<HolidayPackage> x = holidayPackageRepository.findAll();
        System.out.println(x.get(1).getPackageName());

        return x;
    }
}
