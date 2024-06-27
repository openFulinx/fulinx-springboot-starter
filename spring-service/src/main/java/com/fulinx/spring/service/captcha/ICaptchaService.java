package com.fulinx.spring.service.captcha;



import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.service.captcha.dto.CaptchaResultDto;
import com.fulinx.spring.service.enums.CaptchaBusinessTypeEnum;

import java.io.IOException;

public interface ICaptchaService {


    String getCaptchaValue(String captchaKey,
                           CaptchaBusinessTypeEnum captchaBusinessTypeEnum);

    boolean matchCaptchaValue(String captchaValue, String captchaKey,
                              CaptchaBusinessTypeEnum captchaBusinessTypeEnum);

    void flushCaptchaValue(String captchaKey,
                           CaptchaBusinessTypeEnum captchaBusinessTypeEnum);

    /**
     * Cache CaptchaValue
     *
     * @param captchaKey
     * @param captchaBusinessTypeEnum
     * @param captchaValue
     * @return
     */

    String cacheCaptchaValue(String captchaKey,
                             CaptchaBusinessTypeEnum captchaBusinessTypeEnum,
                             String captchaValue);

    /**
     * 获取验证书码
     *
     * @param captchaKey
     * @return
     * @throws BusinessException
     * @throws IOException
     */
    CaptchaResultDto fetchCaptcha(Integer captchaType, String captchaKey) throws BusinessException, IOException;
}
