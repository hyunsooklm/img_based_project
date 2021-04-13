package com.doglovers.abandog.dto;

public class Pagination {
	
	private int listSize;
	private int startList;
	private int gender;
	private int neuter;
	private int location;
	
	public Pagination() {
		super();
	}

	public Pagination(int listSize, int startList, int gender, int neuter, int location) {
		super();
		this.listSize = listSize;
		this.startList = startList;
		this.gender = gender;
		this.neuter = neuter;
		this.location = location;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getStartList() {
		return startList;
	}

	public void setStartList(int startList) {
		this.startList = startList;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getNeuter() {
		return neuter;
	}

	public void setNeuter(int neuter) {
		this.neuter = neuter;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
}
