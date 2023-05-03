package com.bca.travel.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="holiday_package")
public class HolidayPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "packageName")
    private String packageName;
    @Column(name = "packageInformation")
    private String packageInformation;
    @Column(name = "packagePrice")
    private Integer packagePrice;

}
