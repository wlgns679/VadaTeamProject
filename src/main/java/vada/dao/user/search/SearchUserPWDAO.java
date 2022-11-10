package vada.dao.user.search;

import vada.service.user.search.SearchUserPWService;

public interface SearchUserPWDAO extends SearchUserPWService {

	public abstract String searchUserPW(String userid, String email) throws Exception;
}
