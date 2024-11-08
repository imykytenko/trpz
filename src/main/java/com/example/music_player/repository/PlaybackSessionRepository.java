package com.example.music_player.repository;

import com.example.music_player.model.PlaybackSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaybackSessionRepository extends JpaRepository<PlaybackSession, Integer> {
}
