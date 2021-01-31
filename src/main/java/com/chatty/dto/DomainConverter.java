package com.chatty.dto;

public interface DomainConverter<DM, DTO> {

    DM convertToDomain(DTO messageDto);
}
