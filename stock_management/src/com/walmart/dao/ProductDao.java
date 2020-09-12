package com.walmart.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;

import com.walmart.exceptions.StockException;
import com.walmart.pojo.Product;
import com.walmart.utils.DbUtil;

public class ProductDao implements ProductDaoInterface {

	Connection connect;
	Statement st;
	
	public ProductDao() throws SQLException{
		connect = DbUtil.getConnection("stock_mgmt");
		st = connect.createStatement();
		String query = "show tables like 'products'";
		ResultSet r = st.executeQuery(query);
		if(!r.next())
			createTable();
		else if(!r.getString(1).equals("products"))
			createTable();
	}

	private void createTable() throws SQLException {

		String create = "create table products (pid int, pname text, cost double, quantity int)";
		st.executeUpdate(create);
	}
	@Override
	public int addProduct(Product p) {

		int i=0;
		try {
			String sql="insert into products values(?,?,?,?)";
			PreparedStatement pst=connect.prepareStatement(sql);
			pst.setInt(1, p.getPid());
			pst.setString(2, p.getPname());
			pst.setDouble(3, p.getCost());
			pst.setInt(4, p.getQuantity());
			i=pst.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int updateProdcut(int qty, double price, int pid) {
		
		int i=0;
		try {
		String sql="update products set quantity=? , cost =? where pid=?";
		PreparedStatement pst=connect.prepareStatement(sql);
		pst.setInt(1, qty);
		pst.setDouble(2, price);
		pst.setInt(3, pid);
		i=pst.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int deleteProduct(int pid) {
		
		int i=0;
		String sql="delete from products where pid=?";
		PreparedStatement pst;
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, pid);
			i=pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public Product getProdcutById(int id) throws StockException{
		
		Product p=new Product();
		try {
			String sql="select * from products where pid=?";
			PreparedStatement pst = connect.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				p.setPid(rs.getInt(1));
				p.setPname(rs.getString(2));
				p.setCost(rs.getDouble(3));
				p.setQuantity(rs.getInt(4));
			}
			else
				throw new StockException("Product does not exist");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public List<Product> getAll() {
		
		List<Product> products=new ArrayList<Product>();
		try {
			String sql="select * from products";
			Statement pst = connect.createStatement();
			ResultSet rs=pst.executeQuery(sql);
			while(rs.next()) {
				Product p=new Product();
				p.setPid(rs.getInt(1));
				p.setPname(rs.getString(2));
				p.setCost(rs.getDouble(3));
				p.setQuantity(rs.getInt(4));
				products.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}			
		return products;
	}

}
