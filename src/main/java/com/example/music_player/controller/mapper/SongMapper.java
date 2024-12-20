package com.example.music_player.controller.mapper;

import com.example.music_player.controller.dto.SongCreationDto;
import com.example.music_player.controller.dto.SongDto;
import com.example.music_player.model.Song;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {

    SongDto toDto(Song song);

    Song toEntity(SongCreationDto songDto);
}
