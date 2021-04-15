package com.doglovers.abandog.controller;

import java.util.ArrayList;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.*;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.doglovers.abandog.dao.DogDAO;
import com.doglovers.abandog.dao.UserManageDAO;
import com.doglovers.abandog.dto.C;
import com.doglovers.abandog.dto.DogDTO;
import com.doglovers.abandog.dto.MemberDTO;
import com.doglovers.abandog.dto.Pagination;
import com.doglovers.abandog.dto.PasswordEncoding;
import com.doglovers.abandog.dto.Result;

@Controller
@RequestMapping("/")
public class UserManageController {

	@Resource(name = "UserManageDAO")
	private UserManageDAO UserManageDAO;


	public static String getRandomStr(int size) {
		if (size > 0) {
			char[] tmp = new char[size];
			for (int i = 0; i < tmp.length; i++) {
				int div = (int) Math.floor(Math.random() * 2);

				if (div == 0) { // 0이면 숫자로
					tmp[i] = (char) (Math.random() * 10 + '0');
				} else { // 1이면 알파벳
					tmp[i] = (char) (Math.random() * 26 + 'A');
				}
			}
			return new String(tmp);
		}
		return "ERROR : Size is required.";
	}

	// 회원등록
	@RequestMapping("/insertMember")
	public String insertMember(@ModelAttribute("MemberDTO") MemberDTO userVO, HttpServletRequest request, Model model)
			throws Exception {
		
		PasswordEncoding pe = new PasswordEncoding();
		userVO.setPw(pe.encode(userVO.getPw()));
		UserManageDAO.insertMember(userVO); // 이 때 회원가입하며 생긴 uid를 가져와서 userVO에 넣는다.		
		return "redirect:/main";
	}

	// 회원등록시 id중복확인 ajax
	@RequestMapping("/checkId")
	@ResponseBody
	public String checkId(@ModelAttribute("MemberDTO") MemberDTO userVO, HttpServletRequest request, Model model)
			throws Exception {
		int flagid = UserManageDAO.checkId(userVO.getId());
		if (flagid == 1) {
			// id가 있다면
			return "fail";
		}
		return "success";
	}

	// 회원등록시 email중복확인 ajax
	@RequestMapping("/checkEmail")
	@ResponseBody
	public String checkEmail(@ModelAttribute("MemberDTO") MemberDTO userVO, HttpServletRequest request, Model model)
			throws Exception {
		int flagemail = UserManageDAO.checkEmail(userVO.getEmail());
		if (flagemail == 1) {
			// 이메일이 있다면
			return "fail";
		}
		return "success";
	}

	@RequestMapping("/login")
	@ResponseBody
	public String loginMember(@ModelAttribute("MemberDTO") MemberDTO userVO, HttpServletRequest request, Model model)
			throws Exception {
		// id로 암호화된 pw가져오기
		String se_pw = UserManageDAO.se_pw(userVO);
		if (se_pw.equals("")) {
			// 해당 id가 없다면
			return "fail";
		}
		// 해당 id가 있다면
		PasswordEncoding pe = new PasswordEncoding();
		Boolean flag = pe.matches(userVO.getPw(), se_pw);
		if (!flag) {
			return "fail";
		}
		// 회원정보확인 id로-> UID가져오기
		MemberDTO mem = UserManageDAO.login(userVO);
		if (mem.getId().equals(userVO.getId())) {
			mem.setPw(userVO.getPw());
			request.getSession().setAttribute("loginVO", mem);
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpSession session = httpRequest.getSession();
			// 세션 로그인
			session.setAttribute("loginVO", mem);
			// 로그인 후 모니터링화면 호출
			return "success";
		}else {
			return "fail";
		}

	}

	@RequestMapping("/logout")
	public String logoutMember(@ModelAttribute("MemberDTO") MemberDTO userVO, HttpServletRequest request, Model model)
			throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		session.invalidate();

