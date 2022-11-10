package vada.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class BoardDTO implements Serializable{

	public static final long serialVersionUID = 5156115616581816314L;
	 
	private String sellerid;
	private int productnum;
	private String title;
	private Timestamp wdate;
	private String content;
	private int bcategorynum;
	private String buyerid;
	private Timestamp soldoutdate;
	private String review;
	private int reviewscore;
	private String reservation;
	private String reserveid;
	
	public BoardDTO() {
	}

	public String getSellerid() {
		return sellerid;
	}

	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}

	public int getProductnum() {
		return productnum;
	}

	public void setProductnum(int productnum) {
		this.productnum = productnum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getWdate() {
		return wdate;
	}

	public void setWdate(Timestamp wdate) {
		this.wdate = wdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getBcategorynum() {
		return bcategorynum;
	}

	public void setBcategorynum(int bcategorynum) {
		this.bcategorynum = bcategorynum;
	}

	public String getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}

	public Timestamp getSoldoutdate() {
		return soldoutdate;
	}

	public void setSoldoutdate(Timestamp soldoutdate) {
		this.soldoutdate = soldoutdate;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getReviewscore() {
		return reviewscore;
	}

	public void setReviewscore(int reviewscore) {
		this.reviewscore = reviewscore;
	}

	public String getReservation() {
		return reservation;
	}

	public void setReservation(String reservation) {
		this.reservation = reservation;
	}

	public String getReserveid() {
		return reserveid;
	}

	public void setReserveid(String reserveid) {
		this.reserveid = reserveid;
	}

	@Override
	public String toString() {
		return "BoardDTO [sellerid=" + sellerid + ", productnum=" + productnum + ", title=" + title + ", wdate=" + wdate
				+ ", content=" + content + ", bcategorynum=" + bcategorynum + ", buyerid=" + buyerid + ", soldoutdate="
				+ soldoutdate + ", review=" + review + ", reviewscore=" + reviewscore + ", reservation=" + reservation
				+ ", reserveid=" + reserveid + "]";
	}

}
