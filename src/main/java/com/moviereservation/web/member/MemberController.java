package com.moviereservation.web.member;

import com.moviereservation.service.member.MemberService;
import com.moviereservation.utils.validator.RegisterValidator;
import com.moviereservation.web.member.dto.MemberRegisterDto;
import com.moviereservation.web.member.dto.PasswordChangeDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {
    private final MemberService service;
    private final RegisterValidator validator;

    @GetMapping("/login")
    public String login(){
        return "/member/login";
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

    @GetMapping("/register")
    public String save(Model model) {
        MemberRegisterDto member = new MemberRegisterDto(null, null, null, null, null, null);
        model.addAttribute("member", member);
        return "member/register";
    }

    @PostMapping("/register")
    public String save(@ModelAttribute("member") MemberRegisterDto member, Errors errors) {
        validator.validate(member, errors);
        if (errors.hasErrors()) {
            return "member/register";
        }

        service.save(member);
        return "redirect:/member/login";
    }

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
