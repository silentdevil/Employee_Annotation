package com.exist.employee;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class EmployeeDto {
	
	private long employeeId;
	private NameDto employeeName;
	private AddressDto address;
	
	private Date birthday;
	private Float gwa;
	
	private Date dateHired;
	private Boolean currentlyHired;
	
	private Set<ContactDto> contacts;

	private Set<RoleDto> roles = new HashSet<>();

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public NameDto getEmployeeName(){
		return employeeName;
	}

	public void setEmployeeName(NameDto employeeName){
		this.employeeName = employeeName;
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
	
	public Set<ContactDto> getContacts() {
		return contacts;
	}
	
	public void setContacts(Set<ContactDto> contacts) {
		this.contacts = contacts;
	}
	
	public Set<RoleDto> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}
	
	
}
