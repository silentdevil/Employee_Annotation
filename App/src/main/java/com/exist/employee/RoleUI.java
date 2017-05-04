package com.exist.employee;
import java.util.*;
public class RoleUI {

	private FactoryService factoryService;
	private EmployeeService empService;
	private DtoMapper mapper;

	public RoleUI(FactoryService factoryService) {
		this.factoryService = factoryService;
		empService = factoryService.getEmployeeService();
		mapper = factoryService.getMapper();
	}

	public void roleScreen() throws Exception {
		System.out.print("\033\143");

		OUTER:
		while(true) {
			System.out.print("\033\143");
			empService.getAllRoles().forEach(System.out::println);

			System.out.println("\nWHAT TO DO [ADDROLE,UPDATEROLE,DELETEROLE,BACK]");
			String action = InputManager.enterString("Action","EMPTY_NOT_ALLOWED");

			switch(action.toUpperCase()) {
				case "ADDROLE":
					createRole();
					break;
				case "DELETEROLE":
					deleteRole();
					break;
				case "UPDATEROLE":
					updateRole();
				case "BACK": 
					break OUTER;
			} 
		}
	}

	
	public void createRole() {
		System.out.print("\033\143");
		empService.getAllElements(Role.class).forEach(System.out::println);
		RoleDto role = new RoleDto();
		role.setRole(InputManager.enterString("NEW ROLE","EMPTY_NOT_ALLOWED").toUpperCase());
		try {
			empService.getElement(factoryService.createRole(role));
			InputManager.output("Role " + role.getRole() + " is existing.");
		} catch(Exception ex) {
			empService.saveElement(factoryService.createRole(role));
			InputManager.output("Role " + role.getRole() + " is created.");
		}
	}

	public void deleteRole() throws Exception {
		System.out.print("\033\143");
		empService.getAllElements(Role.class).forEach(System.out::println);
		RoleDto role = mapper.mapRoleDto(empService.getElement(Role.class, Long.valueOf(InputManager.getPositiveNumber("ROLE ID","EMPTY_NOT_ALLOWED"))));
		try{
			empService.deleteElement(factoryService.createRole(role));
			InputManager.output("Role " + role + " is sucessfully deleted");
		} catch(Exception ex) {
			InputManager.output("Role " + role + " is cannot be deleted");
		}
	}

	public void updateRole() throws Exception {
		System.out.print("\033\143");
		empService.getAllElements(Role.class).forEach(System.out::println);
		RoleDto role = mapper.mapRoleDto(empService.getElement(Role.class, Long.valueOf(InputManager.getPositiveNumber("ROLE ID","EMPTY_NOT_ALLOWED"))));
		try{
			role.setRole(InputManager.enterString("NEW ROLE NAME","EMPTY_NOT_ALLOWED"));
			empService.updateElement(factoryService.createRole(role));
			InputManager.output("Role " + role + " is sucessfully updated");
		} catch(Exception ex) {
			InputManager.output("Role " + role + " is cannot be updated");
		}
	}

}	