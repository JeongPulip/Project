package kr.co.web.board.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.web.board.model.PostAll2VO;
import kr.co.web.etc.service.InterfaceService;

public class AjaxLoadPostInfoService implements InterfaceService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

//		String tel = (String)request.getSession().getAttribute("tel");
//		String ri = (String)request.getSession().getAttribute("region_id");
//		String ci = (String)request.getSession().getAttribute("city_id");
//		String result =
//		"{\"tel\":\"" + tel + "\",\"ri\":\"" + ri + "\",\"ci\":\"" + ci + "\"}";
//		
////		System.out.println(result);
//		
//		response.setContentType("application/json; charset=utf-8");
//		
//		PrintWriter pw = null;
//		try {
//			pw = response.getWriter();
//			pw.write(result);
//			pw.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if(pw != null) pw.close();
//		}// finally-end	
		
		PostAll2VO postInfo = (PostAll2VO)request.getSession().getAttribute("postInfo");
		
		String ca = postInfo.getCategoryVO().getCtg_id();
		
		String pr = postInfo.getsPostVO().getProgress();

		String ri = postInfo.getCityVO().getRegion_id();
		String ci = postInfo.getCityVO().getCity_id();
		
		String st = postInfo.getsPostVO().getsType();

		String result = "{\"ca\":\"" + ca + "\","
						+ "\"pr\":\""+ pr +"\","
						+ "\"ri\":\"" + ri + "\","
						+ "\"ci\":\"" + ci + "\"}";
		
		response.setContentType("application/json; charset=utf-8");
		
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(pw != null) pw.close();
		}// finally-end
	}

}