		return "redirect:main";
	}

	@RequestMapping("/updtMember")
	public String updtMember(@ModelAttribute("MemberDTO") MemberDTO userVO, HttpServletRequest request, Model model)
			throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		session.invalidate();
		
		PasswordEncoding pe = new PasswordEncoding();
		String se_pw = userVO.getPw();
		userVO.setPw(pe.encode(se_pw));

		UserManageDAO.updtMember(userVO);
		model.addAttribute("message", "다시 로그인하여 주세요.");
		return "redirect:main";
	}

	// id찾기(이름과 이메일을 통해서 찾기) 지금은 ajax로 success중 result가 비어있으면 없는거라고, result가 있으면
	// alert창을 통해서 띄우면 가장 쉽게 구현가능
	@RequestMapping("/selectId")
	@ResponseBody
	public String selectId(@ModelAttribute("MemberDTO") MemberDTO userVO, HttpServletRequest request, Model model)
			throws Exception {
		String id = UserManageDAO.selectId(userVO);
		String result ="";
		
		for (int i = 0; i < id.length(); i++) {
			if(i < 5) {
				result += id.charAt(i);
			} else {
				result += "*";
			}
			
		}
		return result;
	}

	// pw찾기(이름, 이메일, id를 통해서 찾고 email에 임시비밀번호 보내주기)
	@RequestMapping("/selectPw")
	@ResponseBody
	public String selectPw(@ModelAttribute("MemberDTO") MemberDTO userVO, HttpServletRequest request, Model model)
			throws Exception {
		// 해당 이름, 이메일 id의 정보가 있는지 확인
		int flag = UserManageDAO.selectPw(userVO);
		// 성공시 pw updt
		if (flag == 1) {
			// 해당 정보 있을 때!
			// 랜덤 문자열 생성
			String new_pw = getRandomStr(6);

			// userVO에 넣어주기
			userVO.setPw(new_pw);
			// updt
			PasswordEncoding pe = new PasswordEncoding();
			userVO.setPw(pe.encode(new_pw));
			UserManageDAO.updtMember(userVO);
			// 이메일 보내기

			final String SMTP_USERNAME = "slavh96@naver.com";
			final String SMTP_PASSWORD = "eu9578";
			String HOST = "smtp.naver.com";

			Properties props = new Properties();
			props.put("mail.smtp.host", HOST);
			props.put("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
				}
			});
			System.out.println(userVO.getEmail());
			String TO = userVO.getEmail();
			MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_USERNAME));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));
            message.setSubject("Abandog  " + userVO.getName() + " 회원님 비밀번호 찾기 메일입니다.");
            message.setText("임시 비밀번호는 " + new_pw + " 입니다.");
            Transport.send(message);
            
            return "success";

		} else {
			// 해당 정보에 부합하는 회원정보 없음!
			return "fail";
		}
	}
	
	@RequestMapping("/checkEmailNum")
	@ResponseBody
	public String checkEmailNum(@ModelAttribute("MemberDTO") MemberDTO userVO, HttpServletRequest request, Model model)
			throws Exception {

			 char pwCollection[] = new char[] {'1','2','3','4','5','6','7','8','9','0'};
	
			 String ranNum = "";
	  
	        for (int i = 0; i < 6; i++) {
	          int selectRandomPw = (int)(Math.random()*(pwCollection.length));
	          ranNum += pwCollection[selectRandomPw];
	        }

			// 이메일 보내기
	        try {
	        	final String SMTP_USERNAME = "slavh96@naver.com";
				final String SMTP_PASSWORD = "eu9578";
				String HOST = "smtp.naver.com";

				Properties props = new Properties();
				props.put("mail.smtp.host", HOST);
				props.put("mail.smtp.auth", "true");

				Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
					}
				});

				String TO = userVO.getEmail();
				MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(SMTP_USERNAME));
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));
	            message.setSubject("이메일 인증번호 메일입니다.");
	            message.setText("인증번호는 " + ranNum + " 입니다. ");
	            Transport.send(message);
	            
	            return ranNum;
			} catch (Exception e) {
				return "fail";
			}
	}
	
	@RequestMapping("/join")
	public String join(Model model) {
		return "join";
	}
	
	@RequestMapping("/logIn")
	public String logIn(Model model) {
		return "logIn";
	}
	
}