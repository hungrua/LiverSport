package com.liversportweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class CategoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="code")
	private String code;
	
	@OneToMany(mappedBy="category")
	private List <SportFieldEntity> sportField = new ArrayList<SportFieldEntity>();
	
	
	public List<SportFieldEntity> getSportField() {
		return sportField;
	}

	public void setSportField(List<SportFieldEntity> sportField) {
		this.sportField = sportField;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
