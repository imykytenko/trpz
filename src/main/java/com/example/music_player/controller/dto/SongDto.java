package com.example.music_player.controller.dto;

import lombok.Data;

@Data
public class SongDto {
    private Long id;
    private String title;
    private String artist;
    private double duration;
    private String format;
    private String fileUrl;
}
