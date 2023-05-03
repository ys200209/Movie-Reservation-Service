package com.moviereservation.domain.register.controller;

import com.moviereservation.domain.register.RegisterService;
import com.moviereservation.domain.register.controller.dto.MemberRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService service;

    @GetMapping("/register")
    public String save(Model model) {
        MemberRegisterDto member = new MemberRegisterDto(null, null, null, null, null, null);
        model.addAttribute("member", member);
        return "member/register";
    }

    @PostMapping("/register")
    public String save(@ModelAttribute("member") MemberRegisterDto member) {
        service.save(member);
        return "redirect:/member/login";
    }
}
