package com.zettamine.boot.mi.entity;

import java.io.Serializable;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ActualsId.class)
@SQLDelete(sql = "update inspection_actuals set deleted = 1 where ch_id=? and lot_id=?")
@Where(clause = "deleted = 0")
public class InspectionActuals implements Serializable {

	/*
	 * @Id private Integer lotId;
	 * 
	 * @Id private Integer chId;
	 */

	@Id
	@ManyToOne
	@JoinColumn(name = "lot_Id")
	@JsonIgnore
	private InspectionLot lot;

	@Id
	@ManyToOne
	@JoinColumn(name = "ch_Id")
	@JsonIgnore
	private MaterialCharacteristics character;

	private Integer maxMes;
	private Integer minMes;
	private Integer deleted = 0;
	
	@AssertTrue(message = "MAX MES should be greater MIN MES")
	private boolean isValidMinMax() {
		return maxMes >= minMes;
	}

}
