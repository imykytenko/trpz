package com.example.music_player.controller;

import com.example.music_player.controller.dto.SongDto;
import com.example.music_player.controller.mapper.SongMapper;
import com.example.music_player.facade.MusicPlayerFacade;
import com.example.music_player.model.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173"})
public class SongController {

    @Autowired
    private MusicPlayerFacade musicPlayerFacade;

    private final SongMapper songMapper;

    @PostMapping("/add")
    public ResponseEntity<SongDto> addSong(@RequestParam("title") String title,
                                           @RequestParam("artist") String artist,
                                           @RequestParam("duration") Integer duration,
                                           @RequestParam("format") String format,
                                           @RequestParam("file") MultipartFile file) throws IOException {
        String fileUrl = uploadFile(file);
        Song song = new Song();
        song.setTitle(title);
        song.setArtist(artist);
        song.setDuration(duration);
        song.setFormat(format);
        song.setFileUrl(fileUrl);

        song = musicPlayerFacade.addSong(song);
        return ResponseEntity.ok(songMapper.toDto(song));
    }

    private String uploadFile(MultipartFile file) throws IOException {
        String uploadDir = System.getProperty("user.dir") + "/songs";
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(uploadDir, fileName);

        Files.createDirectories(path.getParent());
        file.transferTo(path);

        return path.toString();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDto> findById(@PathVariable long id) {
        return ResponseEntity.of(musicPlayerFacade.findSongById(id).map(songMapper::toDto));
    }

    @GetMapping
    public List<SongDto> findAll() {
        return musicPlayerFacade.getAllSongs().stream().map(songMapper::toDto).toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        musicPlayerFacade.deleteSongById(id);
        return ResponseEntity.noContent().build();
    }
}
