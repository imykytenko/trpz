package com.example.music_player.controller;

import com.example.music_player.controller.dto.SongCreationDto;
import com.example.music_player.controller.dto.SongDto;
import com.example.music_player.controller.mapper.SongMapper;
import com.example.music_player.model.Song;
import com.example.music_player.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SongController {

    @Autowired
    private SongService songService;

    private final SongMapper songMapper;

    @PostMapping("/add")
    public ResponseEntity<Song> addSong(@RequestParam("title") String title,
                                        @RequestParam("artist") String artist,
                                        @RequestParam("duration") Integer duration,
                                        @RequestParam("format") String format,
                                        @RequestParam("file") MultipartFile file) throws IOException {
        Song song = new Song();
        song.setTitle(title);
        song.setArtist(artist);
        song.setDuration(duration);
        song.setFormat(format);
        song.setFileData(file.getBytes());
        song = songService.add(song);
        return ResponseEntity.ok(song);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDto> findById(@PathVariable long id) {
        return ResponseEntity.of(songService.findById(id).map(songMapper::toDto));
    }

    @GetMapping
    public List<SongDto> findAll() {
        return songService.findAll().stream().map(songMapper::toDto).toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        songService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
