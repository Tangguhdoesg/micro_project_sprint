package com.bca.travel.services;

import com.bca.travel.model.Customer;
import com.bca.travel.model.HolidayPackage;
import com.bca.travel.repository.HolidayPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayPackageService {

    @Autowired
    private HolidayPackageRepository holidayPackageRepo;


    public HolidayPackage save(HolidayPackage holidayPackage){
        return holidayPackageRepo.save(holidayPackage);
    }

    public HolidayPackage findById(Integer id){
        return holidayPackageRepo.findById(id).get();
    }

    public List<HolidayPackage> findAll(){
        return holidayPackageRepo.findAll();
    }

    public void delete(HolidayPackage holidayPackage){
        holidayPackageRepo.delete(holidayPackage);
    }
}
