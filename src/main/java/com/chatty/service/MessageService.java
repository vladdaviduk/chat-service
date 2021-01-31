package com.chatty.service;

import com.chatty.model.Message;

public interface MessageService {

    Message saveMessage(Message message, String owner);
}
