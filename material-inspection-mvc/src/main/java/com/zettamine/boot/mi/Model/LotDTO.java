package com.zettamine.boot.mi.Model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LotDTO {
	@NotNull
	private Integer lotId;
	
	@NotNull
	private LocalDate createdOn;
	
	@NotNull
	private LocalDate ispStDate;
	
	@NotNull(message = "provide valid plant Id")
	private String plantId;
	
	@NotNull(message = "provide valid material Id")
	private String materialId;
	
	@NotNull(message = "provide valid vendor Id")
	private Integer vendorId;
	

}
