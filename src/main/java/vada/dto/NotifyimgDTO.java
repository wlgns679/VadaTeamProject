package vada.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class NotifyimgDTO implements Serializable{
	
	public static final long serialVersionUID = 543435325L;
	
	private int notifyimgnotifyid;
	private int notifyimgnum;
	private String notifyimgsname;
	private int notifyimgsize;
	private String notifyimgcname;
	private Timestamp notifyimgdate;
	
	public NotifyimgDTO() {}

	public int getNotifyimgnotifyid() {
		return notifyimgnotifyid;
	}

	public void setNotifyimgnotifyid(int notifyimgnotifyid) {
		this.notifyimgnotifyid = notifyimgnotifyid;
	}

	public int getNotifyimgnum() {
		return notifyimgnum;
	}

	public void setNotifyimgnum(int notifyimgnum) {
		this.notifyimgnum = notifyimgnum;
	}

	public String getNotifyimgsname() {
		return notifyimgsname;
	}

	public void setNotifyimgsname(String notifyimgsname) {
		this.notifyimgsname = notifyimgsname;
	}

	public int getNotifyimgsize() {
		return notifyimgsize;
	}

	public void setNotifyimgsize(int notifyimgsize) {
		this.notifyimgsize = notifyimgsize;
	}

	public String getNotifyimgcname() {
		return notifyimgcname;
	}

	public void setNotifyimgcname(String notifyimgcname) {
		this.notifyimgcname = notifyimgcname;
	}

	public Timestamp getNotifyimgdate() {
		return notifyimgdate;
	}

	public void setNotifyimgdate(Timestamp notifyimgdate) {
		this.notifyimgdate = notifyimgdate;
	}

	@Override
	public String toString() {
		return "NotifyimgDTO [notifyimgnotifyid=" + notifyimgnotifyid + ", notifyimgnum=" + notifyimgnum
				+ ", notifyimgsname=" + notifyimgsname + ", notifyimgsize=" + notifyimgsize + ", notifyimgcname="
				+ notifyimgcname + ", notifyimgdate=" + notifyimgdate + "]";
	}

}
