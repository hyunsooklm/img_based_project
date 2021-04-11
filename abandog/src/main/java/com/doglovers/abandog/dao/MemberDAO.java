package com.doglovers.abandog.dao;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.doglovers.abandog.dto.MemberDTO;


@Repository("MemberDAO")
public class MemberDAO extends AbstractDAO{

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
