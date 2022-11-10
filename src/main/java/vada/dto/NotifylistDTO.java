package vada.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class NotifylistDTO implements Serializable{
	
	public static final long serialVersionUID=8537485932L;
	
	private int notifyid;
	private int notifyproductnum;
	private String notifyreason;
	private String notifyuserid;
	private Timestamp notifydate;
	
	public NotifylistDTO() {}

	public int getNotifyid() {
		return notifyid;
	}

	public void setNotifyid(int notifyid) {
		this.notifyid = notifyid;
	}

	public int getNotifyproductnum() {
		return notifyproductnum;
	}

	public void setNotifyproductnum(int notifyproductnum) {
		this.notifyproductnum = notifyproductnum;
	}

	public String getNotifyreason() {
		return notifyreason;
	}

	public void setNotifyreason(String notifyreason) {
		this.notifyreason = notifyreason;
	}

	public String getNotifyuserid() {
		return notifyuserid;
	}

	public void setNotifyuserid(String notifyuserid) {
		this.notifyuserid = notifyuserid;
	}

	public Timestamp getNotifydate() {
		return notifydate;
	}

	public void setNotifydate(Timestamp notifydate) {
		this.notifydate = notifydate;
	}

	@Override
	public String toString() {
		return "NotifylistBean [notifyid=" + notifyid + ", notifyproductnum=" + notifyproductnum + ", notifyreason="
				+ notifyreason + ", notifyuserid=" + notifyuserid + ", notifydate=" + notifydate + "]";
	}
	
	
}
