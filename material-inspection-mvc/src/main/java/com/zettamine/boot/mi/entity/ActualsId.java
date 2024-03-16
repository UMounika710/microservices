package com.zettamine.boot.mi.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode
public class ActualsId implements Serializable {
	private InspectionLot lot;
	private MaterialCharacteristics character;
	
	
	@Override
	public int hashCode() {
		return Objects.hash(character, lot);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActualsId other = (ActualsId) obj;
		return Objects.equals(character, other.character) && Objects.equals(lot, other.lot);
	}
	
	

}
