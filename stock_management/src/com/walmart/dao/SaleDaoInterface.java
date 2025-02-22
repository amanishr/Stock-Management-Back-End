package com.walmart.dao;

import java.util.List;

import com.walmart.exceptions.StockException;
import com.walmart.pojo.Sale;

public interface SaleDaoInterface {
	
	public int addSale(Sale s);
	public int deleteSale(int sid);
	public Sale getSaleById(int id) throws StockException;
	public List<Sale> getAll();
}
