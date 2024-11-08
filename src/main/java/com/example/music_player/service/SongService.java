package com.example.music_player.service;

import com.example.music_player.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
        Song add(Song song);
        Optional<Song> findById(Long id);
        List<Song> findAll();
        void deleteById(Long id);
}
