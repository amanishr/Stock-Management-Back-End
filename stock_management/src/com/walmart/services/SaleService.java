package com.walmart.services;

import java.sql.SQLException;
import java.util.List;

import com.walmart.dao.SaleDao;
import com.walmart.exceptions.StockException;
import com.walmart.pojo.Product;
import com.walmart.pojo.Sale;

public class SaleService implements SaleServiceInterface {

	SaleDao saleDao;
	ProductService pservice;
	
	public SaleService() throws SQLException{
		saleDao=new SaleDao();
		pservice = new ProductService();
	}
	
	@Override
	public int addSale(Sale s) throws StockException {

		int i=0;
		if(checkAvailability(s.getP())) {
			Product p = pservice.getProdcutById(s.getP().getPid());
			int qty = p.getQuantity() - s.getP().getQuantity();
			int r = pservice.editProdcut(qty, p.getCost(), p.getPid());
			if(r==1)
				i = saleDao.addSale(s);
			else
				throw new StockException("Operation failed! Try again");
		}
		else {
			throw new StockException("Sale could not be processed due to product unavailability");
		}
		return i;
	}

	private boolean checkAvailability(Product pSale) {
			
		try {
			Product p = pservice.getProdcutById(pSale.getPid());
			if(p.getQuantity() >= pSale.getQuantity())
				return true;
			else
				return false;
		} catch (StockException e) {
			return false;
		}
	}

	@Override
	public int removeSale(int sid) throws StockException {
		
		int i = 0;
		Sale s = getSaleById(sid);
		Product p = pservice.getProdcutById(s.getP().getPid());
		int qty = p.getQuantity() + s.getP().getQuantity();
		int r = pservice.editProdcut(qty, p.getCost(), p.getPid());
		if(r==1)
			i = saleDao.deleteSale(sid);
		else
			throw new StockException("Operation failed! Try again");
		return i;
	}

	@Override
	public Sale getSaleById(int id) throws StockException {
		
		return saleDao.getSaleById(id);
	}

	@Override
	public List<Sale> getAll() throws StockException {

		List<Sale> sales=saleDao.getAll();
		if(sales.isEmpty()) {
			throw new StockException("No Sale available");
		}
		return sales;
	}

}
