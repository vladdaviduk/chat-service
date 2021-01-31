package com.chatty.service.impl;

import com.chatty.exceptions.EntityNotFountException;
import com.chatty.model.Chat;
import com.chatty.repository.ChatRepository;
import com.chatty.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public List<Chat> getUserChats(String owner) {
        return chatRepository.findByOwner(owner);
    }

    @Override
    public Chat getChat(String owner, String participant) {
        return chatRepository.findByBothOwners(owner, participant)
                .orElseGet(() -> chatRepository.save(new Chat(owner, participant)));
    }

    @Override
    public Chat getChat(long id) {
        return chatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFountException(Chat.class, id));
    }
}
