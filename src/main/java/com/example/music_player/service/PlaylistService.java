package com.example.music_player.service;

import com.example.music_player.model.Playlist;
import com.example.music_player.model.Song;

import java.util.List;
import java.util.Map;

public interface PlaylistService {
    Playlist createPlaylist(Playlist playlist);
    Playlist getPlaylistById(Long id);
    List<Playlist> getAllPlaylists();
    Playlist updatePlaylist(Long id, Playlist playlist);
    void deletePlaylist(Long id);
    void addSongToPlaylist(Long playlistId, Long songId);
    void removeSongFromPlaylist(Long playlistId, Long songId);
    Song getNextSong(Long playlistId);
    List<Song> getSongsInPlaylist(Long playlistId);
    Map<String, Object> calculateStatistics(Long playlistId);
}
