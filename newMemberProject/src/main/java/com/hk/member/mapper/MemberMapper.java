package com.hk.member.mapper;

import java.util.List;

import com.hk.member.dto.MemberVO;


public interface MemberMapper {

	public List<MemberVO> memberList();
	
	public int memberRegister(MemberVO memberVO);
	
	public MemberVO memberGetOne(int mno);
	
	public int memberUpdate(MemberVO memberVO);

	public int memberDelete(MemberVO memberVO);
	
	public MemberVO memberLogin(MemberVO memberVO);
	
	public MemberVO checkIdDup(String email);


}
