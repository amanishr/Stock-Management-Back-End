package com.walmart.services;

import java.util.List;

import com.walmart.exceptions.StockException;
import com.walmart.pojo.Sale;

public interface SaleServiceInterface {

	public int addSale(Sale s)throws StockException;
	public int removeSale(int sid)throws StockException;
	public Sale getSaleById(int id)throws StockException;
	public List<Sale> getAll()throws StockException;
}
