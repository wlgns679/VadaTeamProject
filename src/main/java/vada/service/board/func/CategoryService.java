package vada.service.board.func;

import java.util.List;

import vada.dto.CategoryDTO;

public interface CategoryService extends BoardFuncService {

	public abstract List<CategoryDTO> getCategoryList() throws Exception;
	
}
