package vada.service.user.search;

public interface SearchUserPWService extends SearchUserService {
	
	public abstract String searchUserPW(String userid, String email)throws Exception;
}
