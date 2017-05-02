package com.exist.employee;

import java.util.logging.Level;
import java.util.function.Consumer;
import java.util.*; 
import org.slf4j.*;
import org.apache.log4j.PropertyConfigurator;
import java.io.*;

public class Main {

	public static Logger logger = LoggerFactory.getLogger("org.hibernate");
	public static void main(String[] args) throws Exception {

	
		UpdateEmployeeScreen updateEmployeeScreen = new UpdateEmployeeScreen(new CreateUI(
			new FactoryService(new EmployeeService(), new DtoMapper())));

		CreateUI createUI = updateEmployeeScreen.getCreateUI();
		EmployeeService empServ = createUI.getFactoryService().getEmployeeService();
		
		System.out.println(empServ.getRoleById(1L).getEmployees());
		String order = "";
		List<Employee> list = empServ.getAllEmployees();
		Consumer<Employee> consumer = System.out::println;
		OUTER:
		while(true) {
			
			list.forEach(consumer);
			String cmd = InputManager.enterString("Action: ADDEMP, DELEMP, EDITEMP, MODIFYROLES\n SORT_GWA, SORT_HIREDATE, SORT_LASTNAME",
			 "EMPTY_NOT_ALLOWED");
			try {
				switch(cmd.toUpperCase()) {
					case "ADDEMP":
						createUI.createEmployee();
						list = empServ.getAllEmployees();
						break;
						
					case "DELEMP":
						empServ.deleteElement(empServ.getElement(Employee.class,Long.valueOf(InputManager.getPositiveNumber("Employee ID","EMPTY_NOT_ALLOWED"))));
						list = empServ.getAllEmployees();
						break;
						
					case "EDITEMP":
						updateEmployeeScreen.updateEmployee();
						break;
					case "MODIFYROLES":
						updateEmployeeScreen.roleScreen();
						break;
					case "SORT_GWA":
						list = empServ.getAllEmployees("gwa");
						consumer = emp -> System.out.printf("%d\t%s %.2f\n",emp.getEmployeeId(),emp.getEmployeeName().toString(),emp.getGwa());
						break;
					case "SORT_HIREDATE":
						list = empServ.getAllEmployees("date_hired");
						consumer = emp -> System.out.printf("%d\t%s %s\n",emp.getEmployeeId(),emp.getEmployeeName().toString(),emp.getDateHired());
						break;
					case "SORT_LASTNAME":
						list = empServ.getAllEmployees("last_name");
						consumer = emp -> System.out.printf("%d\t%s\n",emp.getEmployeeId(),emp.getEmployeeName().toString());
						break;
					case "EXIT":
						break OUTER;
					default:
						consumer = System.out::println;
						list = empServ.getAllEmployees();
				}
			} catch (Exception ex) {
				logger.info("Exception occured", ex);
				ex.printStackTrace();
				InputManager.output("Can't find employee");

			}
		}
	}
}