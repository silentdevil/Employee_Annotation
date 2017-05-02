package com.exist.employee;
import java.util.*;
public class CreateUI {

	private FactoryService factoryService;
	private EmployeeService empService;
	private DtoMapper mapper;

	public CreateUI(FactoryService factoryService) {
		this.factoryService = factoryService;
		empService = factoryService.getEmployeeService();
		mapper = factoryService.getMapper();
	}

	public FactoryService getFactoryService() {
		return factoryService;
	}

	public void createEmployee() throws Exception {
		System.out.print("\033\143");
		System.out.println("CREATE NEW EMPLOYEE!\n\n");
		EmployeeDto employee = new EmployeeDto();
		NameDto employeeName = new NameDto();
		String id = "EMPTY_NOT_ALLOWED";
		try {
			employeeName.setLastName(InputManager.enterString("Lastname", id).toUpperCase());
			employeeName.setFirstName(InputManager.enterString("Firstname", id).toUpperCase());
			employeeName.setMiddleName(InputManager.enterString("Middlename", id).toUpperCase());
			employeeName.setSuffix(InputManager.enterString("Suffix","").toUpperCase());
			employeeName.setTitle(InputManager.enterString("Title","").toUpperCase());
			employee.setEmployeeName(employeeName);
			//System.out.println("printed from CreateUI:32" + employee.getEmployeeName());
			employee.setAddress(createAddressDto());
			employee.setBirthday(DatePicker.parseDate(InputManager.enterString("Date YYYY-MM-DD", id),id));
			employee.setGwa(InputManager.getPositiveFloat("GWA",id));
			employee.setDateHired(DatePicker.parseDate(InputManager.enterString("Hire Date [YYYY-MM-DD]", id),id));
			employee.setCurrentlyHired(InputManager.getBoolean("CURRENTLY HIRED"));
			employee.setContacts(createContact(employee));
			employee.getRoles().add(setRoleToEmployee(employee));
			empService.saveElement(factoryService.createEmployee(employee));
			
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new Exception("Cannot create employee");
		}
	}
	
	private AddressDto createAddressDto() throws Exception {
		System.out.println("Enter ADDRESS!");
		AddressDto address = new AddressDto();
		
			address.setStreetNo(InputManager.getPositiveNumber("Street no","EMPTY_NOT_ALLOWED"));
			address.setStreet(InputManager.enterString("Street","EMPTY_NOT_ALLOWED").toUpperCase());
			address.setBrgy(InputManager.enterString("Brgy", "EMPTY_NOT_ALLOWED").toUpperCase());
			address.setCity(InputManager.enterString("City", "EMPTY_NOT_ALLOWED").toUpperCase());
			address.setZipcode(InputManager.enterString("Zipcode","EMPTY_NOT_ALLOWED").toUpperCase());
		
			return address;
		
	}
	
	private Set<ContactDto> createContact(EmployeeDto employee) throws Exception {
		Set<ContactDto> contacts = employee.getContacts();
		if(contacts == null)
			contacts = new TreeSet<>();
		//employee.setContacts(contacts);
		ContactDto contact = new ContactDto();
		contact.setEmployee(employee);
		String string = "";
		System.out.println("What to do: ");
				for(int i = 1; i <= ContactType.SIZE; i++) {
					System.out.print(ContactType.valueOf(i) + "\t"); 
				}

				System.out.println();
				int choice = InputManager.getPositiveNumber("","");
		switch(ContactType.valueOf(choice)) {
			case EMAIL: {
				string = InputManager.enterString("Email","");
				string = RegexUtils.isValidEmail(string) ? string : "";
				System.out.print((RegexUtils.isValidEmail(string)) ? "":"Not a valid email\n");
				break;
			} case MOBILE: {
				string = InputManager.enterString("Mobile  [XXXX-XXX-XXXX]","");
				string = RegexUtils.isValidMobile(string) ? string : "";
				System.out.print((RegexUtils.isValidMobile(string)) ? "":"Not a valid mobile\n");
				break;
			} case LANDLINE: {
				string = InputManager.enterString("Landline  [XXX-XXXX]","");
				string = RegexUtils.isValidLandline(string) ? string : "";
				System.out.print((RegexUtils.isValidLandline(string)) ? "":"Not a valid mobile\n");
				break;
			} default: {
				return null;
			}
		}
			

			contact.setContactType(ContactType.valueOf(choice).getMessage());
			contact.setContactInfo(string);
			if(contact.getContactInfo() != null && !contact.getContactInfo().isEmpty())
				contacts.add(contact);

			return contacts;
			//return contact.getContactInfo() == "" ? null : contact;
	}

	private RoleDto setRoleToEmployee(EmployeeDto employee) throws Exception {
		System.out.println("What role: ");
		empService.getAllRoles().stream().filter(r ->!employee.getRoles().contains(mapper.mapRoleDto(r))).forEach(System.out::println);
		RoleDto roleDto = mapper.mapRoleDto(empService.getElement(Role.class, Long.valueOf(InputManager.getPositiveNumber("ROLE","EMPTY_NOT_ALLOWED"))));
		roleDto.getRole();
		return roleDto;
	}

	public  EmployeeDto addEmployeeRole(EmployeeDto employee) throws Exception {
		try {
			Set<RoleDto> roles = employee.getRoles();
			roles.add(setRoleToEmployee(employee));	
			employee.setRoles(roles);
			return employee;
		} catch(Exception ex) {
			throw new Exception("Can't find role");
		}
	}

	public EmployeeDto deleteEmployeeRole(EmployeeDto employee) throws Exception {
		Set<RoleDto> roles = employee.getRoles();
		RoleDto role = mapper.mapRoleDto(empService.getRoleById(Long.valueOf(InputManager.getPositiveNumber("ROLE","EMPTY_NOT_ALLOWED"))));
		roles.remove(role);	
		employee.setRoles(roles);
		return employee;
	}
	
	public EmployeeDto addEmployeeContact(EmployeeDto employee) throws Exception {
		System.out.print("\033\143");
		Set<ContactDto> contacts = createContact(employee);
		
		employee.setContacts(contacts);
		return employee;
	}

	public EmployeeDto delEmployeeContact(EmployeeDto employee) throws Exception {
		System.out.print("\033\143");
		//List<ContactDto> contacts = new ArrayList<>(employee.getContacts());
		Set<ContactDto> contacts = employee.getContacts();
		contacts.forEach( contact -> {
			System.out.println(contact.getContactId() + " " + contact);
		});

		int index = InputManager.getPositiveNumber("ID of Contact","");
		try {
			contacts.remove(mapper.mapContactSingle(empService.getContactById(Long.valueOf(index)),employee));
			empService.deleteElement(empService.getContactById(Long.valueOf(index)));
		} catch(Exception ex) {
			ex.printStackTrace();
			System.err.println("index not found");
		}
		employee.setContacts(contacts);
		return employee;
	}


	public void createRole(EmployeeService empService) {
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

	public void deleteRole(EmployeeService empService) throws Exception {
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