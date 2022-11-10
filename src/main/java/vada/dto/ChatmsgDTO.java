package vada.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class ChatmsgDTO implements Serializable{
	
	public static final long serialVersionUID=23253985893L;
	
	private int chatmsgid;
	private String chatmsguserid;
	private int chatmsgproductnum;
	private String chatmsgsellerid;
	private Timestamp chatmsgdate;
	private String msg;
	
	public ChatmsgDTO() {}

	public int getChatmsgid() {
		return chatmsgid;
	}

	public void setChatmsgid(int chatmsgid) {
		this.chatmsgid = chatmsgid;
	}

	public String getChatmsguserid() {
		return chatmsguserid;
	}

	public void setChatmsguserid(String chatmsguserid) {
		this.chatmsguserid = chatmsguserid;
	}

	public int getChatmsgproductnum() {
		return chatmsgproductnum;
	}

	public void setChatmsgproductnum(int chatmsgproductnum) {
		this.chatmsgproductnum = chatmsgproductnum;
	}

	public String getChatmsgsellerid() {
		return chatmsgsellerid;
	}

	public void setChatmsgsellerid(String chatmsgsellerid) {
		this.chatmsgsellerid = chatmsgsellerid;
	}

	public Timestamp getChatmsgdate() {
		return chatmsgdate;
	}

	public void setChatmsgdate(Timestamp chatmsgdate) {
		this.chatmsgdate = chatmsgdate;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ChatmsgBean [chatmsgid=" + chatmsgid + ", chatmsguserid=" + chatmsguserid + ", chatmsgproductnum="
				+ chatmsgproductnum + ", chatmsgsellerid=" + chatmsgsellerid + ", chatmsgdate=" + chatmsgdate + ", msg="
				+ msg + "]";
	}
	
	

}
