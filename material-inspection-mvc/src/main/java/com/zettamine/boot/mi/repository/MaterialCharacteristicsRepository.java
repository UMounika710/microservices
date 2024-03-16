package com.zettamine.boot.mi.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.boot.mi.entity.MaterialCharacteristics;

public interface MaterialCharacteristicsRepository extends JpaRepository<MaterialCharacteristics, Serializable> {

}
