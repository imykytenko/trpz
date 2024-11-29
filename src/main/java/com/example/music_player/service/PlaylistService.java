package com.example.music_player.service;

import com.example.music_player.model.Playlist;
import com.example.music_player.model.Song;

import java.util.List;

public interface PlaylistService {
    Playlist createPlaylist(Playlist playlist);
    Playlist getPlaylistById(Long id);
    List<Playlist> getAllPlaylists();
    Playlist updatePlaylist(Long id, Playlist playlist);
    void deletePlaylist(Long id);
    void addSongToPlaylist(Long playlistId, Long songId);
    void removeSongFromPlaylist(Long playlistId, Long songId);
    List<Song> getSongsInPlaylist(Long playlistId);
    void savePlaylistState(Long playlistId);
    void restorePlaylistState(Long playlistId, int mementoIndex);
    void calculateStatistics(Long playlistId);
}
