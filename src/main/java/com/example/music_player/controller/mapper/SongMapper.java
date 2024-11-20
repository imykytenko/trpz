package com.example.music_player.controller.mapper;

import com.example.music_player.controller.dto.SongCreationDto;
import com.example.music_player.controller.dto.SongDto;
import com.example.music_player.model.Song;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {

    // Перетворення з сутності Song в DTO
    SongDto toDto(Song song);

    // Перетворення з DTO в сутність Song
    Song toEntity(SongCreationDto songDto);

}
