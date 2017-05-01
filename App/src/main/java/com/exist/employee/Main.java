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
	
		/*UpdateEmployeeScreen updateEmployeeScreen = new UpdateEmployeeScreen();
		CreateUI createUI = updateEmployeeScreen.getCreateUI();
		EmployeeService empServ = createUI.getFactoryService().getEmployeeService();

		Employee emp = empServ.findEmployeeById(1L);

		List<Contact> contacts = new ArrayList<>();
		Contact contact = new Contact();
		contact.setEmployee(emp);
		contact.setContactType("EMAIL");
		contact.setContactInfo("jimmikaelcarpio@gmail.com");
		
		empServ.saveElement(contact);
		emp.setContacts(contacts);
		empServ.updateElement(emp);*/
	
	//PropertyConfigurator.configure("D:\\JAVA\\Employee_Annotation\\App\\src\\main\\resources\\log4j.properties");
	//java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
	
		
		UpdateEmployeeScreen updateEmployeeScreen = new UpdateEmployeeScreen();
		CreateUI createUI = updateEmployeeScreen.getCreateUI();
		EmployeeService empServ = createUI.getFactoryService().getEmployeeService();

		//List<Employee> list = empServ.getAllEmployees();
		String order = "";
		List<Employee> list = empServ.getAllEmployees();
		Consumer<Employee> consumer = System.out::println;
		OUTER:
		while(true) {
			//System.out.print("\033\143\n\n");
			
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
						list = empServ.getAllEmployees();
						consumer = emp -> System.out.printf("%d\t%s %.2f\n",emp.getEmployeeId(),emp.getEmployeeName().toString(),emp.getGwa());
						break;
					case "SORT_HIREDATE":
						list = empServ.getAllEmployees();
						consumer = emp -> System.out.printf("%d\t%s %s\n",emp.getEmployeeId(),emp.getEmployeeName().toString(),emp.getDateHired());
						break;
					case "SORT_LASTNAME":
						list = empServ.getAllEmployees();
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