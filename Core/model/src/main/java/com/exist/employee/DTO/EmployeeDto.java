package com.exist.employee;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;


public class EmployeeDto {
	
	private long employeeId;
	private String lastName = "";
	private String firstName = "";
	private String middleName = "";
	private String suffix = "";
	private String title = "";
	private AddressDto address;
	
	private Date birthday;
	private Float gwa;
	
	private Date dateHired;
	private Boolean currentlyHired;
	
	private ContactDto contact;

	private Set<RoleDto> roles = new HashSet<>();

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

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

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Float getGwa() {
		return gwa;
	}
	
	public void setGwa(Float gwa) {
		this.gwa = gwa;
	}
	
	public Date getDateHired() {
		return dateHired;
	}
	
	public void setDateHired(Date dateHired) {
		this.dateHired = dateHired;
	}
	
	public Boolean getCurrentlyHired() {
		return currentlyHired;
	}	
	
	public void setCurrentlyHired(Boolean currentlyHired) {
		this.currentlyHired = currentlyHired;
	}
	
	public ContactDto getContact() {
		return contact;
	}
	
	public void setContact(ContactDto contact) {
		this.contact = contact;
	}
	
	public Set<RoleDto> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}
	
	
}
