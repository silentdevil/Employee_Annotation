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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.Transient;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.HashSet;

import javax.persistence.Cacheable;
import org.hibernate.annotations.*;
//import org.hibernate.cache.CacheConcurrencyStrategy;

@Entity
@Table(name = "employees")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "employeeid")
	private long employeeId;
	
	@Column(name = "lastname")
	private String lastname = "";
	private String firstname = "";
	private String middlename = "";
	private String suffix = "";
	private String title = "";
	@ManyToOne
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name = "address")
	private Address address;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	private Float gwa;
	
	@Temporal(TemporalType.DATE)
	private Date datehired;
	private Boolean currentlyHired;
	
	@OneToOne(mappedBy = "employee", fetch=FetchType.EAGER)
	@Cascade({CascadeType.ALL})
	private Contact contact;
	
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@ManyToMany(fetch=FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinTable(name = "employee_role", 
	joinColumns = { @JoinColumn(name = "employeeid")}, 
		inverseJoinColumns = { @JoinColumn(name = "roleid")})
	private Set<Role> roles = new HashSet<>();

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
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
	
	public Date getDatehired() {
		return datehired;
	}
	
	public void setDatehired(Date datehired) {
		this.datehired = datehired;
	}
	
	public Boolean getCurrentlyHired() {
		return currentlyHired;
	}	
	
	public void setCurrentlyHired(Boolean currentlyHired) {
		this.currentlyHired = currentlyHired;
	}
	
	public Contact getContact() {
		return contact;
	}
	
	public void setContact(Contact contact) {
		this.contact = contact;
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
		sb.append(employeeId + "\t").append(lastname + "," + firstname + " " + middlename + " " + suffix);
		return sb.toString();
	}
}
