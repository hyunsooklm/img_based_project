package com.doglovers.abandog.dao;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.doglovers.abandog.dto.MemberDTO;


@Repository("MemberDAO")
public class MemberDAO extends AbstractDAO{
	
	public int test123() throws Exception{
		return (int) selectOne("test123");
	}
}
