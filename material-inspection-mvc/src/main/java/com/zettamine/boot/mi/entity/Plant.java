package com.zettamine.boot.mi.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "update plant set deleted = 1 where plant_id=?")
@Where(clause = "deleted=0")

public class Plant {

	@Id
	@Pattern(regexp = "^[a-zA-Z0-9 -]*$", message = "Invalid input. Please enter a valid alphanumeric string with spaces and hyphens allowed")
	private String plantId;
	private String plantName;
	private String location;
	private String plantManager;
	private Integer deleted = 0;

	@Pattern(regexp = "^[0-9]{10}", message = "not a valid number")
	private String contact;

	@OneToMany(mappedBy = "plant", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<InspectionLot> lot = new ArrayList<>();
}
