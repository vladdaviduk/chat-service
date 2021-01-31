package com.chatty.service.impl;

import com.chatty.exceptions.ValidationException;
import com.chatty.service.MessageValidationService;
import com.google.common.base.Strings;
import org.springframework.stereotype.Service;


@Service
public class MessageValidationServiceImpl implements MessageValidationService {

    @Override
    public void validateMessageContent(String content) {
        if (Strings.isNullOrEmpty(content))
            throw new ValidationException("Message content should not be empty");
        if (content.length() > 5000)
            throw new ValidationException("Message length should be less than 5000 characters");
    }
}
