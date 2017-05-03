package com.exist.employee;

import java.util.*;

public class DtoMapper {

	public EmployeeDto mapEmployeeDto(Employee employee) {
		if(employee == null) {
			return null;
		}
		EmployeeDto employeeDto = new EmployeeDto();
		NameDto employeeName = new NameDto();
		Name empName = employee.getEmployeeName();
		try {
			employeeDto.setEmployeeId(employee.getEmployeeId());
			employeeName.setLastName(empName.getLastName());
			employeeName.setFirstName(empName.getFirstName());
			employeeName.setMiddleName(empName.getMiddleName());
			employeeName.setSuffix(empName.getSuffix());
			employeeName.setTitle(empName.getTitle());
			employeeDto.setEmployeeName(employeeName);
			employeeDto.setAddress(mapAddressDto(employee.getAddress()));
			employeeDto.setBirthday(employee.getBirthday());
			employeeDto.setGwa(employee.getGwa());
			employeeDto.setDateHired(employee.getDateHired());
			employeeDto.setCurrentlyHired(employee.getCurrentlyHired());
			Set<ContactDto> contactDto = mapContactDto(employee, employeeDto);

			employeeDto.setContacts(contactDto);
			employeeDto.setRoles(mapRoleSetDto(employee.getRoles()));
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return employeeDto;
	}
	
	public Set<EmployeeDto> mapEmployeeSetDto(Set<Employee> employeeSet) {
		List<EmployeeDto> employees = new ArrayList<>();
		employeeSet.forEach(e -> employees.add(mapEmployeeDto(e)));
		return new HashSet<EmployeeDto>(employees);
	}
	
	public Set<RoleDto> mapRoleSetDto(Set<Role> roleSet) {
		List<RoleDto> roles = new ArrayList<>();
		roleSet.forEach(r -> roles.add(mapRoleDto(r)));
		return new HashSet<RoleDto>(roles);
	}
	
	public  AddressDto mapAddressDto(Address address) {
		AddressDto addressDto = new AddressDto();
		try {
			
			addressDto.setStreetNo(address.getStreetNo());
			addressDto.setStreet(address.getStreet());
			addressDto.setBrgy(address.getBrgy());
			addressDto.setCity(address.getCity());
			addressDto.setZipcode(address.getZipcode());
		} catch(Exception ex) {
			return null;
		}
		return addressDto;
	}

	public ContactDto mapContactSingle(Contact c, EmployeeDto employee){
		ContactDto contact = new ContactDto().setContactId(c.getContactId());
		contact.setEmployee(employee);
		contact.setContactType(c.getContactType());
		contact.setContactInfo(c.getContactInfo());
		return contact;
	}
	
	public Set<ContactDto> mapContactDto(Employee employee, EmployeeDto employeeDto) {
		Set<ContactDto> contacts = new TreeSet<>();
		if(employee.getContacts() == null) {
			employee.setContacts(new TreeSet<>());
		}
		try {
			employee.getContacts().forEach( c -> contacts.add(mapContactSingle(c,employeeDto)));
		} catch(Exception ex) {
			System.out.println("Null contact passed");
			ex.printStackTrace();
			return null;
		}
			employeeDto.setContacts(contacts);
			return contacts;	
	}
	
	public RoleDto mapRoleDto(Role role) {
		
		RoleDto roleDto = new RoleDto();
		try {
			roleDto.setRoleId(role.getRoleId());
			roleDto.setRole(role.getRole());
		} catch (Exception ex) {
			System.out.println("Null role passed");
			ex.printStackTrace();
			return null;
		}
			return roleDto;
	}

}
