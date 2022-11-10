package vada.service.board.func;

public interface SoldOutService extends BoardFuncService {
	
	public abstract int soldOut(String reserveid,int productnum) throws Exception;
}
