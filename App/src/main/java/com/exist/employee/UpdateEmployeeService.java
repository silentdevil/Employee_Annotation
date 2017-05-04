package com.exist.employee;
import java.util.*;
public class UpdateEmployeeService {


	public static EmployeeDto addEmployeeRole(EmployeeDto employee) throws Exception {
		try {
			Set<RoleDto> roles = employee.getRoles();
			roles.add(EmployeeUI.setRoleToEmployee(employee));	
			employee.setRoles(roles);
			return employee;
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new Exception("Can't find role");
		}
	}

	public static EmployeeDto deleteEmployeeRole(EmployeeDto employee, RoleDto role) throws Exception {
		Set<RoleDto> roles = employee.getRoles();
		//RoleDto role = mapper.mapRoleDto(empService.getRoleById(Long.valueOf(InputManager.getPositiveNumber("ROLE","EMPTY_NOT_ALLOWED"))));
		roles.remove(role);	
		employee.setRoles(roles);
		return employee;
	}
	
	public static EmployeeDto addEmployeeContact(EmployeeDto employee) throws Exception {
		System.out.print("\033\143");
		Set<ContactDto> contacts = EmployeeUI.createContacts(employee);
		
		employee.setContacts(contacts);
		return employee;
	}

	public static ContactDto updateContact(ContactDto contact) throws Exception {
		try {
			String string = "";
			switch(contact.getContactType()) {
				case "EMAIL": {
					string = InputManager.enterString("Email","");
					string = RegexUtils.isValidEmail(string) ? string : "";
					System.out.print((RegexUtils.isValidEmail(string)) ? "":"Not a valid email\n");
					break;
				} case "MOBILE": {
					string = InputManager.enterString("Mobile  [XXXX-XXX-XXXX]","");
					string = RegexUtils.isValidMobile(string) ? string : "";
					System.out.print((RegexUtils.isValidMobile(string)) ? "":"Not a valid mobile\n");
					break;
				} case "LANDLINE": {
					string = InputManager.enterString("Landline  [XXX-XXXX]","");
					string = RegexUtils.isValidLandline(string) ? string : "";
					System.out.print((RegexUtils.isValidLandline(string)) ? "":"Not a valid mobile\n");
					break;
				} default: {
					break;
				}
			}

			if(string != null && !string.isEmpty())
				contact.setContactInfo(string);
		} catch(Exception ex) {
			ex.printStackTrace();
			System.err.println("index not found");
		}
		System.out.println("printed from UES:63" + contact);
		return contact;
	}

	public static void updateEmployeeContact(EmployeeDto employee, ContactDto contact) throws Exception {
		//Set<ContactDto> contacts = employee.getContacts();
		contact = updateContact(contact);
	}

	public static EmployeeDto delEmployeeContact(EmployeeDto employee, ContactDto contact) throws Exception {
		System.out.print("\033\143");
		Set<ContactDto> contacts = employee.getContacts();
		try {
			contacts.remove(contact);
		} catch(Exception ex) {
			ex.printStackTrace();
			System.err.println("index not found");
		}

		System.out.println(contacts);
		employee.setContacts(contacts);
		return employee;
	}

	public static EmployeeDto updateEmployeeName(EmployeeDto employee) throws Exception {
			employee.setEmployeeName(EmployeeUI.createNameDto());
			return employee;
	}

	public static EmployeeDto updateEmployeeAddress(EmployeeDto employee) throws Exception {
		employee.setAddress(EmployeeUI.createAddressDto());
		return employee;
	}

}