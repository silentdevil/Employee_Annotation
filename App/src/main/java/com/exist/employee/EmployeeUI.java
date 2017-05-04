package com.exist.employee;
import java.util.*;

public class EmployeeUI {

	private FactoryService factoryService;
	private static EmployeeService empService;
	private static DtoMapper mapper;

	public EmployeeUI(FactoryService factoryService) {
		empService = factoryService.getEmployeeService();
		mapper = factoryService.getMapper();
	}

	public void createEmployee() throws Exception {
		System.out.print("\033\143");
		System.out.println("CREATE NEW EMPLOYEE!\n\n");
		EmployeeDto employee = new EmployeeDto();
		String id = "EMPTY_NOT_ALLOWED";
		try {
			employee.setEmployeeName(createNameDto());
			employee.setAddress(createAddressDto());
			employee.setBirthday(DatePicker.parseDate(InputManager.enterString("Date YYYY-MM-DD", id),id));
			employee.setGwa(InputManager.getPositiveFloat("GWA",id));
			employee.setDateHired(DatePicker.parseDate(InputManager.enterString("Hire Date [YYYY-MM-DD]", id),id));
			employee.setCurrentlyHired(InputManager.getBoolean("CURRENTLY HIRED"));
			employee.setContacts(createContacts(employee));
			employee.getRoles().add(setRoleToEmployee(employee));
		
			empService.saveElement(factoryService.createEmployee(employee));
			
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new Exception("Cannot create employee");
		}
	}

	public static NameDto createNameDto() throws Exception {
		NameDto employeeName = new NameDto();
			String id = "EMPTY_NOT_ALLOWED";
			employeeName.setLastName(InputManager.enterString("Lastname", id).toUpperCase());
			employeeName.setFirstName(InputManager.enterString("Firstname", id).toUpperCase());
			employeeName.setMiddleName(InputManager.enterString("Middlename", id).toUpperCase());
			employeeName.setSuffix(InputManager.enterString("Suffix","").toUpperCase());
			employeeName.setTitle(InputManager.enterString("Title","").toUpperCase());

			InputManager.output(employeeName + "printed from EUI:48");
			return employeeName;
	}
	
	public static AddressDto createAddressDto() throws Exception {
		System.out.println("Enter ADDRESS!");
		AddressDto address = new AddressDto();
			String id = "EMPTY_NOT_ALLOWED";
			address.setStreetNo(InputManager.getPositiveNumber("Street no","EMPTY_NOT_ALLOWED"));
			address.setStreet(InputManager.enterString("Street","EMPTY_NOT_ALLOWED").toUpperCase());
			address.setBrgy(InputManager.enterString("Brgy", "EMPTY_NOT_ALLOWED").toUpperCase());
			address.setCity(InputManager.enterString("City", "EMPTY_NOT_ALLOWED").toUpperCase());
			address.setZipcode(InputManager.enterString("Zipcode","EMPTY_NOT_ALLOWED").toUpperCase());
		
			return address;
		
	}
	
	public static Set<ContactDto> createContacts(EmployeeDto employee) throws Exception {
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
		contact.setContactType(ContactType.valueOf(choice).getMessage());
		contact = UpdateEmployeeService.updateContact(contact);
	
		if(contact.getContactInfo() != null && !contact.getContactInfo().isEmpty())
			contacts.add(contact);
		return contacts;
			//return contact.getContactInfo() == "" ? null : contact;
	}

	public static RoleDto setRoleToEmployee(EmployeeDto employee) throws Exception {
		System.out.println("What role: ");
		empService.getAllRoles();//.forEach(System.out::println);
		//empService.getAllRoles().stream().filter(r ->!employee.getRoles().contains(mapper.mapRoleDto(r))).forEach(System.out::println);
		RoleDto roleDto = mapper.mapRoleDto(empService.getElement(Role.class, Long.valueOf(InputManager.getPositiveNumber("ROLE","EMPTY_NOT_ALLOWED"))));
		roleDto.getRole();
		return roleDto;
	}
	public static RoleDto pickRole(EmployeeDto employee) throws Exception {
		List<RoleDto> roles = new ArrayList<>(employee.getRoles());
		for(int i = 1; i < roles.size() + 1; i++) 
			System.out.println(i + " " + roles.get(i - 1).toString());
		
		return roles.get(InputManager.getPositiveNumber("Index of Role","") - 1);	
	}

	public static ContactDto pickContact(EmployeeDto employee) throws Exception {
		List<ContactDto> contacts = new ArrayList<>(employee.getContacts());
		for(int i = 1; i < contacts.size() + 1; i++) 
			System.out.println(i + " " + contacts.get(i - 1).toString());
		
		return contacts.get(InputManager.getPositiveNumber("Index of Contact","") - 1);	
	}

}