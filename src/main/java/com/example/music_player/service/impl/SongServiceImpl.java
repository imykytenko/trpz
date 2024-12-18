package com.example.music_player.service.impl;

import com.example.music_player.model.Playlist;
import com.example.music_player.model.Song;
import com.example.music_player.repository.PlaylistRepository;
import com.example.music_player.repository.SongRepository;
import com.example.music_player.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;
    @Autowired
    private PlaylistRepository playlistRepository;

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
        // Перевірка, чи пісня існує в базі даних
        Optional<Song> song = songRepository.findById(id);
        if (song.isPresent()) {
            // Видаляємо пісню з усіх плейлистів, де вона є
            List<Playlist> playlistsWithSong = playlistRepository.findBySongsId(id);
            for (Playlist playlist : playlistsWithSong) {
                playlist.getSongs().remove(song.get()); // Видаляємо пісню з плейлисту
                playlistRepository.save(playlist); // Зберігаємо оновлений плейлист
            }

            // Тепер можна безпечно видаляти пісню з бази
            songRepository.deleteById(id);

            // Додатково, можна видалити файл пісні з файлової системи
            Path filePath = Paths.get(song.get().getFileUrl());
            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                // Логування помилки при видаленні файлу
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("Song not found");
        }
    }
}
