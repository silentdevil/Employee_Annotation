package com.exist.employee;
import javax.persistence.Embeddable;
import javax.persistence.Column;

import org.hibernate.annotations.*;

@Embeddable
public class Name{

	@Column(name = "last_name")
	private String lastName = "";

	@Column(name = "first_name")
	private String firstName = "";

	@Column(name = "middle_name")
	private String middleName = "";

	private String suffix = "";
	private String title = "";

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(lastName + "," + firstName + " " + middleName + " " + suffix);
		return sb.toString();
	}

}