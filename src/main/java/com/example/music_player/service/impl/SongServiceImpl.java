package com.example.music_player.service.impl;

import com.example.music_player.model.Song;
import com.example.music_player.repository.SongRepository;
import com.example.music_player.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public Song add(Song song) {
        return songRepository.save(song);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public List<Song> findAll() {
        List<Song> songs = songRepository.findAll();
        System.out.println();
        return songs;
    }

    @Override
    public void deleteById(Long id) {

    }
}
