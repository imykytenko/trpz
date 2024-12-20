package com.example.music_player.controller.mapper;

import com.example.music_player.controller.dto.*;
import com.example.music_player.model.Playlist;
import com.example.music_player.model.Song;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {

    PlaylistDto toDto(Playlist playlist);

    Playlist toEntity(PlaylistCreationDto playlistDto);

    Playlist update(PlaylistUpdateDto playlistDto);

    SongDto toDto(Song song);

    @Mapping(target = "id", ignore = true)
    Song toEntity(SongCreationDto songCreationDto);
}
