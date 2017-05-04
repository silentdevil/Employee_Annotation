package com.exist.employee;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import java.util.stream.Collectors;

public class UpdateEmployeeScreen {
	
	
	private FactoryService factoryService;
	private EmployeeService empService;
	private DtoMapper mapper;


	public UpdateEmployeeScreen(FactoryService factoryService) {
		empService = factoryService.getEmployeeService();
		mapper = factoryService.getMapper();
	}

	public void updateEmployee() throws Exception {
		System.out.print("\033\143\n");
		System.out.println("Edit Employee! Input the ID\n");
		try {
			empService.getAllEmployees().forEach(System.out::println);

			EmployeeDto employee = mapper.mapEmployeeDto(empService.getElement(Employee.class, 
							Long.valueOf(InputManager.getPositiveNumber("Employee ID","EMPTY_NOT_ALLOWED"))));
			if(employee == null)
				InputManager.output("EMPLOYEE NULL:UPDATE:38");
			showEmployeeDetails(employee);
			OUTER:
			while(true) {

				String cmd = InputManager.enterString("Action: ADDROLE, DELROLE, ADDCONTACT, DELCONTACT, UPDATECONTACT, ,UPDATENAME, UPDATEADDRESS, BACK", "EMPTY_NOT_ALLOWED");
					switch(cmd) {
						case "ADDROLE":
							employee = UpdateEmployeeService.addEmployeeRole(employee);
							break;
						case "DELROLE":
							RoleDto role = EmployeeUI.pickRole(employee);
							employee = UpdateEmployeeService.deleteEmployeeRole(employee,role);
							break;
						case "ADDCONTACT":
							employee = UpdateEmployeeService.addEmployeeContact(employee);
							break;
						case "DELCONTACT":
							ContactDto contact = EmployeeUI.pickContact(employee);
							employee = UpdateEmployeeService.delEmployeeContact(employee, contact);
							break;
						case "UPDATECONTACT":
							contact = EmployeeUI.pickContact(employee);
							UpdateEmployeeService.updateContact(contact);
							break;
						case "UPDATENAME":
							employee = UpdateEmployeeService.updateEmployeeName(employee);
							break;
						case "UPDATEADDRESS":
							employee = UpdateEmployeeService.updateEmployeeAddress(employee);
							break;
						case "BACK":
							return;
					}
				showEmployeeDetails(employee);
				InputManager.output("BREAK BEFORE UPDATE");
				
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			Thread.sleep(2000);
			
		}	
	}
	
	public void showEmployeeDetails(EmployeeDto employee) throws Exception {
		try {
			System.out.print("\033\143");
			System.out.println(employee.getEmployeeName());
			
			System.out.println("Address: " + employee.getAddress());
			System.out.println("Birthday: " + employee.getBirthday());
			System.out.println("GWA: " + employee.getGwa());
			System.out.println("Date hired: " + employee.getDateHired());
			System.out.println("Currently hired: " + employee.getCurrentlyHired());
			System.out.println("Contacts: ");
				employee.getContacts().forEach(c -> System.out.println("\t" + c));
			System.out.println("Roles: " + employee.getRoles());
		
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new Exception("Cannot find employee");
		}
	}

}