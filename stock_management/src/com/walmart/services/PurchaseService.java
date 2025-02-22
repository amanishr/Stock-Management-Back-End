package com.walmart.services;

import java.sql.SQLException;
import java.util.List;

import com.walmart.dao.PurchaseDao;
import com.walmart.exceptions.StockException;
import com.walmart.pojo.Product;
import com.walmart.pojo.Purchase;
import com.walmart.pojo.Supplier;

public class PurchaseService implements PurchaseServiceInterface {

	PurchaseDao purchaseDao;
	SupplierService sService;
	ProductService pService;
	
	public PurchaseService() throws SQLException{
		purchaseDao = new PurchaseDao();
		sService = new SupplierService();
		pService = new ProductService();
	}
	@Override
	public int addPurchase(int purchaseId, int supplierId, int productId, int quantity, String status)
			throws StockException {

		int i = 0;
		Supplier s = sService.getSupplierById(supplierId);
		Product p = pService.getProdcutById(productId);
		if(!checkStatus(status))
			throw new StockException("Invalid status");
		Purchase pur = new Purchase(purchaseId, p, s, status);
		int r = 1;
		if(status.equals("Received")) {
			r = 0;
			int qty = p.getQuantity() + quantity;
			r = pService.editProdcut(qty, p.getCost(), p.getPid());
		}
		if(r==1) {
			p.setQuantity(quantity);
			i = purchaseDao.addPurchase(pur);
		}
		else
			throw new StockException("Operation failed! Try again");
		return i;
	}

	private boolean checkStatus(String status) {
		
		if(status.equals("Ordered")||status.equals("Received")||status.equals("Cancelled"))
			return true;
		return false;
	}
	@Override
	public int updatePurchase(String status, int pid) throws StockException {
		
		int i = 1;
		Purchase pur = getPurchaseById(pid);
		if(!checkStatus(status))
			throw new StockException("Invalid status");
		if(pur.getStatus().equals("Received")) {
			if(!status.equals("Received"))
				throw new StockException("Invalid status update");
			else
				return i;
		}
		else if(status.equals("Received")) {
			int r = 0;
			Product p = pService.getProdcutById(pur.getP().getPid());
			int qty = p.getQuantity() + pur.getP().getQuantity();
			r = pService.editProdcut(qty, p.getCost(), p.getPid());
			if(r==1)
				i = purchaseDao.updatePurchase(status, pid);
			else
				throw new StockException("Operation failed! Try again");
		}
		else
			i = purchaseDao.updatePurchase(status, pid);
		return i;
	}

	@Override
	public Purchase getPurchaseById(int id) throws StockException {
		
		return purchaseDao.getPurchaseById(id);
	}

	@Override
	public List<Purchase> getAll() throws StockException {

		List<Purchase> purchases = purchaseDao.getAll();
		if(purchases.isEmpty()) {
			throw new StockException("No Purchases available");
		}
		return purchases;
	}

}
