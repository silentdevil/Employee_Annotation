package com.exist.employee;

public class ContactDto implements Comparable<ContactDto>{
	

	private long employeeId;
	
	private EmployeeDto employee;
	private String contactType;
	private String contactInfo;
	
	
	public long getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public EmployeeDto getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDto employee) {
		this.employee = employee;
	}

	public String getContactType(){
		return contactType;
	}

	public void setContactType(String contactType){
		this.contactType = contactType;
	}

	public String getContactInfo(){
		return contactInfo;
	}

	public void setContactInfo(String contactInfo){
		this.contactInfo = contactInfo;
	}
	
	
	public String toString() {
	   return contactType + " : " + contactInfo;
	}

	public int compareTo(ContactDto cto) {
		return (contactType.compareTo(cto.getContactType()));
	}

}