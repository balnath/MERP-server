package com.merp.server.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Permission extends BaseModel {

	@NotNull
	private String name;
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
