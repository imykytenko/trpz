package com.example.music_player.controller.dto;

import com.example.music_player.model.Song;
import lombok.Data;

import java.util.List;

@Data
public class PlaylistDto {
    private Long id;
    private String name;
    private List<Song> songs;
}
