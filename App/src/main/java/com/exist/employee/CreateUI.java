package com.exist.employee;
import java.util.*;
public class CreateUI {

	private static EmployeeService empService = new EmployeeService();
	private static FactoryService factoryService = new FactoryService();
	private static DtoMapper mapper = new DtoMapper();
	public static void createEmployee() throws Exception {
		System.out.print("\033\143");
		System.out.println("CREATE NEW EMPLOYEE!\n\n");
		EmployeeDto employee = new EmployeeDto();
		String id = "EMPTY_NOT_ALLOWED";
		try {
			employee.setLastname(InputManager.enterString("Lastname", id).toUpperCase());
			employee.setFirstname(InputManager.enterString("Firstname", id).toUpperCase());
			employee.setMiddlename(InputManager.enterString("Middlename", id).toUpperCase());
			employee.setSuffix(InputManager.enterString("Suffix","").toUpperCase());
			employee.setTitle(InputManager.enterString("Title","").toUpperCase());
			employee.setAddress(createAddressDto());
			employee.setBirthday(DatePicker.parseDate(InputManager.enterString("Date YYYY-MM-DD", id),id));
			employee.setGwa(InputManager.getPositiveFloat("GWA",id));
			employee.setDatehired(DatePicker.parseDate(InputManager.enterString("Hire Date [YYYY-MM-DD]", id),id));
			employee.setCurrentlyHired(InputManager.getBoolean("CURRENTLY HIRED"));
			employee.setContact(createContact(employee));
			employee.getRoles().add(setRoleToEmployee(employee));
		
			empService.saveElement(factoryService.createEmployee(employee));
			
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new Exception("Cannot create employee");
		}
	}
	
	private static AddressDto createAddressDto() throws Exception {
		System.out.println("Enter ADDRESS!");
		AddressDto address = new AddressDto();
		
			address.setStreetno(InputManager.getPositiveNumber("Street no","EMPTY_NOT_ALLOWED"));
			address.setStreet(InputManager.enterString("Street","EMPTY_NOT_ALLOWED").toUpperCase());
			address.setBrgy(InputManager.enterString("Brgy", "EMPTY_NOT_ALLOWED").toUpperCase());
			address.setCity(InputManager.enterString("City", "EMPTY_NOT_ALLOWED").toUpperCase());
			address.setZipcode(InputManager.enterString("Zipcode","EMPTY_NOT_ALLOWED").toUpperCase());
		
			return address;
		
	}
	
	private static ContactDto createContact(EmployeeDto employee) throws Exception {
		ContactDto contact = new ContactDto();
		contact.setEmployee(employee);
		//try {
			String string = InputManager.enterString("Landline [xxx-xxxx]","");
			contact.setLandline(RegexUtils.isValidLandline(string) ? string : "");
			InputManager.output((RegexUtils.isValidLandline(string)) ? "":"Not a valid landline\n");

			string = InputManager.enterString("Mobile [xxxx-xxx-xxxx]","");
			contact.setMobile(RegexUtils.isValidMobile(string) ? string : "");
			System.out.print((RegexUtils.isValidMobile(string)) ? "":"Not a valid mobile\n");
			string = "";
			while(!RegexUtils.isValidEmail(string)) {
				string = InputManager.enterString("Email",
					(contact.getLandline().equals("") && contact.getMobile().equals("")) ? "EMPTY_NOT_ALLOWED":"");
				contact.setEmail(RegexUtils.isValidEmail(string) ? string : "");
				System.out.print((RegexUtils.isValidEmail(string)) ? "":"Not a valid email\n");
			}
			return contact;
	}

	private static RoleDto setRoleToEmployee(EmployeeDto employee) throws Exception {
		System.out.println("What role: ");
		empService.getAllElements(Role.class).stream().filter(r ->!employee.getRoles().contains(mapper.mapRoleDto(r))).forEach(System.out::println);
		RoleDto roleDto = mapper.mapRoleDto(empService.getElement(Role.class, Long.valueOf(InputManager.getPositiveNumber("ROLE","EMPTY_NOT_ALLOWED"))));
		roleDto.getRole();
		return roleDto;
	}

	public static EmployeeDto addEmployeeRole(EmployeeDto employee) throws Exception {
		try {
			Set<RoleDto> roles = employee.getRoles();
			roles.add(setRoleToEmployee(employee));	
			employee.setRoles(roles);
			return employee;
		} catch(Exception ex) {
			//ex.printStackTrace();
			throw new Exception("Can't find role");
		}
	}

	public static EmployeeDto deleteEmployeeRole(EmployeeDto employee) throws Exception {
		Set<RoleDto> roles = employee.getRoles();
		RoleDto role = mapper.mapRoleDto(empService.getElement(Role.class, Long.valueOf(InputManager.getPositiveNumber("ROLE","EMPTY_NOT_ALLOWED"))));
		roles.remove(role);	
		employee.setRoles(roles);
		return employee;
	}
	
	public static EmployeeDto addEmployeeContact(EmployeeService empService, EmployeeDto employee, String id) throws Exception {
		System.out.print("\033\143");
		String command = InputManager.enterString("Contact info: [LANDLINE, MOBILE, EMAIL]","EMPTY_NOT_ALLOWED");
		ContactDto contact = mapper.mapContactDto(empService.getElement(Contact.class, employee.getContact().getEmployeeId()));
		if(contact == null) {
			System.out.println("Contact is null");
			contact = new ContactDto();
		}
		try {
			switch(command.toUpperCase()) {
				case "LANDLINE":
					contact.setLandline(!id.equals("DEL") ? 
						InputManager.enterString("Landline","EMPTY_IS_ALLOWED") : "");
					break;
				case "MOBILE":
					contact.setMobile(!id.equals("DEL") ? 
						InputManager.enterString("Mobile","EMPTY_IS_ALLOWED"):"");
					break;
				case "EMAIL":
					contact.setEmail(!id.equals("DEL") ? 
						InputManager.enterString("Email","EMPTY_IS_ALLOWED"): "");
					break;

			}
			//empService.updateElement(contact);
			employee.setContact(contact);
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return employee;
	}

	public static void createRole(EmployeeService empService) {
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

	public static void deleteRole(EmployeeService empService) throws Exception {
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

}	