package com.bca.travel.repository;

import com.bca.travel.model.HolidayPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayPackageRepository extends JpaRepository<HolidayPackage,Integer> {
}
