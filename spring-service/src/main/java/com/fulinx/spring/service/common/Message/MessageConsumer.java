/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.common.Message;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class MessageConsumer implements MessageListener {
    /**
     * Callback for processing received objects through Redis.
     *
     * @param message message must not be {@literal null}.
     * @param pattern pattern matching the channel (if specified) - can be {@literal null}.
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String messageContent = new String(message.getBody());
        // 处理消息
    }
}
