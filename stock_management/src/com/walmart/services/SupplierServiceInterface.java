package com.walmart.services;

import java.util.List;

import com.walmart.exceptions.StockException;
import com.walmart.pojo.Supplier;

public interface SupplierServiceInterface {

	public int addSupplier(Supplier s)throws StockException;
	public int editSupplier(double rating, int sid)throws StockException;
	public int removeSupplier(int sid)throws StockException;
	public Supplier getSupplierById(int id)throws StockException;
	public List<Supplier> getAll()throws StockException;
}
