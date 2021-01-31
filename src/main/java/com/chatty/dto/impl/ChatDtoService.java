package com.chatty.dto.impl;

import com.chatty.dto.DtoConverter;
import com.chatty.model.Chat;
import com.chatty.model.dto.ChatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatDtoService implements DtoConverter<Chat, ChatDto> {

    @Autowired
    private MessageDtoService messageDtoService;

    @Override
    public ChatDto convertToDto(Chat chat) {
        ChatDto chatDto = new ChatDto();
        chatDto.setId(chat.getId());
        chatDto.setFirstOwner(chat.getFirstOwner());
        chatDto.setSecondOwner(chat.getSecondOwner());
        chatDto.setMessages(messageDtoService.convertToDto(chat.getMessages()));

        return chatDto;
    }

    @Override
    public List<ChatDto> convertToDto(List<Chat> chats) {
        return chats.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
