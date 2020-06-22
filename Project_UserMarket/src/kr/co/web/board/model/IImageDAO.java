package kr.co.web.board.model;

import java.util.List;

public interface IImageDAO {

	//이미지 경로 등록 메서드
	public boolean registImg(String imgPath);
	
	//이미지 경로 검색 메서드
	public List<String> searchImgPath(int postId);
	
	//이미지 경로 삭제 메서드
	public boolean deleteImg(int postId);
	
	//이미지 경로 재등록 메서드
	public boolean updateImg(int postId, String imgPath);
	
	//image테이블 정보 검색 메서드
	public List<ImageVO> selectImage(int postId);

	
}
