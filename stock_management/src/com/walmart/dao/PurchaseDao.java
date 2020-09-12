package com.walmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.walmart.exceptions.StockException;
import com.walmart.pojo.Product;
import com.walmart.pojo.Purchase;
import com.walmart.pojo.Supplier;
import com.walmart.utils.DbUtil;

public class PurchaseDao implements PurchaseDaoInterface {

	Connection connect;
	Statement st;
	
	public PurchaseDao() throws SQLException{
		connect = DbUtil.getConnection("stock_mgmt");
		st = connect.createStatement();
		String query = "show tables like 'purchases'";
		ResultSet r = st.executeQuery(query);
		if(!r.next())
			createTable();
		else if(!r.getString(1).equals("purchases"))
			createTable();
	}
	
	private void createTable() throws SQLException {

		String create = "create table purchases (purchaseId int, supplierId int, supplierName text, productId int, productName text, cost double, quantity int, status text)";
		st.executeUpdate(create);
	}
	
	@Override
	public int addPurchase(Purchase p) {

		int i=0;
		try {
			String sql="insert into purchases values(?,?,?,?,?,?,?,?)";
			PreparedStatement pst=connect.prepareStatement(sql);
			pst.setInt(1, p.getPurchaseId());
			pst.setInt(2, p.getS().getSid());
			pst.setString(3, p.getS().getSname());
			pst.setInt(4, p.getP().getPid());
			pst.setString(5, p.getP().getPname());
			pst.setDouble(6, p.getP().getCost());
			pst.setInt(7, p.getP().getQuantity());
			pst.setString(8, p.getStatus());
			i=pst.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int updatePurchase(String status, int purchaseId) {

		int i=0;
		try {
		String sql="update purchases set status=? where purchaseId=?";
		PreparedStatement pst=connect.prepareStatement(sql);
		pst.setString(1, status);
		pst.setInt(2, purchaseId);
		i=pst.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public Purchase getPurchaseById(int id) throws StockException {

		Purchase pur = new Purchase();
		try {
			String sql="select * from purchases where purchaseId=?";
			PreparedStatement pst = connect.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				pur.setPurchaseId(rs.getInt(1));
				Supplier s = new Supplier();
				s.setSid(rs.getInt(2));
				s.setSname(rs.getString(3));
				Product p = new Product();
				p.setPid(rs.getInt(4));
				p.setPname(rs.getString(5));
				p.setCost(rs.getDouble(6));
				p.setQuantity(rs.getInt(7));
				pur.setStatus(rs.getString(8));
				pur.setP(p);
				pur.setS(s);
			}
			else
				throw new StockException("Purchase does not exist");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return pur;
	}

	@Override
	public List<Purchase> getAll() {

		List<Purchase> purchases=new ArrayList<Purchase>();
		try {
			String sql="select * from purchases";
			Statement pst = connect.createStatement();
			ResultSet rs=pst.executeQuery(sql);
			while(rs.next()) {
				Purchase pur = new Purchase();
				pur.setPurchaseId(rs.getInt(1));
				Supplier s = new Supplier();
				s.setSid(rs.getInt(2));
				s.setSname(rs.getString(3));
				Product p = new Product();
				p.setPid(rs.getInt(4));
				p.setPname(rs.getString(5));
				p.setCost(rs.getDouble(6));
				p.setQuantity(rs.getInt(7));
				pur.setStatus(rs.getString(8));
				pur.setP(p);
				pur.setS(s);
				purchases.add(pur);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}			
		return purchases;
	}

}
