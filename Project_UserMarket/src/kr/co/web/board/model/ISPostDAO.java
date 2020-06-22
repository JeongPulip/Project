package kr.co.web.board.model;

public interface ISPostDAO {
	
	  //거래 게시글 정보 입력 메서드 
	  public boolean registSPost(String ctgId, String cityId, String sType, String progress, Integer price);
	  
	  //거래 게시글 삭제 메서드
	  public boolean deleteSPost(int postId);
	  
	  //거래 게시글 재등록 메서드
	  public boolean updateSPost(int postId, String ctgId, String cityId, String sType, String progress, Integer price);

	  //s_post테이블 정보 검색 메서드
	  public SPostVO selectSPost(int postId);
	  
	  //거래글 조건부 검색 메서드 (조건: postId오른차순, 조회수순, 카테고리별, 지역별, 거래방식 별, 진행상태별, ... )
//	  public SPostVO selectIfSPost(int cityId, String sType, String progress);

}
