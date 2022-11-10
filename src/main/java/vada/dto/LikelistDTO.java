package vada.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class LikelistDTO implements Serializable{
	public static final long serialVersionUID = 515521984197818163L;
	 
	private String  likeuserid;
	private int likeproductnum;
	private Timestamp likedate;
	
	public LikelistDTO() {
	}

	public String getLikeuserid() {
		return likeuserid;
	}

	public void setLikeuserid(String likeuserid) {
		this.likeuserid = likeuserid;
	}

	public int getLikeproductnum() {
		return likeproductnum;
	}

	public void setLikeproductnum(int likeproductnum) {
		this.likeproductnum = likeproductnum;
	}

	public Timestamp getLikedate() {
		return likedate;
	}

	public void setLikedate(Timestamp likedate) {
		this.likedate = likedate;
	}

	public LikelistDTO(String likeuserid, int likeproductnum, Timestamp likedate) {
		super();
		this.likeuserid = likeuserid;
		this.likeproductnum = likeproductnum;
		this.likedate = likedate;
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
