package com.example.music_player.controller.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SongCreationDto {
    private String title;
    private String artist;
    private double duration;
    private String format;
    private MultipartFile file;
    public SongCreationDto(String title, String artist, double duration, String format, MultipartFile file) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.format = format;
        this.file = file;
    }
}
