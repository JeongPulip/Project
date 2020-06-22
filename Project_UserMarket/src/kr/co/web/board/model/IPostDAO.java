package kr.co.web.board.model;

import java.util.List;

public interface IPostDAO {

	//작성글을 등록하는 메서드
	public boolean regist(String userId, String title, String content);
	
	//모든 게시글 정보를 가져오는 메서드(글 목록 리스트)
	public List<PostVO> listBoard();
	
	//글 상세보기 메서드
	public PostVO contentBoard(int postId);
	
	//게시글 수정 메서드 
	public void updateBoard(int postId, String title, String content);
	
	//게시글 삭제 메서드
	public void deleteBoard(int postId);
	
	//게시글 검색 메서드
	public List<PostVO> searchList(String search);
	
	//조회수 갱신 메서드
	public boolean hitUp(int postId);
	
	//전체 게시글 조건부 정렬 메서드
	public List<PostVO> orderByList(int N);	// N : 구분자(1: postId 내림차순, 2: hit 내림차순)
	
//	//이미지 경로 등록 메서드 
//	public boolean registImg(String imgPath);
//	  
//	//이미지 경로 검색 메서드 
//	public List<String> searchImg(String postId);
//	  
//	//이미지 경로 삭제 메서드 
//	public void deleteImg(String postId);
//	  
//	//이미지 경로 재등록 메서드 
//	public boolean updateImg(String postId, String imgPath);
	 
	
	
//	  //거래 게시글 정보 입력 메서드 
//	  public boolean registSPost(String ctgId, String cityId, String sType, String progress, Integer price);
	 
	
}
