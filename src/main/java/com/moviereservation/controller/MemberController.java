package com.moviereservation.controller;

import com.moviereservation.domain.Login;
import com.moviereservation.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/login")
    public void login(){

    }

    @PostMapping("/login")
    public String login(Login login, Model model){
        if(memberService.checkLogin(login)){
            model.addAttribute("check", login);
        }else{

        }
        return "/member/check";
    }
}
