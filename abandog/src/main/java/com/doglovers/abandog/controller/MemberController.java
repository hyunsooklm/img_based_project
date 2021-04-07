package com.doglovers.abandog.controller;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.doglovers.abandog.dao.MemberDAO;



@Controller
@RequestMapping("/")
public class MemberController {
	
	@Resource(name = "MemberDAO")
	private MemberDAO MemberDAO;
	
	@RequestMapping("/")
	public String main(Model model) throws Exception {
		int ar = MemberDAO.test123();
		System.out.println(ar);
		return "main";
	}
	
	
}
