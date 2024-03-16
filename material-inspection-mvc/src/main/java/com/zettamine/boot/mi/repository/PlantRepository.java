package com.zettamine.boot.mi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.boot.mi.entity.Plant;

public interface PlantRepository extends JpaRepository<Plant, String>{

}
