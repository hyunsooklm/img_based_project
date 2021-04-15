package com.doglovers.abandog.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.doglovers.abandog.dao.DogDAO;
import com.doglovers.abandog.dto.C;
import com.doglovers.abandog.dto.DogDTO;
import com.doglovers.abandog.dto.Pagination;
import com.doglovers.abandog.dto.Result;

@Controller
@RequestMapping("/")
public class DogController {
	
	@RequestMapping("/main")
	public String main(Model model) {
		return "main";
	}
	
	@RequestMapping("/searchbycategory")
	public String searchbycategory(Model model) {
		DogDAO dao =  C.sqlSession.getMapper(DogDAO.class);
		model.addAttribute("total", dao.selectDogListNum());
		model.addAttribute("page", 1);
		model.addAttribute("gender", 0);
		model.addAttribute("neuter", 0);
		model.addAttribute("location", 0);
		return "searchbycategory";
	}
	
	@RequestMapping("/searchbycategory2")
	public String searchbycategory2(Model model, int page, int gender, int neuter, int location) {	
		model.addAttribute("page", page);
		model.addAttribute("gender", gender);
		model.addAttribute("neuter", neuter);
		model.addAttribute("location", location);
		
		Pagination pagination = new Pagination();
		pagination.setGender(gender);
		pagination.setNeuter(neuter);
		pagination.setLocation(location);
		
		DogDAO dao =  C.sqlSession.getMapper(DogDAO.class);
		model.addAttribute("total", dao.selectDogListNum2(pagination));
		model.addAttribute("page", page);
		model.addAttribute("gender", gender);
		model.addAttribute("neuter", neuter);
		model.addAttribute("location", location);
		return "searchbycategory";
	}
	
	@RequestMapping("/getDogList")
	@ResponseBody
	public Result getDogList(Model model, int page, int gender, int neuter, int location) {
		model.addAttribute("page", page);
		model.addAttribute("gender", gender);
		model.addAttribute("neuter", neuter);
		model.addAttribute("location", location);
		Pagination pagination = new Pagination();
		
		pagination.setListSize(12);
		pagination.setStartList((page - 1) * 12);
		pagination.setGender(gender);
		pagination.setNeuter(neuter);
		pagination.setLocation(location);
		
		DogDAO dao =  C.sqlSession.getMapper(DogDAO.class);
		Result result = new Result();
		
		ArrayList<DogDTO> list = dao.selectDogList(pagination);
		result.setList(list);
		result.setCount(list.size());
		
		return result;
	}
	
	@RequestMapping("/dogInfo")
	public String dogInfo(Model model, int cid) {	
		model.addAttribute("cid", cid);
		
		DogDAO dao =  C.sqlSession.getMapper(DogDAO.class);
		model.addAttribute("dog", dao.selectDog(cid));

		return "dogInfo";
	}
	
}
