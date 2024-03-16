package com.zettamine.boot.mi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.boot.mi.entity.Material;

public interface MaterialRepository extends JpaRepository<Material, String> {

}
