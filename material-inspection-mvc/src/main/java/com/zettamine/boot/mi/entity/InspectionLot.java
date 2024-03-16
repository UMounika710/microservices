package com.zettamine.boot.mi.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ISP_LOT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update isp_lot set deleted = 1 where lot_id=?")
@Where(clause = "deleted=0")
public class InspectionLot {
	@Id
	private Integer lotId;
	
	@ManyToOne
	@JoinColumn(name = "material_id")
	@JsonIgnore
	@NotNull
	private Material material;
	
	@ManyToOne
	@JoinColumn(name = "vendor_id")
	@JsonIgnore
	@NotNull
	private Vendor vendor;
	
	@ManyToOne
	@JoinColumn(name = "plant_id")
	@JsonIgnore 
	@NotNull
	private Plant plant;
	
	@PastOrPresent
	private LocalDate createdOn;
	
	private LocalDate ispStDate;
	
	private LocalDate ispEnDate;
	private String result;
	private String remark;
	private Integer deleted = 0;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy = "lot")
	@JsonIgnore
	private List<InspectionActuals> actuals = new ArrayList<>();
	
	public InspectionLot(LocalDate ispEnDate, String result, String remark,
			User user) {
		super();
		this.ispEnDate = ispEnDate;
		this.result = result;
		this.remark = remark;
		this.user = user;
	}



	public InspectionLot(Integer lotId, Material material, Vendor vendor, Plant plant, LocalDate createdOn,LocalDate ispStDate) {
		super();
		this.lotId = lotId;
		this.material = material;
		this.vendor = vendor;
		this.plant = plant;
		this.createdOn = createdOn;
		this.ispStDate = ispStDate;
	}
	
	

}
