package com.example.music_player.service.impl;

import com.example.music_player.model.PlaybackSession;
import com.example.music_player.repository.PlaybackSessionRepository;
import com.example.music_player.service.PlaybackSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaybackSessionServiceImpl implements PlaybackSessionService {

    private final PlaybackSessionRepository playbackSessionRepository;

    @Autowired
    public PlaybackSessionServiceImpl(PlaybackSessionRepository playbackSessionRepository) {
        this.playbackSessionRepository = playbackSessionRepository;
    }

    @Override
    public PlaybackSession createPlaybackSession(PlaybackSession playbackSession) {
        return playbackSessionRepository.save(playbackSession);
    }

    @Override
    public PlaybackSession getPlaybackSessionById(int id) {
        return playbackSessionRepository.findById(id).orElse(null);
    }

    @Override
    public List<PlaybackSession> getAllPlaybackSessions() {
        return playbackSessionRepository.findAll();
    }

    @Override
    public PlaybackSession updatePlaybackSession(int id, PlaybackSession playbackSession) {
        if (playbackSessionRepository.existsById(id)) {
            playbackSession.setId(id);
            return playbackSessionRepository.save(playbackSession);
        }
        return null;
    }

    @Override
    public void deletePlaybackSession(int id) {
        playbackSessionRepository.deleteById(id);
    }
}
