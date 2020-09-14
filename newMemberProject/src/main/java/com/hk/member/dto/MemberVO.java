package com.hk.member.dto;

import java.util.Date;
//@Getter @Setter 쓰면 따로 게터세터 안써도됨
public class MemberVO {

	int mno;
	String email;
	String pwd;
	String mname;
	Date cre_date; // import java.util
	Date mod_date;
	//getter/setter 만들고
	//toString->값을 한번에 찍을 때
	//constructor(생성자)
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public Date getCre_date() {
		return cre_date;
	}
	public void setCre_date(Date cre_date) {
		this.cre_date = cre_date;
	}
	public Date getMod_date() {
		return mod_date;
	}
	public void setMod_date(Date mod_date) {
		this.mod_date = mod_date;
	}
	@Override
	public String toString() {
		return "MemberVO [mno=" + mno + ", email=" + email + ", pwd=" + pwd + ", mname=" + mname + ", cre_date="
				+ cre_date + ", mod_date=" + mod_date + "]";
	}
	public MemberVO() {}
	public MemberVO(int mno, String email, String pwd, String mname, Date cre_date, Date mod_date) {
		super();
		this.mno = mno;
		this.email = email;
		this.pwd = pwd;
		this.mname = mname;
		this.cre_date = cre_date;
		this.mod_date = mod_date;
	}
	
	
}
