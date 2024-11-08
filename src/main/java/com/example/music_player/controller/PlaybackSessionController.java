package com.example.music_player.controller;

import com.example.music_player.model.PlaybackSession;
import com.example.music_player.service.PlaybackSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playback-sessions")
public class PlaybackSessionController{

    private final PlaybackSessionService playbackSessionService;

    @Autowired
    public PlaybackSessionController(PlaybackSessionService playbackSessionService) {
        this.playbackSessionService = playbackSessionService;
    }

    @PostMapping
    public ResponseEntity<PlaybackSession> createPlaybackSession(@RequestBody PlaybackSession playbackSession) {
        return new ResponseEntity<>(playbackSessionService.createPlaybackSession(playbackSession), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaybackSession> getPlaybackSessionById(@PathVariable int id) {
        PlaybackSession playbackSession = playbackSessionService.getPlaybackSessionById(id);
        return playbackSession != null ? ResponseEntity.ok(playbackSession) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PlaybackSession>> getAllPlaybackSessions() {
        return ResponseEntity.ok(playbackSessionService.getAllPlaybackSessions());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaybackSession> updatePlaybackSession(@PathVariable int id, @RequestBody PlaybackSession playbackSession) {
        PlaybackSession updatedPlaybackSession = playbackSessionService.updatePlaybackSession(id, playbackSession);
        return updatedPlaybackSession != null ? ResponseEntity.ok(updatedPlaybackSession) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaybackSession(@PathVariable int id) {
        playbackSessionService.deletePlaybackSession(id);
        return ResponseEntity.noContent().build();
    }
}
