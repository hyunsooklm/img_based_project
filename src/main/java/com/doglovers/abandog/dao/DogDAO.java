package com.doglovers.abandog.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.doglovers.abandog.dto.DogDTO;
import com.doglovers.abandog.dto.Pagination;

public interface DogDAO {
	int selectDogListNum();
	int selectDogListNum2(Pagination pagination);
	ArrayList<DogDTO> selectDogList(Pagination pagination);
	DogDTO selectDog(int cid);
}
