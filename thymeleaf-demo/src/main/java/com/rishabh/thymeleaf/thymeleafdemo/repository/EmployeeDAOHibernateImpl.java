package com.rishabh.thymeleaf.thymeleafdemo.repository;

import java.util.Currency;
import java.util.List;


import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rishabh.thymeleaf.thymeleafdemo.entity.Employee;


@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDao {

	@Autowired
	EntityManager entityManager;

	
	@Override
	public List<Employee> findAll() {
		Session curr = entityManager.unwrap(Session.class);
		
		Query<Employee> theQuery=
				curr.createQuery("from Employee",Employee.class);
		
		List<Employee> list= theQuery.getResultList(); 
		
		return list;
		
	}


	@Override
	public Employee findById(int theId) {
		Session curr = entityManager.unwrap(Session.class);
		
		Employee employee=
				curr.get(Employee.class,theId);
		
		return employee;
		
	}


	@Override
	public void save(Employee theEmployee) {
		
		Session curr = entityManager.unwrap(Session.class);
		curr.saveOrUpdate(theEmployee);		
	}


	@Override
	public void deleteById(int theId) {
		Session curr = entityManager.unwrap(Session.class);
		
		Query theQuery=
				curr.createQuery(
						"delete from Employee where id=:employeeId");
		
		theQuery.setParameter("employeeId", theId);
	
		theQuery.executeUpdate();
	}

}
