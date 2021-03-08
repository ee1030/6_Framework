package com.kh.testproject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) 
public class LoginControllerTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	
	@Before // JUnit 테스트 진행 전 먼저 실행하는 것을 지정하는 어노테이션
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test // 테스트용 메소드임을 명시하는 어노테이션
	public void testMemberLogin() throws Exception {
		try{
			mockMvc.perform(post("/login.do").param("id", "admin").param("pwd", "1234"))
				.andDo(print())
				.andExpect(status().isOk());
			
			System.out.println("테스트 성공!!!");
		} 
		catch(Exception e){
			System.out.println("테스트 실패 ㅠㅠ");
		}
	}
	
}
