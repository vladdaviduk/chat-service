package com.chatty.controller;

import com.chatty.dto.impl.ChatDtoService;
import com.chatty.dto.impl.MessageDtoService;
import com.chatty.model.dto.ChatDto;
import com.chatty.model.dto.MessageDto;
import com.chatty.service.ChatService;
import com.chatty.service.MessageService;
import com.chatty.user.service.UserService;
import com.chatty.user.service.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChattyController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidationService validationService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatDtoService chatDtoService;

    @Autowired
    private MessageDtoService messageDtoService;

    @GetMapping("/active-users")
    public List<String> getActiveUsers() {
        return userService.getLoggedInUserNames();
    }

    @GetMapping("/chats")
    public ChatDto getChat(@RequestParam String participant) {
        validationService.validateUser(participant);
        return chatDtoService.convertToDto(chatService.getChat(userService.getCurrentLoggedInUserName(), participant));
    }

    @GetMapping("/user-chats")
    public List<ChatDto> getUserChats() {
        return chatDtoService.convertToDto(chatService.getUserChats(userService.getCurrentLoggedInUserName()));
    }

    @PostMapping("/messages")
    public MessageDto createMessage(@RequestBody MessageDto message) {
        return messageDtoService.convertToDto(messageService.saveMessage(
                messageDtoService.convertToDomain(message), userService.getCurrentLoggedInUserName()));
    }
}
