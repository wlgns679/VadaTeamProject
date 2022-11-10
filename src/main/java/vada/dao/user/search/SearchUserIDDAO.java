package vada.dao.user.search;

import vada.service.user.search.SearchUserIDService;

public interface SearchUserIDDAO extends SearchUserIDService {

	public abstract String searchUserID(String name, String email) throws Exception;
}
