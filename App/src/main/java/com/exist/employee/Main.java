package com.exist.employee;

import java.util.logging.Level;
import java.util.function.Consumer;
import java.util.List; 
public class Main {
	public static void main(String[] args) throws Exception {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		EmployeeService empServ = new EmployeeService();

		UpdateEmployeeScreen updateEmployeeScreen = new UpdateEmployeeScreen();
		String order = "";
		List<Employee> list = empServ.getAllElements(Employee.class);
		Consumer<Employee> consumer = System.out::println;
		OUTER:
		while(true) {
			System.out.print("\033\143\n\n");
			
			list.forEach(consumer);
			String cmd = InputManager.enterString("Action: ADDEMP, DELEMP, EDITEMP, MODIFYROLES\n SORT_GWA, SORT_HIREDATE, SORT_LASTNAME",
			 "EMPTY_NOT_ALLOWED");
			try {
				switch(cmd.toUpperCase()) {
					case "ADDEMP":
						CreateUI.createEmployee();
						list = empServ.getAllElements(Employee.class);
						break;
						
					case "DELEMP":
						empServ.deleteElement(empServ.getElement(Employee.class,Long.valueOf(InputManager.getPositiveNumber("Employee ID","EMPTY_NOT_ALLOWED"))));
						list = empServ.getAllElements(Employee.class);
						break;
						
					case "EDITEMP":
						updateEmployeeScreen.updateEmployee(empServ);
						break;
					case "MODIFYROLES":
						updateEmployeeScreen.roleScreen(empServ);
						break;
					case "SORT_GWA":
						list = empServ.getAllElements(Employee.class,"gwa");
						list = empServ.getAllElements(Employee.class,"gwa");
						consumer = emp -> System.out.printf("%d\t%s, %s %s %.2f\n",emp.getEmployeeId(),emp.getLastname(),emp.getFirstname(),emp.getMiddlename(),emp.getGwa());
						break;
					case "SORT_HIREDATE":
						list = empServ.getAllElements(Employee.class, "datehired");
						consumer = emp -> System.out.printf("%d\t%s, %s %s %s\n",emp.getEmployeeId(),emp.getLastname(),emp.getFirstname(),emp.getMiddlename(),emp.getDatehired());
						break;
					case "SORT_LASTNAME":
						list = empServ.getAllElements(Employee.class,"lastname");
						consumer = emp -> System.out.printf("%d\t%s, %s %s\n",emp.getEmployeeId(),emp.getLastname(),emp.getFirstname(),emp.getMiddlename());
						break;
					case "EXIT":
						break OUTER;
					default:
						consumer = System.out::println;
						list = empServ.getAllElements(Employee.class);
				}
			} catch (Exception ex) {
				InputManager.output("Can't find employee");
			}
		}
	}
}