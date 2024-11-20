package com.example.music_player.service;

import com.example.music_player.model.PlaybackSession;
import java.util.List;

public interface PlaybackSessionService {
    PlaybackSession createPlaybackSession(PlaybackSession playbackSession);
    PlaybackSession getPlaybackSessionById(int id);
    List<PlaybackSession> getAllPlaybackSessions();
    PlaybackSession updatePlaybackSession(int id, PlaybackSession playbackSession);
    void deletePlaybackSession(int id);
}
