package vada.dao.board.func;

import vada.service.board.func.SoldOutService;

public interface SoldOutDAO extends SoldOutService {

	public abstract int soldOut(String reserveid,int productnum) throws Exception;
	
}
