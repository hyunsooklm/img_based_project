package com.doglovers.abandog.controller;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.doglovers.abandog.dao.MemberDAO;
import com.doglovers.abandog.dao.UserManageDAO;
import com.doglovers.abandog.dto.C;
import com.doglovers.abandog.dto.DogDTO;
import com.doglovers.abandog.dto.MemberDTO;
import com.doglovers.abandog.dto.Pagination;
import com.doglovers.abandog.dto.Result;

@Controller
@RequestMapping("/")
public class UserManageController {

	@Resource(name = "UserManageDAO")
	private UserManageDAO UserManageDAO;

	// 회원등록(성공시 로그인까지)
	@RequestMapping("/insertMember")
	public String insertMember(@ModelAttribute("MemberDTO") MemberDTO userVO, HttpServletRequest request, Model model)
			throws Exception {
		UserManageDAO.insertMemDTO(userVO);
		// 로그인 정보를 세션에 저장
		System.out.println(userVO.getUid());
		request.getSession().setAttribute("loginVO", userVO);

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		// 세션 로그인
		session.setAttribute("loginVO", userVO);

		// 로그인 후 모니터링화면 호출
		return "redirect:/main";
	}

	@RequestMapping("/checkId")
	public String idcheck(@ModelAttribute("MemberDTO") MemberDTO userVO, HttpServletRequest request, Model model)
			throws Exception {
		System.out.println(userVO.getId());
		int flagid = UserManageDAO.checkId(userVO.getId());
		if (flagid == 1) {
			// id가 있다면
			model.addAttribute("message", "이미 존재하는 아이디 입니다.");
			return "fail";
		}
		model.addAttribute("message", "사용가능한 아이디 입니다.");
		return "success";
	}

	@RequestMapping("/checkEmail")
	public String checkEmail(@ModelAttribute("MemberDTO") MemberDTO userVO, HttpServletRequest request, Model model)
			throws Exception {
		System.out.println(userVO.getId());
		int flagemail = UserManageDAO.checkId(userVO.getEmail());
		if (flagemail == 1) {
			// 이메일이 있다면
			model.addAttribute("message", "이미 존재하는 이메일 입니다.");
			return "fail";
		}
		model.addAttribute("message", "사용가능한 이메일 입니다.");
		return "success";
	}

	@RequestMapping("/login")
	public String loginMember(@ModelAttribute("MemberDTO") MemberDTO userVO, HttpServletRequest request, Model model)
			throws Exception {
		System.out.println(userVO.getId());
		// 회원정보확인 id,pw로-> UID가져오기
		MemberDTO mem = UserManageDAO.login(userVO);
		if (mem.getId().equals(userVO.getId())) {
			request.getSession().setAttribute("loginVO", mem);
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpSession session = httpRequest.getSession();
			// 세션 로그인
			session.setAttribute("loginVO", mem);
			// 로그인 후 모니터링화면 호출
			return "redirect:/main";
		}
		model.addAttribute("message", "회원정보를 다시 한번 확인하여주세요.");
		return "main";
	}
	@RequestMapping("/logout")
	public String logoutMember(@ModelAttribute("MemberDTO") MemberDTO userVO, HttpServletRequest request, Model model)
			throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		session.invalidate();
		
		return "redirect:main";
	}
}