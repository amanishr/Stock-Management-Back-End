package com.walmart.pojo;

import java.io.Serializable;

public class Sale implements Comparable<Product>, Serializable{

	private static final long serialVersionUID = -4882484524642613742L;
	private int saleId;
	private Product p;
	
	public Sale(int saleId, Product p) {
		super();
		this.saleId = saleId;
		this.p = p;
	}
	
	public Sale(){}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	public Product getP() {
		return p;
	}

	public void setP(Product p) {
		this.p = p;
	}

	@Override
	public String toString() {
		return "Sale [saleId=" + saleId + ", p=" + p + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p == null) ? 0 : p.hashCode());
		result = prime * result + saleId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sale other = (Sale) obj;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		if (saleId != other.saleId)
			return false;
		return true;
	}

	@Override
	public int compareTo(Product o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
