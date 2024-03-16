package com.zettamine.boot.mi.repository;

import java.io.Serializable;

import org.aspectj.weaver.tools.Trace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zettamine.boot.mi.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Serializable>{

}
