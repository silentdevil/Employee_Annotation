package com.exist.employee;
import java.util.*;
public class FactoryService {

	private EmployeeService empService;
	private DtoMapper mapper;

	public FactoryService(EmployeeService empService, DtoMapper mapper){
		this.empService = empService;
		this.mapper = mapper;
	}

	public EmployeeService getEmployeeService() {
		return empService;
	}
	
	public DtoMapper getMapper() {
		return mapper;
	}

	public void createEmployee(EmployeeDto employeeDto) {
		//System.out.println(employeeDto.getEmployeeId() + "FactoryService:22");
		Employee employee = empService.getElement(Employee.class,employeeDto.getEmployeeId());
			//InputManager.output(employee.getEmployeeName().toString());
		if(employee == null)
			employee = new Employee();
		Name employeeName;// = new Name();
		NameDto nameDto;// = employeeDto.getEmployeeName();
		try{
			employee = empService.getElement(Employee.class,employeeDto.getEmployeeId());
			//InputManager.output(employee.getEmployeeName().toString());
			if(employee == null)
				employee = new Employee();

			employeeName = new Name();
			nameDto = employeeDto.getEmployeeName();
		
			employee.setEmployeeId(employeeDto.getEmployeeId());
			
			employeeName.setLastName(nameDto.getLastName());
			employeeName.setFirstName(nameDto.getFirstName());
			employeeName.setMiddleName(nameDto.getMiddleName());
			employeeName.setSuffix(nameDto.getSuffix());
			employeeName.setTitle(nameDto.getTitle());
			employee.setEmployeeName(employeeName);
			employee.setAddress(createAddress(employeeDto.getAddress()));
			employee.setBirthday(employeeDto.getBirthday());
			employee.setGwa(employeeDto.getGwa());
			employee.setDateHired(employeeDto.getDateHired());
			employee.setCurrentlyHired(employeeDto.getCurrentlyHired());
			Set<Contact> contacts = createContacts(employeeDto, employee);
			System.out.println(contacts);
			employee.setContacts(contacts);
			employee.setRoles(createRoleSet(employeeDto.getRoles()));
			
			empService.saveOrUpdate(employee);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		//return employee;
	}
	
	public  Address createAddress(AddressDto addressDto) throws Exception {
		Address address = new Address();

			address.setStreetNo(addressDto.getStreetNo());
			address.setStreet(addressDto.getStreet());
			address.setBrgy(addressDto.getBrgy());
			address.setCity(addressDto.getCity());
			address.setZipcode(addressDto.getZipcode());
		return address;
	}
	
	public Set<Contact> createContacts(EmployeeDto employeeDto, Employee employee) throws Exception {
		InputManager.output("PRINTED FROM CREATE CONTACTS");
		Set<Contact> contacts = employee.getContacts();
		if(contacts == null)
			contacts = new TreeSet<>();
		contacts.clear();
		System.out.println("printed from FactoryService.createContacts" + employeeDto.getContacts());
		try {
			for(ContactDto c: employeeDto.getContacts()) {
				Contact contact = new Contact();
				contact.setEmployee(employee);
				contact.setContactType(c.getContactType());
				contact.setContactInfo(c.getContactInfo());
				System.out.println(contacts.add(contact) + contact.toString());
			}
		} catch(Exception ex) {
			System.out.println("Null contact passed");
			ex.printStackTrace();
			return null;
		}
			System.out.println("contacts from create contacts" + contacts);
			return contacts;
	}
	
	public Role createRole(RoleDto roleDto) {
		Role role = new Role();
		role.setRoleId(roleDto.getRoleId());
		role.setRole(roleDto.getRole());
		return role;
	}
	
	public Set<Role> createRoleSet(Set<RoleDto> roleDtoSet) {
		Set<Role> roles = new HashSet<>();
		roleDtoSet.forEach(r -> roles.add(createRole(r)));
		return roles;
	}
	
	
}
