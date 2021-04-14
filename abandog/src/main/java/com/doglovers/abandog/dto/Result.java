package com.doglovers.abandog.dto;

import java.util.ArrayList;

public class Result {
	private int count;
	private ArrayList<DogDTO> list;
	
	public Result() {
		super();
	}
	
	public Result(int count, ArrayList<DogDTO> list) {
		super();
		this.count = count;
		this.list = list;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ArrayList<DogDTO> getList() {
		return list;
	}
	public void setList(ArrayList<DogDTO> list) {
		this.list = list;
	}

}
