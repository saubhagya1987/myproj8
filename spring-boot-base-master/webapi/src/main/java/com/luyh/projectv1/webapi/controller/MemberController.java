package com.luyh.projectv1.webapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.luyh.projectv1.service.IMemberService;

@RestController
public class MemberController {

    @Autowired
    IMemberService memberService;
/*
    @RequestMapping(value="/member/{memberId}",method = RequestMethod.GET, produces = "application/json")
    Member findMemberByMemberId(@PathVariable("memberId") String memberId) throws Exception{
       return memberService.findMemberByUid(memberId);
    }

    @RequestMapping(value="/test",method = RequestMethod.GET, produces = "application/json")
    Member findMemberByMemberId() throws Exception{
    	Member member=new Member();
    	member.setEmail("test");
       return member;
    }*/



}
