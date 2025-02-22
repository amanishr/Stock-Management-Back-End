package com.walmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.walmart.exceptions.StockException;
import com.walmart.pojo.Employee;
import com.walmart.utils.DbUtil;

public class EmployeeDao implements EmployeeDaoInterface {

	Connection connect;
	Statement st;
	
	public EmployeeDao() throws SQLException{
		connect = DbUtil.getConnection("stock_mgmt");
		st = connect.createStatement();
		String query = "show tables like 'employees'";
		ResultSet r = st.executeQuery(query);
		if(!r.next())
			createTable();
		else if(!r.getString(1).equals("employees"))
			createTable();
	}
	
	private void createTable() throws SQLException {

		String create = "create table employees (eid int, ename text, salary double, active text)";
		st.executeUpdate(create);
	}
	
	@Override
	public int addEmployee(Employee e) {
		
		int i=0;
		try {
			String sql="insert into employees values(?,?,?,?)";
			PreparedStatement pst=connect.prepareStatement(sql);
			pst.setInt(1, e.getEid());
			pst.setString(2, e.getEname());
			pst.setDouble(3, e.getSalary());
			pst.setString(4, e.getActive());
			i=pst.executeUpdate();
		}catch(SQLException err) {
			err.printStackTrace();
		}
		return i;
	}

	@Override
	public int updateEmployee(double salary, String active, int eid) {

		int i=0;
		try {
		String sql="update employees set salary=? , active =? where eid=?";
		PreparedStatement pst=connect.prepareStatement(sql);
		pst.setDouble(1, salary);
		pst.setString(2, active);
		pst.setInt(3, eid);
		i=pst.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int deleteEmployee(int eid) {
		
		int i=0;
		String sql="delete from employees where eid=?";
		PreparedStatement pst;
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, eid);
			i=pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public Employee getEmployeeById(int id) throws StockException {

		Employee e=new Employee();
		try {
			String sql="select * from employees where eid=?";
			PreparedStatement pst = connect.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				e.setEid(rs.getInt(1));
				e.setEname(rs.getString(2));
				e.setSalary(rs.getDouble(3));
				e.setActive(rs.getString(4));
			}
			else
				throw new StockException("Employee does not exist");
		}catch(SQLException err) {
			err.printStackTrace();
		}
		return e;
	}

	@Override
	public List<Employee> getAll() {

		List<Employee> employees=new ArrayList<Employee>();
		try {
			String sql="select * from employees";
			Statement pst = connect.createStatement();
			ResultSet rs=pst.executeQuery(sql);
			while(rs.next()) {
				Employee e=new Employee();
				e.setEid(rs.getInt(1));
				e.setEname(rs.getString(2));
				e.setSalary(rs.getDouble(3));
				e.setActive(rs.getString(4));
				employees.add(e);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}			
		return employees;
	}

}
