package com.zettamine.boot.mi.service;

import java.util.List;
import java.util.Optional;

import com.zettamine.boot.mi.entity.Vendor;

public interface VendorService {
	public List<Vendor> getAllVendorDetails();
	public boolean saveVendor(Vendor vndr);
	public Optional<Vendor> getVendorById(Integer id);
	
	public boolean deleteVendor(Integer id);
	//public void saveVendor(List<Vendor> vndrList);

}
