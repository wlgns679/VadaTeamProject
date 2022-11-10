package vada.dao.board.func;

import java.util.List;

import vada.dto.CategoryDTO;
import vada.service.board.func.CategoryService;

public interface CategoryListDAO extends CategoryService {

	public abstract List<CategoryDTO> getCategoryList() throws Exception;

}
