package vada.dto;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

	public static final long serialVersionUID = 51561165316516541L;
	
		private int categorynum ;
		private String categoryname;
		
		public CategoryDTO() {
		}
 
		public int getCategorynum() {
			return categorynum;
		}

		public void setCategorynum(int categorynum) {
			this.categorynum = categorynum;
		}

		public String getCategoryname() {
			return categoryname;
		}

		public void setCategoryname(String categoryname) {
			this.categoryname = categoryname;
		}

		@Override
		public String toString() {
			return "CategoryDTO [categorynum=" + categorynum + ", categoryname=" + categoryname + "]";
		}
		 
		@Override
		public boolean equals(Object obj) {
			return super.equals(obj);
		}
				
}