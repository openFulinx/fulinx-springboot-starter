/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.common.Message;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private final RedisTemplate<String, String> redisTemplate;

    public MessageProducer(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void sendMessage(String message) {
        redisTemplate.convertAndSend("queue", message);
    }
}
