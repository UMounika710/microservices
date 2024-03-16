package com.zettamine.boot.mi.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mat_isp_ch")
@SQLDelete(sql = "update mat_isp_ch set deleted = 1 where ch_id=?")
@Where(clause = "deleted=0")
//@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MaterialCharacteristics {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id_gen")
	@SequenceGenerator(name = "id_gen", sequenceName = "ch_seq", initialValue = 101, allocationSize = 1)
	private Integer chId;
	private String chDesc;
	private Integer tolUl;
	private Integer tolLl;
	private String uom;
	private Integer deleted = 0;
	

	@ManyToOne
	@JoinColumn(name = "mat_id")
	@JsonIgnore
	private Material material;
	
	@OneToMany(mappedBy = "character")
	@JsonIgnore
	private List<InspectionActuals> actuals = new ArrayList<>();
	
	public MaterialCharacteristics(String chDesc, Integer tolUl, Integer tolLl, String uom, Material material) {
		super();
		this.chDesc = chDesc;
		this.tolUl = tolUl;
		this.tolLl = tolLl;
		this.uom = uom;
		this.material = material;
	}

	
}
