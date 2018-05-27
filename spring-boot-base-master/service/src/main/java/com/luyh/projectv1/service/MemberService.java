package com.luyh.projectv1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luyh.projectv1.dao.classses.UserDao;


@Service
public class MemberService implements IMemberService{

    @Autowired
    UserDao daoMember;


	/*@Override
	public Member findMemberByUid(String uid) throws Exception {
		User user=daoMember.loadUserByUsername(uid);
		Member member =new Member();
		member.setUname(user.getDireccion());
		return member;
	}
*/
}
