package com.example.music_player.controller;

import com.example.music_player.controller.dto.PlaylistCreationDto;
import com.example.music_player.controller.dto.PlaylistDto;
import com.example.music_player.controller.dto.PlaylistUpdateDto;
import com.example.music_player.facade.MusicPlayerFacade;
import com.example.music_player.controller.mapper.PlaylistMapper;
import com.example.music_player.model.Playlist;
import com.example.music_player.model.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/playlists")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173"})
public class PlaylistController {

    @Autowired
    private MusicPlayerFacade musicPlayerFacade;

    @Autowired
    private PlaylistMapper playlistMapper;

    @PostMapping("/add")
    public ResponseEntity<PlaylistDto> createPlaylist(@RequestBody PlaylistCreationDto playlistDto) {
        var playlist = musicPlayerFacade.createPlaylist(playlistMapper.toEntity(playlistDto));
        return new ResponseEntity<>(playlistMapper.toDto(playlist), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDto> getPlaylistById(@PathVariable Long id) {
        Playlist playlist = musicPlayerFacade.getPlaylistById(id);
        return playlist != null ? ResponseEntity.ok(playlistMapper.toDto(playlist)) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<PlaylistDto> getAllPlaylists() {
        return musicPlayerFacade.getAllPlaylists().stream().map(playlistMapper::toDto).toList();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaylistDto> updatePlaylist(@PathVariable Long id, @RequestBody PlaylistUpdateDto playlistDto) {
        var playlist = musicPlayerFacade.updatePlaylist(id, playlistMapper.update(playlistDto));
        return playlist != null ? ResponseEntity.ok(playlistMapper.toDto(playlist)) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<List<Song>> getSongsInPlaylist(@PathVariable Long id) {
        List<Song> songsInPlaylist = musicPlayerFacade.getSongsInPlaylist(id);

        if (songsInPlaylist != null) {
            return ResponseEntity.ok(songsInPlaylist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        musicPlayerFacade.deletePlaylist(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<Void> addSongToPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        musicPlayerFacade.addSongToPlaylist(playlistId, songId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<Void> removeSongFromPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        musicPlayerFacade.removeSongFromPlaylist(playlistId, songId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/next-song")
    public ResponseEntity<Song> getNextSong(@PathVariable Long id) {
        try {
            Song nextSong = musicPlayerFacade.getNextSong(id);
            return ResponseEntity.ok(nextSong);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{playlistId}/statistics")
    public ResponseEntity<Map<String, Object>> calculateStatistics(@PathVariable Long playlistId) {
        Map<String, Object> statistics = musicPlayerFacade.calculateStatistics(playlistId);
        return ResponseEntity.ok(statistics);
    }
}