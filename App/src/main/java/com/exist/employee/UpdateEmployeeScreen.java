package com.exist.employee;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import java.util.stream.Collectors;

public class UpdateEmployeeScreen {
	
	
	private CreateUI createUI;
	private FactoryService factoryService;
	private EmployeeService empService;
	private DtoMapper mapper;


	public UpdateEmployeeScreen(CreateUI createUI) {
		this.createUI = createUI;
		factoryService = createUI.getFactoryService();
		empService = factoryService.getEmployeeService();
		mapper = factoryService.getMapper();
	}

	public CreateUI getCreateUI() {
		return createUI;
	}

	public  void updateEmployee() throws Exception {
		System.out.print("\033\143\n");
		System.out.println("Edit Employee! Input the ID\n");
		try {
			empService.getAllEmployees().forEach(System.out::println);

			EmployeeDto employee = mapper.mapEmployeeDto(empService.getElement(Employee.class, 
							Long.valueOf(InputManager.getPositiveNumber("Employee ID","EMPTY_NOT_ALLOWED"))));
			showEmployeeDetails(employee);
			OUTER:
			while(true) {

				String cmd = InputManager.enterString("Action: ADDROLE, DELROLE, ADDCONTACT, DELCONTACT, BACK", "EMPTY_NOT_ALLOWED");
					switch(cmd) {
						case "ADDROLE":
							employee = createUI.addEmployeeRole(employee);
							break;
						case "DELROLE":
							employee = createUI.deleteEmployeeRole(employee);
							break;
						case "ADDCONTACT":
							employee = createUI.addEmployeeContact(employee);
							break;
						case "DELCONTACT":
							employee = createUI.delEmployeeContact(employee);
							break;
						case "BACK":
							return;
					}
				showEmployeeDetails(employee);
				InputManager.output("BREAK BEFORE UPDATE");
				empService.updateElement(factoryService.createEmployee(employee));
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
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
			throw new Exception("Cannot find employee");
		}
	}

	public void roleScreen() throws Exception {
		System.out.print("\033\143");

		OUTER:
		while(true) {
			System.out.print("\033\143");
			empService.getAllRoles().forEach(System.out::println);

			System.out.println("\nWHAT TO DO [ADDROLE,DELETEROLE,BACK]");
			String action = InputManager.enterString("Action","EMPTY_NOT_ALLOWED");

			switch(action.toUpperCase()) {
				case "ADDROLE":
					createUI.createRole(empService);
					break;
				case "DELETEROLE":
					createUI.deleteRole(empService);
					break;
				case "BACK": 
					break OUTER;
			} 
		}
	}
}