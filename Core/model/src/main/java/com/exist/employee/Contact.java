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

import javax.persistence.Cacheable;
import org.hibernate.annotations.*;

@Entity
@Table(name = "contacts")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Contact implements java.io.Serializable
{
	
	
	//@Column(name="employeeid")
	//@GeneratedValue(generator="gen")
	//@GenericGenerator(name="gen", strategy="foreign", 
	//	parameters={@Parameter(name = "property", value="employee")})
	//private long employeeId;
	@Id
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;

	@Column(name="contact_type")
	private String contactType;

	@Column(name="contact_info")
	private String contactInfo;

	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	

}
