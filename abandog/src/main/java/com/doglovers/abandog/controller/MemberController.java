package com.doglovers.abandog.controller;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.doglovers.abandog.dao.MemberDAO;
import com.doglovers.abandog.dto.MemberDTO;



@Controller
@RequestMapping("/")
public class MemberController {
	
	@Resource(name = "MemberDAO")
	private MemberDAO MemberDAO;
	
	// 회원등록(id, email 중복확인)(성공시 로그인까지)
	@RequestMapping("/insertMember")
	public String insertMember(@ModelAttribute("MemberDTO") MemberDTO userVO, HttpServletRequest request, Model model) throws Exception {
		System.out.println(userVO.getId());
		int flagid = MemberDAO.checkId(userVO.getId());
		int flagemail = MemberDAO.checkEmail(userVO.getEmail());
		try {
			if(flagid == 1) {
				//id가 있다면
				model.addAttribute("message", "이미 존재하는 아이디 입니다.");
				return "index";
			
			}
			else if(flagemail == 1){
				//email이 있다면
				model.addAttribute("message", "이미 존재하는 이메일 입니다.");
				return "index";
			}
			else{
				MemberDAO.insertMemDTO(userVO);
				// 로그인 정보를 세션에 저장
				request.getSession().setAttribute("loginVO", userVO);
	
				HttpServletRequest httpRequest = (HttpServletRequest) request;
				HttpSession session = httpRequest.getSession();
				//세션 로그인
				session.setAttribute("loginVO", userVO);
				
				// 로그인 후 모니터링화면 호출
				return "redirect:/index";
			}
			// 요기에서~ 입력된 아이디가 존재한다면 -> 다시 회원가입 페이지로 돌아가기 
			// 존재하지 않는다면 -> register
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	@RequestMapping("/login")
	public String loginMember(@ModelAttribute("MemberDTO") MemberDTO userVO, HttpServletRequest request, Model model) throws Exception {
		System.out.println(userVO.getId());
		// 회원정보확인
		
		return "redirect:/index";
	}
}
