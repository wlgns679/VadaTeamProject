package vada.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserDTO implements Serializable {

	public static final long serialVersionUID = 5156116531651651L;

	private String userid;
	private String userpw;
	private String address;
	private String detailaddress;
	private String email;
	private String tel;
	private String nickname;
	private Timestamp joindate;
	private String currentip;
	private String blackyn;
	private String adminyn;
	private String name;
	private int interestcategorynum;

	public UserDTO() {
	}
 
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailaddress() {
		return detailaddress;
	}

	public void setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Timestamp getJoindate() {
		return joindate;
	}

	public void setJoindate(Timestamp joindate) {
		this.joindate = joindate;
	}

	public String getCurrentip() {
		return currentip;
	}

	public void setCurrentip(String currentip) {
		this.currentip = currentip;
	}

	public String getBlackyn() {
		return blackyn;
	}

	public void setBlackyn(String blackyn) {
		this.blackyn = blackyn;
	}

	public String getAdminyn() {
		return adminyn;
	}

	public void setAdminyn(String adminyn) {
		this.adminyn = adminyn;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getInterestcategorynum() {
		return interestcategorynum;
	}
	
	public void setInterestcategorynum(int interestcategorynum) {
		this.interestcategorynum = interestcategorynum;
	}

	@Override
	public String toString() {
		return "UserDTO [userid=" + userid + ", userpw=" + userpw + ", address=" + address + ", detailaddress="
				+ detailaddress + ", email=" + email + ", tel=" + tel + ", nickname=" + nickname + ", joindate="
				+ joindate + ", currentip=" + currentip + ", blackyn=" + blackyn + ", adminyn=" + adminyn
				+ ", name=" + name + ", interestcategorynum=" + interestcategorynum + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
