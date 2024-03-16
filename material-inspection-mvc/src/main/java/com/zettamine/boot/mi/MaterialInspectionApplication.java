package com.zettamine.boot.mi;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zettamine.boot.mi.entity.Vendor;
import com.zettamine.boot.mi.service.VendorService;

@SpringBootApplication
public class MaterialInspectionApplication {

	
	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(MaterialInspectionApplication.class, args);
		
		/*
		VendorService vndrService = context.getBean(VendorService.class);
		
		List<Vendor> vndrList = Arrays.asList(
				new Vendor("ABC Suppliers", "distributor", "Hyderabad", "abc@gmail.com"),
				new Vendor("XYZ Suppliers", "manufacturer", "Kurnool", "xyz@gmail.com")
				);
		
		vndrService.saveVendor(vndrList);
		
		List<Vendor> vndrLists = vndrService.getVendorDetails();
		vndrLists.forEach(vndr -> System.out.println(vndr));
		*/
	}

}
