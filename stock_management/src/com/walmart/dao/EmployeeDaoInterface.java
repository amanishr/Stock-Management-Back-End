package com.walmart.dao;

import java.util.List;

import com.walmart.exceptions.StockException;
import com.walmart.pojo.Employee;

public interface EmployeeDaoInterface {

	public int addEmployee(Employee e);
	public int updateEmployee(double salary, String active, int eid);
	public int deleteEmployee(int eid);
	public Employee getEmployeeById(int id) throws StockException;
	public List<Employee> getAll();
}
