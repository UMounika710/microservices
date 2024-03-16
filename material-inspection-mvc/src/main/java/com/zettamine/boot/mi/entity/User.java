package com.zettamine.boot.mi.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USR_DTLS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id_gen")
	@SequenceGenerator(name = "id_gen", sequenceName = "user_seq", initialValue = 1081, allocationSize = 1)
	private Integer id;
	private String userName;
	private String password;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<InspectionLot> lot = new ArrayList<>();
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

}
