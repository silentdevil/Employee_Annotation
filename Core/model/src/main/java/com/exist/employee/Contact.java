package com.exist.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.GeneratedValue;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Embeddable;
import javax.persistence.GenerationType;
import javax.persistence.Cacheable;
import org.hibernate.annotations.*;

@Entity
@Table(name = "contacts")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Contact implements java.io.Serializable, Comparable<Contact>
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="contact_id")
	private long contactId;

	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="employee_id")
	private Employee employee;



	@Column(name="contact_type")
	private String contactType;

	@Column(name="contact_info")
	private String contactInfo;

	

	public long getContactId(){
		return contactId;
	}

	public void setContactId(int contactId){
		this.contactId = contactId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
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
		return contactType + " : " + contactInfo + "\n\t";
	}

	public int compareTo(Contact contact){
		return (contactType+contactInfo).compareTo((contact.getContactType()+contact.getContactInfo()));
	}

	@Override
   	public boolean equals(Object obj) {
       if(obj == null || getClass() != obj.getClass())
         return false;

        Contact add2 = (Contact) obj;

         return this.contactType.equals(add2.getContactType()) && 
         		this.contactInfo.equals(add2.getContactInfo());
        
   }

   @Override
   public int hashCode() {
        return java.util.Objects.hash(contactType,contactInfo);
    }

}

enum ContactType {
	EMAIL(1,"EMAIL"),
	MOBILE(2,"MOBILE"),
	LANDLINE(3,"LANDLINE");

	private int id;
	private String message;
	public static int SIZE = ContactType.values().length;

	ContactType(int id, String message) {
		this.id = id;
		this.message = message;
	}

	public String toString() {
	  return id + ": " + message; 
	}

	public String getMessage() {
		return message;
	}



	public static ContactType valueOf(int i) {
		for(ContactType t : ContactType.values()) {

			if(t.id == i)
				return t;
			}
		return null;

	}


}
