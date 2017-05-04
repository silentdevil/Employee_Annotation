package com.exist.employee;
import java.util.*;
public class CreateUI {

	private FactoryService factoryService;
	private EmployeeService empService;
	private DtoMapper mapper;

	private EmployeeUI employeeUI;
	private RoleUI roleUI;
	private UpdateEmployeeScreen updateEmployeeScreen;

	public CreateUI(FactoryService factoryService) {
		this.factoryService = factoryService;
		empService = factoryService.getEmployeeService();
		mapper = factoryService.getMapper();
	}

	public EmployeeUI getEmployeeUI() {
		if(employeeUI == null)
			employeeUI = new EmployeeUI(factoryService);

		return employeeUI;
	}

	public  RoleUI getRoleUI() {
		if(roleUI == null)
			roleUI = new RoleUI(factoryService);

		return roleUI;
	}

	public UpdateEmployeeScreen getUpdateEmployeeScreen() {
		if(updateEmployeeScreen == null)
			updateEmployeeScreen = new UpdateEmployeeScreen(factoryService);
		return updateEmployeeScreen;
	}


}	