package com.kh.testproject;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.testproject.model.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService service;

	@RequestMapping("/login.do")
	public ModelAndView login(HttpServletRequest request, ModelAndView mv) {
		
		// ModelAndView : 데이터 전달 객체인 Model과 연결할 View 이름을 함께 저장하는 객체
		try {
	
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
	
			HashMap<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("id", id);
			paramMap.put("pwd", pwd);
		
			int n = service.login(paramMap);
			String result = "";
			if(n==1)
				result = "로그인 성공!!";
			else
				result = "로그인 실패!!";
			
			mv.addObject("result", result);
			mv.setViewName("loginTest");
	
		} catch (Exception e) {
			mv.addObject("error", "로그인 중 오류가 발생하였습니다");
			mv.setViewName("error");
		}

		return mv;
	}
}
