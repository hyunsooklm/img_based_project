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
	public int checkEmail(String email) {
		return (int) selectOne("checkemail", email);
	}
	public void insertMemDTO(MemberDTO userVO) {
		insert("insertMember",userVO);
	}
}
