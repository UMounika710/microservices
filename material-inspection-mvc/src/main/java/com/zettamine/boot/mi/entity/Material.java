package com.zettamine.boot.mi.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.repository.query.Param;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update material set deleted = 1 where id=?")
@Where(clause = "deleted=0")
public class Material {
	@Id
	@Pattern(regexp = "^[a-zA-Z0-9]+(?:[-\\\\s][a-zA-Z0-9]+)*$", message = "will accept only alphanumeric values with '-' or space")
	//@Pattern(regexp = "^[a-zA-Z0-9\\- ]$", message = "will accept only alphanumeric values with '-' or space")
	private String id;
	private String descpt;
	private String type;
	private Float price;
	private String ck;
	private Integer deleted;
	
	@OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<InspectionLot> lot = new ArrayList<>();
	
	@OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<MaterialCharacteristics> chList = new ArrayList<>();

}
