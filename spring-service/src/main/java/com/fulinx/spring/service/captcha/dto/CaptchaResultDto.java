package com.fulinx.spring.service.captcha.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CaptchaResultDto {

    @Parameter(name = "Base64 Image")
    private String imageData;

    @Parameter(name = "Captcha Value")
    private String captchaValue;
}
