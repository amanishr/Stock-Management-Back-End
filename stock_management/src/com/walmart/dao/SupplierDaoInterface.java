package com.walmart.dao;

import java.util.List;

import com.walmart.exceptions.StockException;
import com.walmart.pojo.Supplier;

public interface SupplierDaoInterface {

	public int addSupplier(Supplier s);
	public int updateSupplier(double rating, int sid);
	public int deleteSupplier(int sid);
	public Supplier getSupplierById(int id) throws StockException;
	public List<Supplier> getAll();
}
