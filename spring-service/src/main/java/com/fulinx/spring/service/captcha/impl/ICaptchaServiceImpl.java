/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.captcha.impl;

import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.service.captcha.ICaptchaService;
import com.fulinx.spring.service.captcha.dto.CaptchaResultDto;
import com.fulinx.spring.service.enums.CaptchaBusinessTypeEnum;
import com.fulinx.spring.service.enums.ErrorMessageEnum;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

@CacheConfig(cacheNames = {"com.fulinx.spring.service.captcha.impl.ICaptchaServiceImpl"})
@Slf4j
@Service
public class ICaptchaServiceImpl implements ICaptchaService {


    private final ICaptchaService iCaptchaService;

    @Lazy
    @Autowired
    public ICaptchaServiceImpl(ICaptchaService iCaptchaService) {
        this.iCaptchaService = iCaptchaService;
    }

    @Cacheable(key = "#captchaBusinessTypeEnum + ':' + #captchaKey", unless = "#result == null")
    @Override
    public String getCaptchaValue(String captchaKey,
                                  CaptchaBusinessTypeEnum captchaBusinessTypeEnum) {
        return null;
    }

    @Override
    public boolean matchCaptchaValue(String captchaValue, String captchaKey, CaptchaBusinessTypeEnum captchaBusinessTypeEnum) {
        String captchaValueFromCache = iCaptchaService.getCaptchaValue(captchaKey, captchaBusinessTypeEnum);
        log.debug("The graphic verification code retrieved from the cache is，captchaKey={}, captchaBusinessType={}, captchaValue={}", captchaKey, captchaBusinessTypeEnum.getMessageKey(), captchaValueFromCache);
        return StringUtils.equals(captchaValue, captchaValueFromCache);
    }

    @CacheEvict(key = "#captchaBusinessTypeEnum + ':' + #captchaKey")
    @Override
    public void flushCaptchaValue(String captchaKey,
                                  CaptchaBusinessTypeEnum captchaBusinessTypeEnum) {
    }

    @Override
    @CachePut(key = "#captchaBusinessTypeEnum + ':' + #captchaKey")
    public String cacheCaptchaValue(String captchaKey, CaptchaBusinessTypeEnum captchaBusinessTypeEnum, String captchaValue) {
        log.debug("The graphic verification code saved to the cache is，captchaKey={}, captchaBusinessType={}, captchaValue={}", captchaKey, captchaBusinessTypeEnum.getMessageKey(), captchaValue);
        return captchaValue;
    }

    /**
     * 获取验证码
     *
     * @param captchaKey
     * @return
     * @throws BusinessException
     * @throws IOException
     */
    @Override
    public CaptchaResultDto fetchCaptcha(Integer captchaType, String captchaKey) throws BusinessException, IOException {

        // 根据验证码业务类型，获取验证码的业务类型枚举
        CaptchaBusinessTypeEnum.of(captchaType).orElseThrow(() -> {
            log.error("{}, captchaBusinessType={}", ErrorMessageEnum.CAPTCHA_BUSINESS_TYPE_INCORRECT.getMessage(), captchaType);
            return new BusinessException(ErrorMessageEnum.SYSTEM_ERROR.getMessage(), ErrorMessageEnum.SYSTEM_ERROR.getIndex());
        });

        // 创建Kaptcha对象
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();

        // 设置配置
        Properties properties = new Properties();
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        // 生成验证码
        String text = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(text);

        // 将图片字节流转换成Base64编码
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        String imageData = Base64.getEncoder().encodeToString(baos.toByteArray());
        // 拼接Base64编码的图片数据
        imageData = "data:image/png;base64," + imageData;
        // 返回Base64编码的图片数据
        CaptchaResultDto captchaResultDto = new CaptchaResultDto();
        captchaResultDto.setImageData(imageData);
        captchaResultDto.setCaptchaValue(text);
        return captchaResultDto;
    }
}
