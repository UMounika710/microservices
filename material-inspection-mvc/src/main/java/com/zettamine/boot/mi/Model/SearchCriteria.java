package com.zettamine.boot.mi.Model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
	
	@PastOrPresent
	@NotNull
	private LocalDate fromDate;
	@NotNull
	private LocalDate toDate;
	private String materialId;
	private String plantId;
	private String status;

}
