package com.moviereservation.web.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@RequiredArgsConstructor
@ToString
public class PasswordChangeDto {
    @NotBlank
    private final String memberPassword;

    @NotBlank
    @Size(min = 8, max = 45, message = "{valid.size.password}")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$", message = "{valid.pattern.password}")
    private final String newPassword;

    @NotBlank
    @Size(min = 8, max = 45, message = "{valid.size.password}")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$", message = "{valid.pattern.password}")
    private final String confirmPassword;
}
