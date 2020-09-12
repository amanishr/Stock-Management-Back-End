package com.walmart.services;

import java.util.List;

import com.walmart.exceptions.StockException;
import com.walmart.pojo.Employee;

public interface EmployeeServiceInterface {

	public int addEmployee(Employee e)throws StockException;
	public int editEmployee(double salary, String active, int eid)throws StockException;
	public int removeEmployee(int eid)throws StockException;
	public Employee getEmployeeById(int id)throws StockException;
	public List<Employee> getAll()throws StockException;
}
