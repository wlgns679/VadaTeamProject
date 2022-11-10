package vada.dao.board.crud;

import java.util.Map;

import vada.service.board.crud.BoardDetailService;

public interface BoardDetailDAO extends BoardDetailService {
   
   public abstract Map<String, Object> getBoardList(int bid) throws Exception;
   public abstract Map<String, Object> notifyView(int notifyid) throws Exception ;
   public abstract int reserveBoard(int productnum, String command, String userid) throws Exception;

}