package vada.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProductpriceDTO implements Serializable {
 
	
	public static final long serialVersionUID = 7984816848163L;
	
	private int productpriceid;
	private int productpricenum;
	private int productprice;
	private Timestamp productpriceupdatedate;
	
	public ProductpriceDTO() {
	}

	public int getProductpriceid() {
		return productpriceid;
	}

	public void setProductpriceid(int productpriceid) {
		this.productpriceid = productpriceid;
	}

	public int getProductpricenum() {
		return productpricenum;
	}

	public void setProductpricenum(int productpricenum) {
		this.productpricenum = productpricenum;
	}

	public int getProductprice() {
		return productprice;
	}

	public void setProductprice(int productprice) {
		this.productprice = productprice;
	}

	public Timestamp getProductpriceupdatedate() {
		return productpriceupdatedate;
	}

	public void setProductpriceupdatedate(Timestamp productpriceupdatedate) {
		this.productpriceupdatedate = productpriceupdatedate;
	}

	@Override
	public String toString() {
		return "ProductpriceBean [productpriceid=" + productpriceid + ", productpricenum=" + productpricenum
				+ ", productprice=" + productprice + ", productpriceupdatedate=" + productpriceupdatedate + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	
	
}

