package vada.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class ChatmsglogDTO implements Serializable {
	
	public static final long serializable=2344582985L;
	
	private int chatmsglogid;
	private String chatmsguserid;
	private int chatmsgproductnum;
	private String chatmsgsellerid;
	private Timestamp chatmsgdate;
	
	public ChatmsglogDTO() {}

	public int getChatmsglogid() {
		return chatmsglogid;
	}

	public void setChatmsglogid(int chatmsglogid) {
		this.chatmsglogid = chatmsglogid;
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

	@Override
	public String toString() {
		return "Chatmsglog [chatmsglogid=" + chatmsglogid + ", chatmsguserid=" + chatmsguserid + ", chatmsgproductnum="
				+ chatmsgproductnum + ", chatmsgsellerid=" + chatmsgsellerid + ", chatmsgdate=" + chatmsgdate + "]";
	}
	
	
	
	

}
