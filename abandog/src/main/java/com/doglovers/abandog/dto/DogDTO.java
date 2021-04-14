package com.doglovers.abandog.dto;

public class DogDTO {
	
	private String age;
	private String careAddr;
	private String careNm;
	private String careTel;
	private String color;
	private String img;
	private String kind;
	private String sex;
	private String neuter;
	private int cid;
	
	public DogDTO() {
		super();
	}
	
	public DogDTO(String age, String careAddr, String careNm, String careTel, String color, String img, String kind,
			String sex, String neuter, int cid) {
		super();
		this.age = age;
		this.careAddr = careAddr;
		this.careNm = careNm;
		this.careTel = careTel;
		this.color = color;
		this.img = img;
		this.kind = kind;
		this.sex = sex;
		this.neuter = neuter;
		this.cid = cid;
	}
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCareAddr() {
		return careAddr;
	}
	public void setCareAddr(String careAddr) {
		this.careAddr = careAddr;
	}
	public String getCareNm() {
		return careNm;
	}
	public void setCareNm(String careNm) {
		this.careNm = careNm;
	}
	public String getCareTel() {
		return careTel;
	}
	public void setCareTel(String careTel) {
		this.careTel = careTel;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNeuter() {
		return neuter;
	}
	public void setNeuter(String neuter) {
		this.neuter = neuter;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
}
