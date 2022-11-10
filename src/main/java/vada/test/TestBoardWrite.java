package vada.test;

public class TestBoardWrite {
	
	public static void main(String[] args) {
//		BoardWriteService writeService = new BoardWriteDAOImpl();
//		BoardDTO boardDTO = new BoardDTO();
//		
//		boardDTO.setSellerid("testid5");
//		boardDTO.setTitle("손용균 팜 제시~");
//		boardDTO.setContent("네고 잘해드려요~");
//		boardDTO.setBcategorynum(201);
//		boardDTO.setBuyerid("testid2");
		
		int result = 0;
		
		try {
//			result = writeService.writeBoard(boardDTO);
			System.out.println("결과 반영 행 수 : " + result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} // main
} // class

