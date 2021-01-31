package com.chatty.dto;
import java.util.List;

public interface DtoConverter<DM, DTO> {

    DTO convertToDto(DM domainModel);

    List<DTO> convertToDto(List<DM> domainModels);
}
