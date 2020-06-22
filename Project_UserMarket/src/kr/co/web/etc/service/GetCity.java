package kr.co.web.etc.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.co.web.etc.model.CityDAO;
import kr.co.web.etc.model.CityVO;

public class GetCity implements InterfaceService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String region_id = request.getParameter("region_id");
		ArrayList<CityVO> clist = CityDAO.getInstance().getCity(region_id);
		String result = new Gson().toJson(clist);
//		System.out.println(result);
		
	//============= 이것도 안써주면 받아온 값이 제대로 출력이 안된다 ============
		response.setContentType("application/json; charset=utf-8");
	//=============================================================	
		
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
