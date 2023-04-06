package com.moviereservation.controller;

import com.moviereservation.domain.Login;
import com.moviereservation.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("/check")
    public String check(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();
        String password = userDetails.getPassword();
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "/member/check";
    }

}
