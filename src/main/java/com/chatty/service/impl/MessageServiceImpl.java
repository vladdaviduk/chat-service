package com.chatty.service.impl;

import com.chatty.model.Message;
import com.chatty.repository.MessageRepository;
import com.chatty.service.MessageService;
import com.chatty.service.MessageValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageValidationService validationService;

    @Override
    public Message saveMessage(Message message, String owner) {
        validationService.validateMessageContent(message.getContent());
        message.setTimestamp(new Date());
        message.setOwner(owner);
        return messageRepository.save(message);
    }
}
