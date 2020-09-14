package com.hk.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.member.dto.MemberVO;
import com.hk.member.mapper.MemberMapper;


@Service
public class MemberService {

	@Autowired
	MemberMapper memberMapper;
	
	public List<MemberVO> memberList(){
		return memberMapper.memberList(); //mybatis를 호출
		
	}
	public MemberVO memberGetOne(int mno) {
		return memberMapper.memberGetOne(mno);
		
	}
	public int memberRegister(MemberVO memberVO) {
		
		return memberMapper.memberRegister(memberVO);
	}
	public int memberUpdate(MemberVO memberVO) {
		
		return memberMapper.memberUpdate(memberVO);
	}
	public int memberDelete(MemberVO memberVO) {
		
		return memberMapper.memberDelete(memberVO);
	}
	public MemberVO memberLogin(MemberVO memberVO) {
		
		return memberMapper.memberLogin(memberVO);
	}
	public MemberVO checkIdDup(String email){
	
		return memberMapper.checkIdDup(email);
	}
}
