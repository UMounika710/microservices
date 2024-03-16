package com.zettamine.boot.mi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zettamine.boot.mi.entity.Vendor;
import com.zettamine.boot.mi.repository.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {
	private VendorRepository vndRepo;
	
	@Autowired
	public VendorServiceImpl(VendorRepository vndRepo) {
		super();
		this.vndRepo = vndRepo;
	}

	@Override
	public List<Vendor> getAllVendorDetails() {
		List<Vendor> vendorList = vndRepo.findAll();
		return vendorList;
	}


	@Override
	public Optional<Vendor> getVendorById(Integer id) {
		Optional<Vendor> vndr = vndRepo.findById(id);
		return vndr;
	}

	@Override
	public boolean saveVendor(Vendor vndr) {
		Vendor savedObj = vndRepo.save(vndr);
		return savedObj.getVendorId() != null;	
	}
	
	
	@Override
	public boolean deleteVendor(Integer id) {
		vndRepo.deleteById(id);
		//vndRepo.findById(id);
		return vndRepo.findById(id).isEmpty();
	}
	
	/*
	@Override
	public void saveVendor(List<Vendor> vndrList) {
		vndRepo.saveAll(vndrList);
		
	}
	*/

}
