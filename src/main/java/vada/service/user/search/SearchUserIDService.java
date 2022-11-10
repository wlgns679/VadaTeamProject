package vada.service.user.search;

public interface SearchUserIDService extends SearchUserService {
	
	public abstract String searchUserID(String name, String email) throws Exception;
	
}
