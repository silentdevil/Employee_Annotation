package com.exist.employee;
import java.util.Date;
import java.util.Set;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.List;

import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.Embedded;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.HashSet;

import javax.persistence.Cacheable;
import org.hibernate.annotations.*;


@Entity
@Table(name = "employees")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "employee_id")
	private long employeeId;
	
	@Embedded
	private Name employeeName;

	@Embedded
	private Address address;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	private Float gwa;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_hired")
	private Date dateHired;

	@Column(name = "currently_hired")
	private Boolean currentlyHired;
	
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@OneToMany(mappedBy="employee",fetch=FetchType.EAGER)
	@Cascade({CascadeType.ALL})
	private Set<Contact> contacts;
	
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@ManyToMany(fetch=FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinTable(name = "employee_role", 
		joinColumns = { @JoinColumn(name = "employee_id")}, 
			inverseJoinColumns = { @JoinColumn(name = "role_id")})
	private Set<Role> roles = new HashSet<>();

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public Name getEmployeeName(){
		return employeeName;
	}

	public void setEmployeeName(Name employeeName){
		this.employeeName = employeeName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
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
	
	public Set<Contact> getContacts() {
		return contacts;
	}
	
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public Employee addRole(Role role) {
		roles.add(role);
		return this;
	}
	
	public Employee deleteRole(Role role) {
		roles.remove(role);
		return this;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(employeeId + "\t").append(employeeName);
		return sb.toString();
	}
}
