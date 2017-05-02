package com.exist.employee;

public class ContactDto implements Comparable<ContactDto>{
	
	private long contactId;
	private long employeeId;
	
	private EmployeeDto employee;
	private String contactType;
	private String contactInfo;


	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}
	
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
		return (contactType+contactInfo).compareTo((cto.getContactType()+cto.getContactInfo()));
	}

	@Override
   	public boolean equals(Object obj) {
       if(obj == null || getClass() != obj.getClass())
         return false;

        ContactDto add2 = (ContactDto) obj;

         return this.contactType.equals(add2.getContactType()) && 
         		this.contactInfo.equals(add2.getContactInfo());
        
   }

   @Override
   public int hashCode() {
        return java.util.Objects.hash(contactType,contactInfo);
    }

}