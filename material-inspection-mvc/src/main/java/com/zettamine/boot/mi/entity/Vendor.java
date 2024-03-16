package com.zettamine.boot.mi.entity;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VNDR")
@SQLDelete(sql = "UPDATE vndr SET deleted = 1 WHERE id=?")
@Where(clause = "deleted=0")
public class Vendor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id_gen")
	@SequenceGenerator(name = "id_gen", sequenceName = "vndr_seq", initialValue = 5001, allocationSize = 1)
	@Column(name = "ID")
	private Integer vendorId;
	
	@Column(name = "NAME")
	@NotBlank(message = " * required")
	private String vendorName;
	
	@Column(name = "TYPE")
	@NotEmpty(message = "please fill out this field")
	private String vendorType;
	
	@Column(name = "LOC")
	@Size(min = 3, max = 20)
	private String location;
	
	@Column(name = "EMAIL")
	@Email
	private String email;
	
	
	private Integer deleted = 0;
	
	@OneToMany(mappedBy = "vendor")
	@JsonIgnore
	private List<InspectionLot> lot = new ArrayList<>();
	
	
	
	public Vendor(String vendorName, String vendorType, String location, String email) {
		super();
		this.vendorName = vendorName;
		this.vendorType = vendorType;
		this.location = location;
		this.email = email;
	}



	/*
	 * public Vendor(Integer vendorId, String vendorName, String vendorType, String
	 * location, String email, boolean) { super(); this.vendorId = vendorId;
	 * this.vendorName = vendorName; this.vendorType = vendorType; this.location =
	 * location; this.email = email; }
	 */
	
	

}
