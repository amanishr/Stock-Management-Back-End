package com.walmart.pojo;

import java.io.Serializable;

public class Purchase implements Comparable<Product>, Serializable{
	
	private static final long serialVersionUID = -2551390768286127512L;
	private int purchaseId;
	private Product p;
	private Supplier s;
	// Ordered, Received, Cancelled
	private String status;
	
	public Purchase(int purchaseId, Product p, Supplier s, String status) {
		super();
		this.purchaseId = purchaseId;
		this.p = p;
		this.s = s;
		this.status = status;
	}

	public Purchase() {}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Product getP() {
		return p;
	}

	public void setP(Product p) {
		this.p = p;
	}

	public Supplier getS() {
		return s;
	}

	public void setS(Supplier s) {
		this.s = s;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Purchase [purchaseId=" + purchaseId + ", p=" + p + ", s=" + s + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p == null) ? 0 : p.hashCode());
		result = prime * result + purchaseId;
		result = prime * result + ((s == null) ? 0 : s.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Purchase other = (Purchase) obj;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		if (purchaseId != other.purchaseId)
			return false;
		if (s == null) {
			if (other.s != null)
				return false;
		} else if (!s.equals(other.s))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public int compareTo(Product o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
