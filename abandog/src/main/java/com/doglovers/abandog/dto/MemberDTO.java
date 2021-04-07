package com.doglovers.abandog.dto;

public class MemberDTO {
	
	private int uid;
	private String id;
	private String pw;
	private String name;
	private String email;
	private String regDate;
	
	public MemberDTO() {
		super();
	}

	public MemberDTO(int uid, String id, String pw, String name, String email, String regDate) {
		super();
		this.uid = uid;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.regDate = regDate;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
}
