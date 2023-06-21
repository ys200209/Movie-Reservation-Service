package com.moviereservation.domain.member.controller;

import com.moviereservation.domain.member.MemberService;
import com.moviereservation.domain.member.controller.dto.PasswordChangeDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService service;

    @GetMapping("/me")
    public String me(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("name", userDetails.getUsername());
        return "member/me";
    }

    @GetMapping("/password")
    public String changePassword(Model model) {
        PasswordChangeDto member = new PasswordChangeDto(null, null, null);
        model.addAttribute("member", member);
        return "member/change_password";
    }

    @PostMapping("/password")
    public String changePassword(@AuthenticationPrincipal UserDetails userDetails,
                                 @Valid @ModelAttribute(name = "member") PasswordChangeDto passwordDto,
                                 BindingResult result) {
        service.changePassword(userDetails.getUsername(), passwordDto, result);

        if (result.hasErrors()) {
            return "member/change_password";
        }
        return "redirect:/member/me";
    }
}
