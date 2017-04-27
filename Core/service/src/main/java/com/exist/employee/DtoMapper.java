package com.exist.employee;

import java.util.*;

public class DtoMapper {

	public EmployeeDto mapEmployeeDto(Employee employee) {
		if(employee == null) {
			return null;
		}
		EmployeeDto employeeDto = new EmployeeDto();
		try {
			employeeDto.setEmployeeId(employee.getEmployeeId());
			employeeDto.setLastName(employee.getLastName());
			employeeDto.setFirstName(employee.getFirstName());
			employeeDto.setMiddleName(employee.getMiddleName());
			employeeDto.setSuffix(employee.getSuffix());
			employeeDto.setTitle(employee.getTitle());
			employeeDto.setAddress(mapAddressDto(employee.getAddress()));
			employeeDto.setBirthday(employee.getBirthday());
			employeeDto.setGwa(employee.getGwa());
			employeeDto.setDateHired(employee.getDateHired());
			employeeDto.setCurrentlyHired(employee.getCurrentlyHired());
			//ContactDto contactDto = mapContactDto(employee.getContacts());
			//contactDto.setEmployee(employeeDto);
			//employeeDto.setContact(contactDto);
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
	
	public  ContactDto mapContactDto(Contact contact) {
		ContactDto contactDto = new ContactDto();
		try {
			
		} catch(Exception ex) {
			System.out.println("Null contact passed");
			return null;
		}
			return contactDto;
		
	}
	
	public RoleDto mapRoleDto(Role role) {
		
		RoleDto roleDto = new RoleDto();
		try {
			roleDto.setRoleId(role.getRoleId());
			roleDto.setRole(role.getRole());
			//role.getEmployees();
			//roleDto.setEmployees(mapEmployeeSetDto(role.getEmployees()));
		} catch (Exception ex) {
			System.out.println("Null role passed");
			ex.printStackTrace();
			return null;
		}
			return roleDto;
	}
	
	
	
	

}
