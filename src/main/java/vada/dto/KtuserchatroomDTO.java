package vada.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class KtuserchatroomDTO implements Serializable {
	
	public static final long serialVersionUID=395839853L;
	
	private String ktuserid;
	private int ktproductnum;
	private String ktsellerid;
	private String chatroomtitle;
	private int chatroomusercnt;
	private Timestamp chatroomdate;
	
	public KtuserchatroomDTO() {}

	public String getKtuserid() {
		return ktuserid;
	}

	public void setKtuserid(String ktuserid) {
		this.ktuserid = ktuserid;
	}

	public int getKtproductnum() {
		return ktproductnum;
	}

	public void setKtproductnum(int ktproductnum) {
		this.ktproductnum = ktproductnum;
	}

	public String getKtsellerid() {
		return ktsellerid;
	}

	public void setKtsellerid(String ktsellerid) {
		this.ktsellerid = ktsellerid;
	}

	public String getChatroomtitle() {
		return chatroomtitle;
	}

	public void setChatroomtitle(String chatroomtitle) {
		this.chatroomtitle = chatroomtitle;
	}

	public int getChatroomusercnt() {
		return chatroomusercnt;
	}

	public void setChatroomusercnt(int chatroomusercnt) {
		this.chatroomusercnt = chatroomusercnt;
	}

	public Timestamp getChatroomdate() {
		return chatroomdate;
	}

	public void setChatroomdate(Timestamp chatroomdate) {
		this.chatroomdate = chatroomdate;
	}

	@Override
	public String toString() {
		return "KtuserchatroomBean [ktuserid=" + ktuserid + ", ktproductnum=" + ktproductnum + ", ktsellerid="
				+ ktsellerid + ", chatroomtitle=" + chatroomtitle + ", chatroomusercnt=" + chatroomusercnt
				+ ", chatroomdate=" + chatroomdate + "]";
	}
	

}
