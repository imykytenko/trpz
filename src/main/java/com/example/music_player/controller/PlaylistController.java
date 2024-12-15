package com.example.music_player.controller;

import com.example.music_player.facade.MusicPlayerFacade;
import com.example.music_player.model.Playlist;
import com.example.music_player.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController{
    @Autowired
    private MusicPlayerFacade musicPlayerFacade;

    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        return new ResponseEntity<>(musicPlayerFacade.createPlaylist(playlist), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable Long id) {
        Playlist playlist = musicPlayerFacade.getPlaylistById(id);
        return playlist != null ? ResponseEntity.ok(playlist) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        return ResponseEntity.ok(musicPlayerFacade.getAllPlaylists());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable Long id, @RequestBody Playlist playlist) {
        Playlist updatedPlaylist = musicPlayerFacade.updatePlaylist(id, playlist);
        return updatedPlaylist != null ? ResponseEntity.ok(updatedPlaylist) : ResponseEntity.notFound().build();
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

    @GetMapping("/{id}/songs")
    public ResponseEntity<List<Song>> getSongsInPlaylist(@PathVariable Long id) {
        List<Song> songsInPlaylist = musicPlayerFacade.getSongsInPlaylist(id);

        if (songsInPlaylist != null) {
            return ResponseEntity.ok(songsInPlaylist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{playlistId}/save-state")
    public ResponseEntity<Void> savePlaylistState(@PathVariable Long playlistId) {
        musicPlayerFacade.savePlaylistState(playlistId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{playlistId}/restore-state/{index}")
    public ResponseEntity<Void> restorePlaylistState(@PathVariable Long playlistId, @PathVariable int index) {
        musicPlayerFacade.restorePlaylistState(playlistId, index);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{playlistId}/statistics")
    public void calculateStatistics(@PathVariable Long playlistId) {
        musicPlayerFacade.calculatePlaylistStatistics(playlistId);
    }
}
