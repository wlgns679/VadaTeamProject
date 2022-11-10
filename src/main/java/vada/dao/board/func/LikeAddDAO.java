package vada.dao.board.func;

import java.util.List;

import vada.service.board.func.LikeService;

public interface LikeAddDAO extends LikeService {
	
	public abstract int likeAdd(String userid, int productnum) throws Exception; 
	
	public abstract List<Integer> get_Productnum(String userid) throws Exception;
}
