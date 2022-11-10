package vada.service.board.func;

import java.util.List;
import java.util.Map;

public interface LikeService extends BoardFuncService {

	public abstract int likeAdd(String userid, int productnum) throws Exception; 

	public abstract List<Map<String, Object>> likeList(String userid) throws Exception;  

	public abstract int likeDelete(String userid, int productnum) throws Exception;  
	
	public abstract List likeCheck(String userid) throws Exception;
	
	public abstract List<Integer> get_Productnum(String userid) throws Exception;

}
