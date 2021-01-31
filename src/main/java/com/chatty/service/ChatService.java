package com.chatty.service;

import com.chatty.model.Chat;

import java.util.List;

public interface ChatService {

    List<Chat> getUserChats(String owner);

    Chat getChat(String owner, String participant);

    Chat getChat(long id);
}
