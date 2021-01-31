package com.chatty.dto.impl;

import com.chatty.dto.DomainConverter;
import com.chatty.dto.DtoConverter;
import com.chatty.model.Message;
import com.chatty.model.dto.MessageDto;
import com.chatty.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.chatty.util.DateUtil.fromDate;
import static com.chatty.util.DateUtil.parseDate;

@Service
public class MessageDtoService implements DtoConverter<Message, MessageDto>, DomainConverter<Message, MessageDto> {

    @Autowired
    private ChatService chatService;

    @Override
    public MessageDto convertToDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setOwner(message.getOwner());
        messageDto.setTimestamp(fromDate(message.getTimestamp()));
        messageDto.setContent(message.getContent());
        messageDto.setChatId(message.getChat().getId());

        return messageDto;
    }

    @Override
    public List<MessageDto> convertToDto(List<Message> messages) {
        return messages.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Message convertToDomain(MessageDto messageDto) {
        Message message = new Message();
        message.setOwner(messageDto.getOwner());
        message.setTimestamp(parseDate(messageDto.getTimestamp()));
        message.setContent(messageDto.getContent());
        message.setChat(chatService.getChat(messageDto.getChatId()));

        return message;
    }
}
