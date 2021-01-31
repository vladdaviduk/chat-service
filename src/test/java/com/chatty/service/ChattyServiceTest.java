package com.chatty.service;

import com.chatty.exceptions.EntityNotFountException;
import com.chatty.model.Chat;
import com.chatty.model.Message;
import com.chatty.repository.ChatRepository;
import com.chatty.repository.MessageRepository;
import com.chatty.service.impl.ChatServiceImpl;
import com.chatty.service.impl.MessageServiceImpl;
import com.chatty.service.impl.MessageValidationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChattyServiceTest {

    @InjectMocks
    private MessageServiceImpl messageService;

    @InjectMocks
    private ChatServiceImpl chatService;

    @Mock
    private MessageValidationServiceImpl messageValidationService;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private ChatRepository chatRepository;

    private static long CHAT_ID = 1;
    private static long NONEXISTENT_CHAT_ID = 1;
    private static String CONTENT = "Hello!";
    private static String FIRST_OWNER = "Mike";
    private static String SECOND_OWNER = "Pieter";
    private static String NEW_OWNER = "Mechelle";
    private static Chat CHAT = chatInstance(FIRST_OWNER, SECOND_OWNER);

    @Test
    public void testMessageService() {

        Message message = messageInstance(CONTENT);
        when(messageRepository.save(any(Message.class))).thenAnswer(m -> message);

        Message messageResult = messageService.saveMessage(message, FIRST_OWNER);
        verify(messageRepository, times(1)).save(any(Message.class));
        assertEquals(FIRST_OWNER, messageResult.getOwner());
        assertEquals(CONTENT, messageResult.getContent());
    }

    @Test
    public void testChatService() {

        when(chatRepository.findByOwner(FIRST_OWNER)).thenReturn(List.of(CHAT));
        List<Chat> chats = chatService.getUserChats(FIRST_OWNER);
        assertFalse(chats.isEmpty());

        when(chatRepository.findByOwner(NEW_OWNER)).thenReturn(Collections.emptyList());
        chats = chatService.getUserChats(NEW_OWNER);
        assertTrue(chats.isEmpty());

        when(chatRepository.findById(CHAT_ID)).thenReturn(Optional.of(CHAT));
        Chat chat = chatService.getChat(CHAT_ID);
        assertEquals(CHAT_ID, chat.getId());

        when(chatRepository.findById(NONEXISTENT_CHAT_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFountException.class, () -> chatService.getChat(NONEXISTENT_CHAT_ID));

        //test existent chat
        when(chatRepository.findByBothOwners(FIRST_OWNER, SECOND_OWNER)).thenReturn(Optional.of(CHAT));
        chat = chatService.getChat(FIRST_OWNER, SECOND_OWNER);
        verify(chatRepository, times(1)).findByBothOwners(FIRST_OWNER, SECOND_OWNER);
        verify(chatRepository, never()).save(any(Chat.class));
        assertEquals(FIRST_OWNER, chat.getFirstOwner());
        assertEquals(SECOND_OWNER, chat.getSecondOwner());

        //test new chat
        when(chatRepository.findByBothOwners(FIRST_OWNER, NEW_OWNER)).thenReturn(Optional.empty());
        when(chatRepository.save(any(Chat.class))).thenReturn(chatInstance(FIRST_OWNER, NEW_OWNER));
        chat = chatService.getChat(FIRST_OWNER, NEW_OWNER);
        verify(chatRepository, times(1)).findByBothOwners(FIRST_OWNER, NEW_OWNER);
        verify(chatRepository, times(1)).save(any(Chat.class));
        assertEquals(FIRST_OWNER, chat.getFirstOwner());
        assertEquals(NEW_OWNER, chat.getSecondOwner());
    }

    private Message messageInstance(String content) {
        Message message = new Message();
        message.setContent(content);
        message.setChat(CHAT);

        return message;
    }

    private static Chat chatInstance(String firstOwner, String secondOwner) {
        return new Chat(CHAT_ID, firstOwner, secondOwner, new ArrayList<>());
    }
}
