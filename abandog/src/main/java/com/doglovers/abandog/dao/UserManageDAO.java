package com.doglovers.abandog.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.doglovers.abandog.dto.DogDTO;
import com.doglovers.abandog.dto.MemberDTO;
import com.doglovers.abandog.dto.Pagination;

@Repository("UserManageDAO")
public class UserManageDAO extends AbstractDAO{
	public int checkId(String id) throws Exception{
		return (int) selectOne("checkId", id);
	}
	public int checkEmail(String email) throws Exception{
		return (int) selectOne("checkEmail", email);
	}
	public void insertMember(MemberDTO userVO) throws Exception{
		insert("insertMember",userVO);
	}
	public MemberDTO login(MemberDTO userVO) throws Exception{
		return (MemberDTO) selectOne("login", userVO);
	}
	public void updtMember(MemberDTO userVO) throws Exception{
		update("updtMember", userVO);
	}
	public String selectId(MemberDTO userVO) throws Exception{
		return (String) selectOne("selectId", userVO);
	}
	public int selectPw(MemberDTO userVO) throws Exception{
		return (int) selectOne("selectPw", userVO);
	}
	public String se_pw(MemberDTO userVO) {
		return (String) selectOne("se_pw", userVO);
	}
}
