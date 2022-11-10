package vada.dto;

import java.io.Serializable;

public class ImgDTO implements Serializable{
	public static final long serialVersionUID = 755116848163L;

	
	private int imgproductnum;
	private int imgnum;
	private String imgsname;
	private int imgsize;
	private String imgcname;

	public ImgDTO() {
	}  

	public int getImgproductnum() {
		return imgproductnum;
	}

	public void setImgproductnum(int imgproductnum) {
		this.imgproductnum = imgproductnum;
	}

	public int getImgnum() {
		return imgnum;
	}

	public void setImgnum(int imgnum) {
		this.imgnum = imgnum;
	}

	public String getImgsname() {
		return imgsname;
	}

	public void setImgsname(String imgsname) {
		this.imgsname = imgsname;
	}

	public int getImgsize() {
		return imgsize;
	}

	public void setImgsize(int imgsize) {
		this.imgsize = imgsize;
	}

	public String getImgcname() {
		return imgcname;
	} 

	public void setImgcname(String imgcname) {
		this.imgcname = imgcname;
	}

	@Override
	public String toString() {
		return "ImgBean [imgproductnum=" + imgproductnum + ", imgnum=" + imgnum + ", imgsname=" + imgsname
				+ ", imgsize=" + imgsize + ", imgcname=" + imgcname + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}


}
