package com.exist.employee;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Set;
import java.util.Date;
import java.util.HashSet;

public class EmployeeService {

	private Dao dao = new Dao();

	public <T> List<T> getAllElements(final Class<T> type) {
		return dao.getAll(type);	
	}

	public List<Employee> getAllEmployees() {
		return dao.getAll(Employee.class);
	}

	public List getAllEmployees(String order){
		return dao.getAll(Employee.class,order);
	}

	public List<Role> getAllRoles() {
		return dao.getAll(Role.class);
	}

	public <T> List getAllElements(final Class<T> type, String order) {
      return dao.getAll(type, order);
    }

    public Role getRoleById(Long id) {
    	return dao.get(Role.class,id);
    }

    public Contact getContactById(Long id){
    	return dao.get(Contact.class,id);
    }

    public Employee findEmployeeById(Long id) {
    	InputManager.output("hey");
    	return dao.get(Employee.class, id);
    }

	public <T> T getElement(final Class<T> type, final Long id){
      	return dao.get(type,id);
    }

	public <T> T getElement(T t){
      	return dao.get(t);
    }

	public <E> void saveElement(E e) {
		dao.save(e);
	}

	public <E> void deleteElement(E e) {
		dao.delete(e);
	}
	
	public <E> void updateElement(E e) {
		dao.update(e);
	}

	public <E> void saveOrUpdate(E e){
		dao.saveOrUpdate(e);
	}


/*
	public boolean isRoleDeletable(Role role) {
		Session session = beginTransaction();	
	 	List<Role> list = session.createSQLQuery("SELECT * FROM roles INNER JOIN employee_role On employee_role.roleid = roles.roleid AND employee_role.roleid =" + role.getRoleId()).addEntity(Role.class).list();
		session.getTransaction().commit();
		return list.size() == 0;
	}

	public Set<Role> listEmployeeRoles(Employee employee) {
		Session session = beginTransaction();	
		List<Role> list = session.createSQLQuery("SELECT * FROM roles JOIN employee_role ON employee_role.roleid = roles.roleid JOIN employees ON employees.employeeid = employee_role.employeeid WHERE employees.employeeid = " + employee.getEmployeeId()).addEntity(Role.class).list();		//list.forEach(emp -> System.out.printf("%s\t",emp.getRole()));
		session.getTransaction().commit();
		return new HashSet<Role>(list);
	}
*/
}