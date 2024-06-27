package com.fulinx.spring.web.controller.share.captcha;

import com.fulinx.spring.core.generic.ResultVo;
import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.service.captcha.ICaptchaService;
import com.fulinx.spring.service.captcha.dto.CaptchaResultDto;
import com.fulinx.spring.service.enums.CaptchaBusinessTypeEnum;
import com.fulinx.spring.service.enums.ErrorMessageEnum;
import com.fulinx.spring.web.controller.share.captcha.vo.FetchCaptchaVo;
import com.fulinx.spring.web.framework.base.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Tag(name = "公开接口-验证码")
@Slf4j
@Validated
@RestController
@RequestMapping("/share/public/captcha")
public class ShareCaptchaController extends BaseController {

    private final ICaptchaService iCaptchaService;

    public ShareCaptchaController(ICaptchaService iCaptchaService) {
        this.iCaptchaService = iCaptchaService;
    }

    /**
     * 获取验证码
     *
     * @return
     * @throws BusinessException
     */
    @Operation(summary = "获取验证码", method = "POST")
    @PostMapping("/fetch")
    public ResultVo<String> fetch(@RequestBody @Valid FetchCaptchaVo fetchCaptchaVo) throws BusinessException, IOException {
        // 根据验证码业务类型，获取验证码的业务类型枚举
        CaptchaBusinessTypeEnum captchaBusinessTypeEnum = CaptchaBusinessTypeEnum.of(fetchCaptchaVo.getCaptchaType()).orElseThrow(() -> {
            log.error("{}, captchaBusinessType={}", ErrorMessageEnum.CAPTCHA_BUSINESS_TYPE_INCORRECT.getMessage(), fetchCaptchaVo.getCaptchaType());
            return new BusinessException(ErrorMessageEnum.SYSTEM_ERROR.getMessage(), ErrorMessageEnum.SYSTEM_ERROR.getIndex());
        });
        CaptchaResultDto captchaResultDto = iCaptchaService.fetchCaptcha(fetchCaptchaVo.getCaptchaType(), fetchCaptchaVo.getCaptchaKey());
        if (captchaResultDto.getImageData() != null && captchaResultDto.getCaptchaValue() != null) {
            iCaptchaService.cacheCaptchaValue(fetchCaptchaVo.getCaptchaKey(), captchaBusinessTypeEnum, captchaResultDto.getCaptchaValue());
        }
        return ResultVo.build(captchaResultDto.getImageData());
    }
}
